package Iterations;
import java.util.HashSet;

import Controller.*;
import Interfaces.StationInterface;
import Model.*;
import Model.Metro.Etat;


public class Main {

	final static int nbMetro = 500;
	final static int nbIterations = 200;
	final static double tauxEvaporation = 0.02;
	final static int tempsIteration = 10;         //En secondes
	final static int stationDeDepart = 1;
	final static int stationDeDestination = 4;
	
	
	public static void main(String[] args) {
		
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
		station1.lierStation(station3, 42);
		station2.lierStation(station4, 38);
		station3.lierStation(station4, 48);
		
		//AFFICHAGE DES STATIONS
		affichageStation(station1, station2, station3, station4);

		
		//ITERATIONS
		int declencheEvaporation = 0;
		for (int i = 1; i <= nbIterations; i++) {
			
			Metro metro = new MetroController();
			
			//EFFECTUE L EVAPORATION DES PHE TOUTES LES 20 ITERATIONS
			declencheEvaporation++;
			if(declencheEvaporation == 20)
			{
				for (Arc key : Arc.ListeArc )
				{
					double phe = key.getPheromone();
					phe = phe * (1 - tauxEvaporation);
					key.setPheromone(Math.round(phe*100.0)/100.0);
					System.out.println(key.getDepart()+" : " + key.getArrivee());
					System.out.println("Nouveau taux : "+key.getPheromone());
					System.out.println("\n---------------------------------------\n");
				}
				declencheEvaporation = 0;
			}
			
			//POUR CHAQUE METRO EXISTANT FAIRE
			
			//CREATIONS DE NOUVEAUX METROS
			for (int j = 1; j <= nbMetro / nbIterations; j++)
			{
//				metro.setstationDestination(stationDeDestination);
//				metro.setstationOrigin(stationDeDepart);
//				metro.setEtat(Etat.premierNoeud);
//				metro.setNbStationVisitees(0);
//				for (Station key : Station.ListeStation)
//				{
//					metro.setStationsAVisitees(key);
//				}
//				metro.setStationsVisitees(null);
//				
//				//A FINIR !!
//				StationController sc = new StationController();
//				sc.getArcStationId(stationDeDepart);
//				
//				//CREATION DE LA DESTINATION DU METRO
//				
//				metro.setCurrentArcSize(0);
//				metro.settempsTrajetArc(0);
			}
			
			switch (metro.etat) {
			
			case premierNoeud:
				break;
				
			case cherche:
				
				metro.currentArcSize += 10;
				if (metro.currentArcSize >= metro.tempsTrajetArc) 
				{
					metro.stationCurrent = metro.stationDestination;
					
					// On envoi le metro vers une nouvelle station
					Station prochaineStation = metro.findNextSearchStation(metro.getStationCurrent());
					metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
					metro.stationDestination = prochaineStation;
				}
			
				
				if (metro.stationDestinationFinal == metro.stationCurrent)
				{
					HashSet<Arc> arcsParcourus = new HashSet<Arc>();
					arcsParcourus = metro.getArcsBetweenStations(metro.getStationsVisitees());
					for (Arc arc : arcsParcourus) {
						arc.setPheromone(arc.getPheromone()+1);
					}
					metro.etat = Etat.rentre;
				}
				
				break;
				
			case rentre:
				break;
			}
		}
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

}
