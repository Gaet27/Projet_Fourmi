package Iterations;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import Controller.ArcController;
import Controller.MetroController;
import Controller.StationController;
import Model.Arc;
import Model.Metro;
import Model.Metro.Etat;
import Model.Station;


public class Main {

	////////////////////////////////////////////////////////////////////////////////
	////////////////----------ON RENSEIGNE ICI -------------------//////////////////
	////////////////////////////////////////////////////////////////////////////////
	
	//LE NOMBRE DE METRO
	final static int nbMetro = 800;
	//LE NOMBRE D'ITERATIONS
	final static int nbIterations = 600;
	//LE TAUX D'EVAPORATION
	final static double tauxEvaporation = 0.02;
	//DE COMBIEN AVANCE LES METROS PAR ITERATION SUR UN ARC EN SECONDES
	final static int tempsIteration = 20;  
	//LA STATION DE DEPART DES METROS
	final static int stationDeDepart = 274;
	//LA STATION D'ARRIVEE
	final static int stationDeDestinationFinale = 360;
	//PERMET D'AUGMENTER LA VALEUR D'UN THREAD SLEEP, POUR VOIR LES VALEURS DES METROS ENTRE CHAQUE ITERATION
	final static int tempsEntreIteration = 0;
	
	
	public static void main(String[] args) {
		
		Station stationDepart = new StationController();
		Station prochaineStation = new StationController();
		Arc arc = new ArcController();
		
		//CREATION DES STATIONS ET DES ARCS VIA LES FICHERS TXT
		//creationStation();
		creerArcs();
		creerStations();
		
		//AFFICHAGE DES ARCS ET STATIONS DU FICHIER TXT
//		for (Station key : StationController.ListeStation)
//		{
//			System.out.println(key.getId());
//			System.out.println(key.getNom());
//			for(Arc key2: key.getArcStation()){
//			  System.out.println(key2.getDepart()+" : " + key2.getArrivee());
//			}
//			System.out.println("\n---------------------------------------\n");
//		}

		//ITERATIONS
		for (int i = 1; i <= nbIterations; i++) {
			
			//EFFECTUE L EVAPORATION DES PHEROMONE A CHAQUE ITERATION
			evaporation();
			
			//CREER LES NOUVEAUX METROS
			premierNoeud(stationDepart, prochaineStation, arc);
			
			//POUR CHAQUE METRO FAIRE EXISTANT ET NOUVEAU FAIRE
			for(Metro key : MetroController.ListeMetro)
			{
				//SI LE NOMBRE DE STATION VISITE EST SUPERIEUR A 100, ON SUPPRIME LE METRO
				if(key.getNbStationVisitees() > 100)
				{
					key = null;
				}
				else
				{
					switch (key.etat) {
					
					//LANCE LE METRO QUI VIENT D'ETRE CREE SUR SON ARC
					case premierNoeud:
						demarrerMetro(key, arc);
						
					case cherche:
						//POUR LES METROS DEJA LANCER
						cherche(key, stationDepart, prochaineStation, arc);
						
					case rentre:
						//LE METRO RENTRE, LES PHEROMONES SONT DEPOSES, DONC ON LE SUPPRIME
						suprMetro(key);
						break;
					}
				}
			}
		}
	}
	
	
	//CREATION DE STATIONS ET ARCS DE TEST
//	public static void creationStation()
//	{
//		//CREATION DE 4 STATIONS
//		Station station1 = new StationController();
//		station1.id = 1;
//		station1.nom = "Anvers";
//		
//		Station station2 = new StationController();
//		station2.id = 2;
//		station2.nom = "Liberté";
//		
//		Station station3 = new StationController();
//		station3.id = 3;
//		station3.nom = "Madeleine";
//		
//		Station station4 = new StationController();
//		station4.id = 4;
//		station4.nom = "Mirabeau";
//		
//		//LIAISONS DES 4 STATIONS
//		station1.lierStation(station2, 35);
//		station1.lierStation(station3, 42);
//		station2.lierStation(station4, 38);
//		station3.lierStation(station4, 48);
//		
//		//AFFICHAGE DES STATIONS
//		affichageStation(station1, station2, station3, station4);
//	}
//	
	
