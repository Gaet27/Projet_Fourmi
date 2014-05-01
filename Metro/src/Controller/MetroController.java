package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import Interfaces.MetroInterface;
import Model.Arc;
import Model.Metro;
import Model.Station;



public class MetroController extends Metro implements MetroInterface {

	//IMPLEMENTS FUNTIONS
	public MetroController()
	{
		super();
	}
	
	public MetroController(Etat etat, int tempsTrajet, int nbStationVisitees,
			HashMap<Integer, Station> stationsVisitees,
			HashSet<Station> stationsAVisitees, int currentArcSize,
			Station currentOrigin, Station currentDestination) {
		super(etat, tempsTrajet, nbStationVisitees, stationsVisitees,
				stationsAVisitees, currentArcSize, currentOrigin, currentDestination);
	}
	
	
	//On récupère la liste des arcs parcouru pour pouvoir déposer les phéromones
	public HashSet<Arc> getArcsBetweenStations(HashMap<Integer, Station> stations) {
		HashSet<Arc> ListeArcBetweenStation  = new HashSet<Arc>();
		
		int nomDepart = 0;
		int nomArrivee = 0;
		int cursor = 0;
		int cursor2 = 0;

		for(Entry<Integer, Station> key : stations.entrySet()) {
		    			
		    nomDepart = key.getValue().getId();
		    cursor = key.getKey().intValue();
		    
		    for(Entry<Integer, Station> key2 : stations.entrySet()) {
		    	
		    	cursor2 = key2.getKey().intValue();
		    	
		    	if (cursor2 > cursor){
		    		nomArrivee = key2.getValue().getId();
		    		break;
		    	}
		    }   
		    
			for (Arc key2 : key.getValue().getArcStation())
			{
				if(key2.getDepart() ==  nomDepart && key2.getArrivee() == nomArrivee)
				{
					ListeArcBetweenStation.add(key2);
				}
			}
		}
		return ListeArcBetweenStation;
	}
	
	
	//On indique la prochaine station à visiter
	public Station findNextSearchStation(Station currentStation){
		ArrayList<Arc> ListeArcPheromone = new ArrayList<Arc>();
		for (Arc arcStation : currentStation.getArcStation()) {
			
			double TempsTrajet = Math.round((150-arcStation.gettempsParcours())/10); 
			int phe = 0;
			if (arcStation.getPheromone() == 0)
			{
			     TempsTrajet = Math.round((150-arcStation.gettempsParcours())/70); 
				 phe = 1;
			}
			double txPheromone = (arcStation.getPheromone()*30)+phe;
			
			for (int i = 0; i < TempsTrajet+txPheromone ; i++) {
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
			if (key.getDepart() == current.getId() && key.getArrivee() == next.getId()){
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
