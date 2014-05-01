package Iterations;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

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
		
		Station stationDepart = new StationController();
		Station prochaineStation = new StationController();
		Metro metro = new MetroController();
		Arc arc = new ArcController();
		
		//CREATION DES STATIONS ET DES ARCS
		//
		creerArcs();
		creerStations();
		//creationStation();

		//ITERATIONS
		for (int i = 1; i <= nbIterations; i++) {
			
			//CREER LES NOUVEAUX METROS
			premierNoeud(metro, stationDepart, prochaineStation, arc);
			
			//EFFECTUE L EVAPORATION DES PHE TOUTES LES 20 ITERATIONS
			evaporation();
				
			switch (metro.etat) {
			
			case premierNoeud:
				demarrerMetro(metro);
				break;
				
			case cherche:
				cherche(metro, stationDepart, prochaineStation, arc);
				break;
				
			case rentre:
				break;
			}
		}
	}
	
	
	//FONCTIONS DU MAIN
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
//			Thread.sleep(600);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	public static void premierNoeud(Metro metro, Station stationDepart, Station prochaineStation, Arc arc){
		
		//CREATIONS DE NOUVEAUX METROS
		for (int j = 1; j <= nbMetro / nbIterations; j++)
		{
			stationDepart = stationDepart.getStationId(stationDeDepart);
			for (Station key : StationController.ListeStation)
			{
				if(key.getNom() != stationDeDepart)
				metro.setStationsAVisitees(key);
			}
			metro.setStationsVisitees(stationDepart);//
			metro.setStationOrigin(stationDepart);
			metro.setStationCurrent(stationDepart);//
			prochaineStation = metro.findNextSearchStation(stationDepart);
			arc = metro.findArcByStationId(stationDepart, prochaineStation);
			metro.setTempsTrajetArc(arc.gettempsParcours());
			metro.setStationDestination(prochaineStation);//
			
			//L'OBJET prochaineStation EST MODIFIE POUR QUE METRO OBTIENNE SA DESTINATION FINALE
			prochaineStation = prochaineStation.getStationId(stationDeDestinationFinale);
			metro.setStationDestinationFinal(prochaineStation);
			
			metro.setEtat(Etat.premierNoeud);
			metro.setNbStationVisitees(1);
			
			
			//AFFICHAGE
			System.out.println("Position sur l'arc : " + metro.getCurrentArcSize()+" sur "+ metro.getTempsTrajetArc());
			System.out.println("Dernière station visitée : " + metro.getStationCurrent().getNom());
			System.out.println("Nombre de stations visitées : " + metro.getNbStationVisitees());
			System.out.println("Prochaine destination de la station : " + metro.getStationDestination().getNom());
			System.out.println("Point de départ : " + metro.getStationOrigin().getNom());
			System.out.println("Destination finale : " + metro.getStationDestinationFinal().getNom());
			System.out.println("\n---------------------------------------\n");
		}
	}
	
	
	
	public static void cherche(Metro metro, Station stationDepart, Station prochaineStation, Arc arc){
		
		
		if (metro.currentArcSize >= metro.tempsTrajetArc) 
		{
			metro.setStationsVisitees(metro.stationCurrent);
			metro.stationsAVisitees.remove(metro.stationCurrent);
			metro.stationCurrent = metro.stationDestination;
			if (metro.stationCurrent == metro.stationDestinationFinal)
			{
				HashSet<Arc> arcsParcourus = new HashSet<Arc>();
				arcsParcourus = metro.getArcsBetweenStations(metro.getStationsVisitees());
				for (Arc arcs : arcsParcourus) {
					arcs.setPheromone(arc.getPheromone()+1);
				}
				metro.setEtat(Etat.rentre);
			}else{
				//On stocke le déplacement en trop sur un arc pour le ré-affecter sur le nouvel arc
				int residuTemps = metro.tempsTrajetArc - metro.currentArcSize;
				metro.setCurrentArcSize(residuTemps);
				
				// On envoi le metro vers une nouvelle station
				prochaineStation = metro.findNextSearchStation(metro.getStationCurrent());
				metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setStationDestination(prochaineStation);
				arc = metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
				metro.setTempsTrajetArc(arc.gettempsParcours());
				metro.nbStationVisitees++;
				metro.currentArcSize += 10;
			}			
		}else{
			arc = metro.findArcByStationId(metro.getStationCurrent(), prochaineStation);
			metro.currentArcSize += 10;
		}
		
		//AFFICHAGE
		System.out.println("Position sur l'arc : " + metro.getCurrentArcSize()+" sur "+ metro.getTempsTrajetArc());
		System.out.println("Nombre de phéromones : " + arc.getPheromone());
		System.out.println("Dernière station visitée : " + metro.getStationCurrent().getNom());
		System.out.println("Nombre de stations visitées : " + metro.getNbStationVisitees());
		System.out.println("Prochaine destination de la station : " + metro.getStationDestination().getNom());
		System.out.println("Point de départ : " + metro.getStationOrigin().getNom());
		System.out.println("Destination finale : " + metro.getStationDestinationFinal().getNom());
		System.out.println("\n---------------------------------------\n");
		
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void evaporation(){
	
		for (Arc key : ArcController.ListeArc)
		{
			if(key.getDepart() != null)
			{
				double phe = key.getPheromone();
				phe = phe * (1 - tauxEvaporation);
				phe = Math.floor(10 * phe)/10;
				key.setPheromone(phe);
				System.out.println(key.getDepart()+" : " + key.getArrivee());
				System.out.println("Nouveau taux : "+key.getPheromone());
				System.out.println("\n---------------------------------------\n");
			}
		}
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void demarrerMetro(Metro metro){
		metro.setEtat(Etat.cherche);
		metro.setCurrentArcSize(10);
	}
	
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
				String str[] =ligne.split("" + "");
				depart = Integer.parseInt(ligne.substring(0, 1));
				arrivee = Integer.parseInt(ligne.substring(2, 5));
				tempsParcours = Integer.parseInt(ligne.substring(6, 8));
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
	
	public void liaisons(){
		for (Station key : StationController.ListeStation){
			int id = key.getId();
		}
	}
}
