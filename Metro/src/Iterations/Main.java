package Iterations;
import java.util.HashSet;

import Controller.*;
import Model.*;
import Model.Metro.Etat;


public class Main {

	final static int nbMetro = 500;
	final static int nbIterations = 100;
	final static double tauxEvaporation = 0.02;
	final static int tempsIteration = 5;         //En secondes
	final static int stationDeDepart = 1;
	final static int stationDeDestinationFinale = 4;
	final static int tempsEntreIteration = 0;
	
	
	public static void main(String[] args) {
		
		Station stationDepart = new StationController();
		Station prochaineStation = new StationController();
		Arc arc = new ArcController();
		
		//CREATION DES STATIONS ET DES ARCS
		creationStation();

		//ITERATIONS
		for (int i = 1; i <= nbIterations; i++) {
			
			//EFFECTUE L EVAPORATION DES PHE 
			evaporation();
			
			//CREER LES NOUVEAUX METROS
			premierNoeud(stationDepart, prochaineStation, arc);
			
			//POUR CHAQUE METRO FAIRE EXISTANT ET NOUVEAU FAIRE
			for(Metro key : MetroController.ListeMetro)
			{
				switch (key.etat) {
				
				case premierNoeud:
					demarrerMetro(key, arc);
					
				case cherche:
					cherche(key, stationDepart, prochaineStation, arc);
					
				case rentre:
					suprMetro(key);
					break;
				}
			}
		}
	}
	
	
	//FONCTIONS DU MAIN
	public static void creationStation()
	{
		//CREATION DE 4 STATIONS
		Station station1 = new StationController();
		station1.id = 1;
		station1.nom = "Anvers";
		
		Station station2 = new StationController();
		station2.id = 2;
		station2.nom = "Libert�";
		
		Station station3 = new StationController();
		station3.id = 3;
		station3.nom = "Madeleine";
		
		Station station4 = new StationController();
		station4.id = 4;
		station4.nom = "Mirabeau";
		
		//LIAISONS DES 4 STATIONS
		station1.lierStation(station2, 35);
		station1.lierStation(station3, 30);
		station2.lierStation(station4, 38);
		station3.lierStation(station4, 48);
		
		//AFFICHAGE DES STATIONS
		affichageStation(station1, station2, station3, station4);
	}
	
