package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import Interfaces.MetroInterface;
import Model.*;
import Controller.*;



public class MetroController extends Metro implements MetroInterface {

	//IMPLEMENTS FUNTIONS
	public MetroController()
	{
		super();
	}
	
	public MetroController(Etat etat, int tempsTrajet, int nbStationVisitees,
			HashSet<Station> stationsVisitees,
			HashSet<Station> stationsAVisitees, int currentArcSize,
			Station currentOrigin, Station currentDestination) {
		super(etat, tempsTrajet, nbStationVisitees, stationsVisitees,
				stationsAVisitees, currentArcSize, currentOrigin, currentDestination);
	}
	
	public void trouverProchaineStation(){
		
	}
	
	public void evoluer(){
		
	}
	
	public void deposerPheromone(){
		
	}
	
	//On récupère la liste des arcs parcouru pour pouvoir déposer les phéromones
	public HashSet<Arc> getArcsBetweenStations(HashSet<Station> stations) {
		HashSet<Arc> ListeArcBetweenStation  = new HashSet<Arc>();

		int j = 1;
		int i = 1;
		String Station2 = null;
		
		for (Station key : stations)
		{
			//RECUPERE DE 2 EN 2
			for (Station keyy : stations){
			    Station2 = keyy.getNom();
				if(j >= i+1)
				{
					break;
				}
				j++;
			}
			i++;
			
			String Station1 = key.getNom();
		
			for (Arc key2 : key.getArcStation())
			{
				if(key2.getDepart() ==  Station1 && key2.getArrivee() == Station2)
				{
					ListeArcBetweenStation .add(key2);
				}
			}
		}
		return ListeArcBetweenStation ;
	}
	
	//On indique la prochaine station à visiter
	public Station findNextSearchStation(Station currentStation){
		ArrayList<Arc> ListeArcPheromone = new ArrayList<Arc>();
		for (Arc arcStation : currentStation.getArcStation()) {
			
			int phe = 0;
			if (arcStation.getPheromone() == 0)
			{
				 phe = 1;
			}
			
			for (int i = 0; i < (arcStation.getPheromone()*10)+phe; i++) {
				ListeArcPheromone.add(arcStation);
			}			
		}
		int nombreAleatoire = (int)(Math.random() * (ListeArcPheromone.size() - 0)) + 0;
		Arc arc = ListeArcPheromone.get(nombreAleatoire);
		
		Station prochaineStation = new StationController();
		prochaineStation = prochaineStation.getStationId(arc.getArrivee());
		
		return prochaineStation;
	}
	
	//On retrouve un arc à partir de l'id de deux stations
	public Arc findArcByStationId(Station current, Station next){
		Arc arc = new ArcController();
		for (Arc key : current.getArcStation())
		{
			if (key.getDepart()== current.getNom() && key.getArrivee() == next.getNom()){
				arc = key;
			}
		}
		return arc;
	}
	
	
	//LISTE DE TOUTES LES INSTANCES DE CETTE CLASSE
	public static final ArrayList<Metro> ListeMetro = new ArrayList<Metro>();
	{
		ListeMetro.add(this);
	}
	
}