	//AFFICHAGE DES STATION ET ARCS DE TEST
//	public static void affichageStation(Station station1, Station station2, Station station3, Station station4){
//		
//		System.out.println("Nom de la station : " + station1.getNom() + "\n");
//		System.out.println("Liste des arcs : \n");
//		for(Arc key: station1.getArcStation()){
//			  System.out.println(key.getDepart()+" : " + key.getArrivee());
//	    }
//		System.out.println("\n---------------------------------------\n");
//		
//		System.out.println("Nom de la station : " + station2.getNom() + "\n");
//		System.out.println("Liste des arcs : \n");
//		for(Arc key: station2.getArcStation()){
//			  System.out.println(key.getDepart()+" : " + key.getArrivee());
//	    }
//		System.out.println("\n---------------------------------------\n");
//		
//		System.out.println("Nom de la station : " + station3.getNom() + "\n");
//		System.out.println("Liste des arcs : \n");
//		for(Arc key: station3.getArcStation()){
//			  System.out.println(key.getDepart()+" : " + key.getArrivee());
//	    }
//		System.out.println("\n---------------------------------------\n");
//		
//		System.out.println("Nom de la station : " + station4.getNom() + "\n");
//		System.out.println("Liste des arcs : \n");
//		for(Arc key: station4.getArcStation()){
//			  System.out.println(key.getDepart()+" : " + key.getArrivee());
//	    }
//		System.out.println("\n---------------------------------------\n");
//		
//		//TEMPORAIRE POUR VOIR LA CREATIO DES STATIONS
//		try {
//			Thread.sleep(tempsEntreIteration);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	
	
	
	public static void premierNoeud(Station stationDepart, Station prochaineStation, Arc arc){
		
		//CREATIONS DE NOUVEAUX METROS
		for (int j = 1; j <= nbMetro / nbIterations; j++)
		{
			//CREATION DU METRO
			Metro metro = new MetroController();
			//L'OBJET stationDepart DEVIENT EGALE A L'OBJET STATION AYANT POUR ID, L'ID RENSEIGNE DANS LES 
			// VARIABLES STATIQUES
			stationDepart = stationDepart.getStationId(stationDeDepart);
			//ON AJOUTE DANS SA LISTE DE STATIONS A VISITER TOUTES LES STATIONS SAUF LA STATION DE DEPART
			for (Station key : StationController.ListeStation)
			{
				if(key.getId() != stationDeDepart)
				metro.setStationsAVisitees(key);
			}
			//ON LUI DONNE LA STATION DE DEPART
			metro.setStationOrigin(stationDepart);
			//ON AJOUTE LA STATION DE DEPART A LA LISTE DES STATIONS VISITES
			metro.setStationsVisitees(stationDepart);
			//SA STATION COURANTE, LA DERNIERE VISITEE
			metro.setStationCurrent(stationDepart);
			//ON LUI TROUVE LA PROCHAINE STATION A ATTEINDRE AVEC UN FONCTIONNEMENT PAR ROUE BIAISEE
			prochaineStation = metro.findNextSearchStation(metro);
			//ON INSTANCIE L'OBJET ARC, QUI EST L'ARC ENTRE LA STATION DE DEPART ET LA STATION DE DESTINATION 
			//TROUVEE
			arc = metro.findArcByStationId(stationDepart, prochaineStation);
			//ON OBTIENT DONC LE TEMPS DE PARCOURS SUR L'ARC QUE LE METRO VA DEVOIR PARCOURIR
			metro.setTempsTrajetArc(arc.gettempsParcours());
			metro.setStationDestination(prochaineStation);
			
			//L'OBJET prochaineStation EST MODIFIE POUR QUE METRO OBTIENNE SA DESTINATION FINALE
			prochaineStation = prochaineStation.getStationId(stationDeDestinationFinale);
			metro.setStationDestinationFinal(prochaineStation);
			metro.setEtat(Etat.premierNoeud);
			//ON PASSE LE NOMBRE DE STATION VISITE A UN, LA STATION DE DEPART
			metro.setNbStationVisitees(1);
		}
	}
	
	
	
