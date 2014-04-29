package Iterations;
import java.util.HashSet;

import Controller.*;
import Model.*;
import Model.Metro.Etat;


public class Main {

	final static int nbMetro = 500;
	final static int nbIterations = 200;
	final static double tauxEvaporation = 0.02;
	final static int tempsIteration = 10;         //En secondes
	final static String stationDeDepart = "Anvers";
	final static String stationDeDestinationFinale = "Mirabeau";
	
	
	public static void main(String[] args) {
		
		Station station = new StationController();
		Metro metro = new MetroController();
		
		//CREATION DES STATIONS ET DES ARCS
		creationStation();

		//ITERATIONS
		for (int i = 1; i <= nbIterations; i++) {
			
			//EFFECTUE L EVAPORATION DES PHE TOUTES LES 20 ITERATIONS
			evaporation();
			
			//CREER LES NOUVEAUX METROS
			premierNoeud(metro, station);
				
			switch (metro.etat) {
			
			case premierNoeud:
				break;
				
			case cherche:
				cherche(metro, station);
				break;
				
			case rentre:
				break;
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
		station2.nom = "Liberté";
		
		Station station3 = new StationController();
		station3.id = 3;
		station3.nom = "Madeleine";
		
		Station station4 = new StationController();
		station4.id = 4;
		station4.nom = "Mirabeau";
		
		//LIAISONS DES 4 STATIONS
		station1.lierStation(station2, 35);
		station1.lierStation(station3, 42);
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
	}
	
	
	
	public static void premierNoeud(Metro metro, Station station){
		
		//CREATIONS DE NOUVEAUX METROS
		for (int j = 1; j <= nbMetro / nbIterations; j++)
		{
			station = station.getStationId(stationDeDepart);
			for (Station key : StationController.ListeStation)
			{
				if(key.getNom() != stationDeDepart)
				metro.setStationsAVisitees(key);
			}
			metro.setStationsVisitees(station);
			metro.setStationOrigin(station);
			metro.setStationCurrent(station);
			station = metro.findNextSearchStation(station);
			metro.setStationDestination(station);
			station = station.getStationId(stationDeDestinationFinale);
			metro.setStationDestinationFinal(station);
			metro.setEtat(Etat.premierNoeud);
			metro.setTempsTrajetArc(45);//A completer
			metro.setNbStationVisitees(1);
			metro.setCurrentArcSize(10);
			
			//AFFICHAGE
			System.out.println("Position sur l'arc : " + metro.getCurrentArcSize()+" sur "+ metro.getTempsTrajetArc());
			System.out.println("Dernière station visitée : " + metro.getStationCurrent().getNom());
			System.out.println("Nombre de stations visitées : " + metro.getNbStationVisitees());
			System.out.println("Prochaine destination de la station : " + metro.getStationDestination().getNom());
			System.out.println("Point de départ :" + metro.getStationOrigin().getNom());
			System.out.println("Destination finale : " + metro.getStationDestinationFinal().getNom());
			System.out.println("\n---------------------------------------\n");
		}
	}
	
	
	
	public static void cherche(Metro metro, Station station){
		
		if (metro.currentArcSize >= metro.tempsTrajetArc) 
		{
			metro.setStationsVisitees(metro.stationCurrent);
			metro.stationsAVisitees.remove(metro.stationCurrent);
			metro.stationCurrent = metro.stationDestination;
			if (metro.stationCurrent == metro.stationDestinationFinal)
			{
				HashSet<Arc> arcsParcourus = new HashSet<Arc>();
				arcsParcourus = metro.getArcsBetweenStations(metro.getStationsVisitees());
				for (Arc arc : arcsParcourus) {
					arc.setPheromone(arc.getPheromone()+1);
				}
				metro.setEtat(Etat.rentre);
			}else{
				//On stocke le déplacement en trop sur un arc pour le ré-affecter sur le nouvel arc
				int residuTemps = metro.tempsTrajetArc - metro.currentArcSize;
				metro.setCurrentArcSize(residuTemps);
				
				// On envoi le metro vers une nouvelle station
				Station prochaineStation = metro.findNextSearchStation(metro.getStationCurrent());
				metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setStationDestination(prochaineStation);
			}			
		}else{
			metro.currentArcSize += 10;
		}
		
		
	}
	
	
	
	public static void evaporation(){
	
		for (Arc key : Arc.ListeArc )
		{
			double phe = key.getPheromone();
			phe = phe * (1 - tauxEvaporation);
			phe = Math.rint(10 * phe)/10;
			key.setPheromone(phe);
			System.out.println(key.getDepart()+" : " + key.getArrivee());
			System.out.println("Nouveau taux : "+key.getPheromone());
			System.out.println("\n---------------------------------------\n");
		}

	}
}