	public static void affichageStation(Station station1, Station station2, Station station3, Station station4){
		
		System.out.println("Nom de la station : " + station1.getNom() + "\n");
		System.out.println("Liste des arcs : \n");
		for(Arc key: station1.getArcStation()){
			  System.out.println(key.getDepart()+" : " + key.getArrivee());
	    }
		System.out.println("\n---------------------------------------\n");
		
		System.out.println("Nom de la station : " + station2.getNom() + "\n");
		System.out.println("Liste des arcs : \n");
		for(Arc key: station2.getArcStation()){
			  System.out.println(key.getDepart()+" : " + key.getArrivee());
	    }
		System.out.println("\n---------------------------------------\n");
		
		System.out.println("Nom de la station : " + station3.getNom() + "\n");
		System.out.println("Liste des arcs : \n");
		for(Arc key: station3.getArcStation()){
			  System.out.println(key.getDepart()+" : " + key.getArrivee());
	    }
		System.out.println("\n---------------------------------------\n");
		
		System.out.println("Nom de la station : " + station4.getNom() + "\n");
		System.out.println("Liste des arcs : \n");
		for(Arc key: station4.getArcStation()){
			  System.out.println(key.getDepart()+" : " + key.getArrivee());
	    }
		System.out.println("\n---------------------------------------\n");
		
		//TEMPORAIRE POUR VOIR LA CREATIO DES STATIONS
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void premierNoeud(Station stationDepart, Station prochaineStation, Arc arc){
		
		//CREATIONS DE NOUVEAUX METROS
		for (int j = 1; j <= nbMetro / nbIterations; j++)
		{
			Metro metro = new MetroController();
			stationDepart = stationDepart.getStationId(stationDeDepart);
			for (Station key : StationController.ListeStation)
			{
				if(key.getId() != stationDeDepart)
				metro.setStationsAVisitees(key);
			}
			//metro.setStationsVisitees(stationDepart);
			metro.setStationOrigin(stationDepart);
			metro.setStationCurrent(stationDepart);
			prochaineStation = metro.findNextSearchStation(stationDepart);
			arc = metro.findArcByStationId(stationDepart, prochaineStation);
			metro.setTempsTrajetArc(arc.gettempsParcours());
			metro.setStationDestination(prochaineStation);
			
			//L'OBJET prochaineStation EST MODIFIE POUR QUE METRO OBTIENNE SA DESTINATION FINALE
			prochaineStation = prochaineStation.getStationId(stationDeDestinationFinale);
			metro.setStationDestinationFinal(prochaineStation);
			
			metro.setEtat(Etat.premierNoeud);
			metro.setNbStationVisitees(1);
		}
	}
	
	
	
	public static void cherche(Metro metro, Station stationDepart, Station prochaineStation, Arc arc){
		
		
		if (metro.currentArcSize >= metro.tempsTrajetArc) 
		{
			metro.setStationsVisitees(metro.stationCurrent);
			metro.stationsAVisitees.remove(metro.stationCurrent);
			stationDepart = metro.getStationCurrent();
			metro.stationCurrent = metro.stationDestination;
			if (metro.stationCurrent == metro.stationDestinationFinal)
			{
				arc = metro.findArcByStationId(stationDepart, metro.getStationDestination());
				metro.setStationsVisitees(metro.stationCurrent);
				HashSet<Arc> arcsParcourus = new HashSet<Arc>();
				arcsParcourus = metro.getArcsBetweenStations(metro.getStationsVisitees());
				for (Arc arcs : arcsParcourus) {
					arcs.setPheromone(arc.getPheromone()+1);
				}
				metro.setEtat(Etat.rentre);
			}else{
				//On stocke le d�placement en trop sur un arc pour le r�-affecter sur le nouvel arc
				int residuTemps = metro.tempsTrajetArc - metro.currentArcSize;
				metro.setCurrentArcSize(residuTemps);
				
				// On envoi le metro vers une nouvelle station
				prochaineStation = metro.findNextSearchStation(metro.getStationCurrent());
				metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setStationDestination(prochaineStation);
				arc = metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setTempsTrajetArc(arc.gettempsParcours());
				metro.nbStationVisitees++;
				metro.currentArcSize += tempsIteration;
			}			
		}else{
			arc = metro.findArcByStationId(metro.getStationCurrent(), metro.getStationDestination());
			metro.currentArcSize += tempsIteration;
		}
		

		//AFFICHAGE
		System.out.println("Etat du m�tro : " + metro.getEtat());
		System.out.println("Position sur l'arc : " + metro.getCurrentArcSize()+" sur "+ metro.getTempsTrajetArc());
		System.out.println("Nombre de ph�romones : " + arc.getPheromone());
		System.out.println("Derni�re station visit�e : " + metro.getStationCurrent().getNom());
		System.out.println("Nombre de stations visit�es : " + metro.getNbStationVisitees());
		System.out.println("Prochaine destination de la station : " + metro.getStationDestination().getNom());
		System.out.println("Point de d�part : " + metro.getStationOrigin().getNom());
		System.out.println("Destination finale : " + metro.getStationDestinationFinal().getNom());
		System.out.println("\n---------------------------------------\n");

		try {
			Thread.sleep(tempsEntreIteration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void evaporation(){
	
		for (Arc key : ArcController.ListeArc)
		{
			if(key.getDepart() != 0)
			{
				double phe = key.getPheromone();
				phe = phe * (1 - tauxEvaporation);
				phe = Math.floor(10 * phe)/10;
				key.setPheromone(phe);
			}
		}
	}
	
	public static void demarrerMetro(Metro metro, Arc arc){
		metro.setEtat(Etat.cherche);
		metro.setCurrentArcSize(tempsIteration);
	}
	
	
	public static void suprMetro(Metro metro){
			metro = null;
	}
}