	public static void cherche(Metro metro, Station stationDepart, Station prochaineStation, Arc arc){
		
		//SI LE METRO A FINI DE PARCOURIR SON ARC
		if (metro.currentArcSize >= metro.tempsTrajetArc) 
		{
			//ON AJOUTE LA STATION DE DESTINATION QUE L'ON VIENT D'ATTEINDRE AUX STATIONS VISITES
			metro.setStationsVisitees(metro.getStationDestination());
			//ON ENLEVE LA STATION DES STATIONS A VISITER
			metro.stationsAVisitees.remove(metro.getStationDestination());
			stationDepart = metro.getStationCurrent();
			//LA STATION COURANTE DEVIENT LA DESTINATION ATTEINTE
			metro.stationCurrent = metro.stationDestination;
			//si la station ou l'ON SE TROUVE EST EGAL A LA STATION DE DESTINATION FINALE
			//ALORS ON DEPOSE LES PHEROMONES !
			if (metro.stationCurrent == metro.stationDestinationFinal)
			{
				//ON RECUPERE LA LISTE D'ARC PARCOURUE PAR LE METRO
				arc = metro.findArcByStationId(stationDepart, metro.getStationDestination());
				HashSet<Arc> arcsParcourus = new HashSet<Arc>();
				arcsParcourus = metro.getArcsBetweenStations(metro.getStationsVisitees());
				//ON AJOUTE 1 A CHAQUE ARC
				for (Arc arcs : arcsParcourus) {
					arcs.setPheromone(arc.getPheromone()+1);
				}
				metro.setEtat(Etat.rentre);
			}else{
				//On stocke le déplacement en trop sur un arc pour le ré-affecter sur le nouvel arc
				int residuTemps = metro.tempsTrajetArc - metro.currentArcSize;
				metro.setCurrentArcSize(residuTemps);
				
				// On envoi le metro vers une nouvelle station
				prochaineStation = metro.findNextSearchStation(metro);
				//on trouve le nouvel arc sur lequel il va s'engager
				metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setStationDestination(prochaineStation);
				arc = metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setTempsTrajetArc(arc.gettempsParcours());
				metro.nbStationVisitees++;
				metro.currentArcSize += tempsIteration;
			}			
		}else{
			//RECUPERE L'ARC SUR LEQUEL LE METRO EST ENGAGE POUR ENSUITE POUVOIR AFFCIHER LE TAUX DE PHEROMONE 
			//ACTUEL QU'IL POSSEDE
			arc = metro.findArcByStationId(metro.getStationCurrent(), metro.getStationDestination());
			metro.currentArcSize += tempsIteration;
		}
		

		//AFFICHAGE DE CHAQUE METRO A L'ETAT CHERCHE OU RENTRE
		System.out.println("Etat du métro : " + metro.getEtat());
		System.out.println("Position sur l'arc : " + metro.getCurrentArcSize()+" sur "+ metro.getTempsTrajetArc());
		System.out.println("Nombre de phéromones : " + arc.getPheromone());
		System.out.println("Dernière station visitée : " + metro.getStationCurrent().getNom());
		System.out.println("Nombre de stations visitées : " + metro.getNbStationVisitees());
		System.out.println("Prochaine destination de la station : " + metro.getStationDestination().getNom());
		System.out.println("Point de départ : " + metro.getStationOrigin().getNom());
		System.out.println("Destination finale : " + metro.getStationDestinationFinal().getNom());
		System.out.println("\n---------------------------------------\n");

		//PERMET DE RALENTIR L'AFFICHAGE ENTRE DEUX METROS
		try {
			Thread.sleep(tempsEntreIteration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void evaporation(){
	
		for (Arc key : ArcController.ListeArc)
		{
			//SI L'OBJET EST NON NUL ON EFFECTUE L'EVAPORATION DEFINIT DANS LES STATICS
			if(key.getDepart() != 0)
			{
				double phe = key.getPheromone();
				phe = phe * (1 - tauxEvaporation);
				phe = Math.floor(10 * phe)/10;
				key.setPheromone(phe);
			}
		}
	}
	
	//PASSE LE METRO DE L'ETAT PREMIER NOEUD A CHERCHE ET L'ENGAGE SUR L'ARC
	public static void demarrerMetro(Metro metro, Arc arc){
		metro.setEtat(Etat.cherche);
		metro.setCurrentArcSize(tempsIteration);
	}
	
	//SURRPIME LE METRO
	public static void suprMetro(Metro metro){
			metro = null;
	}
	
	
	//PARCOURS DU FICHIER
	public static void creerStations (){
		String fichier = "donneesMetro.txt";
		try{
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr= new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			int id;
			String nom;
			String ligne;
			while((ligne=br.readLine())!=null){
				String str[] =ligne.split("" + "");
				int n = 4;
				int length = ligne.length();
				id = Integer.parseInt(ligne.substring(0, n));
				nom = ligne.substring(n+1, length);
				Station station = new StationController();
				station.setId(id);
				station.setNom(nom);
				station.lier(station);
			}
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public static void creerArcs (){
		String fichier = "donneesArc.txt";
		try{
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr= new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			int depart;
			int arrivee;
			int tempsParcours;
			String ligne;
			while((ligne=br.readLine())!=null){
				String str[] =ligne.split(" ");
				depart = Integer.parseInt(str[0]);
				arrivee = Integer.parseInt(str[1]);
				tempsParcours = Integer.parseInt(str[2]);
				Arc arc = new ArcController();
				arc.setArrivee(arrivee);
				arc.setDepart(depart);
				arc.settempsParcours(tempsParcours);
				arc.setPheromone(0);
			}
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
