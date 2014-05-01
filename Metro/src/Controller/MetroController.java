package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

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
		
		//ON PARCOURS LA LISTE DES ARCS QUE LA STATION A VISITE
		for(Entry<Integer, Station> key : stations.entrySet()) {
		    			
		    nomDepart = key.getValue().getId();
		    cursor = key.getKey().intValue();
		    
		    for(Entry<Integer, Station> key2 : stations.entrySet()) {
		    	
		    	cursor2 = key2.getKey().intValue();
		    	//APRES AVOIR RECUPERER L'ID DE LA PREMIERE STATION ON RECUPERE l'ID DE LA STATION SUIVANTE
		    	if (cursor2 > cursor){
		    		nomArrivee = key2.getValue().getId();
		    		break;
		    	}
		    }   
		    
			for (Arc key2 : key.getValue().getArcStation())
			{
				//ENSUITE SI L'ARC CONTIENT L'ID DE LA PREMIERE STATION DANS SON ID DE DEPART ET L'ID DE LA 
				//DEUXIEME STATION DANS SON ID D'ARRIVEE ALORS ON L'AJOUTE DANS LA LISTE RETOURNEE
				if(key2.getDepart() ==  nomDepart && key2.getArrivee() == nomArrivee)
				{
					ListeArcBetweenStation.add(key2);
				}
			}
		}
		return ListeArcBetweenStation;
	}
	
	
	//On indique la prochaine station à visiter
	public Station findNextSearchStation(Metro metro){
		
		ArrayList<Arc> ListeArcPheromone = new ArrayList<Arc>();
		Station prochaineStation = new StationController();
		
		//ON PARCOURS LA LISTE DES ARCS DU METRO PASSE EN PARAMETRE
		for (Arc arcStation : metro.getStationCurrent().getArcStation()) {
			
			double TempsTrajet = Math.round((150-arcStation.gettempsParcours())/10); 
			int phe = 0;
			if (arcStation.getPheromone() == 0)
			{
			     TempsTrajet = Math.round((150-arcStation.gettempsParcours())/70); 
				 phe = 1;
			}
			double txPheromone = (arcStation.getPheromone()*30)+phe;
			
			//EN FONCTION DU TEMPS DE TRAJET ET DU NOMBRE DE PHEROMONE, PLUS LE NOMBRE DE PHEROMONE
			//EST IMPORTANT ET PLUS LE TEMPS DE TRAJET EST COURT SUR L'ARC, PLUS IL AURA DE CASE DANS LE TABLEAU
			for (int i = 0; i < TempsTrajet+txPheromone ; i++) {
				ListeArcPheromone.add(arcStation);
			}			
		}
			//ON TIRE UN NOMBRE ALEATOIRE ENTRE 1 ET LA TAILLE DU TABLEAU
			int nombreAleatoire = (int)(Math.random() * (ListeArcPheromone.size() - 0)) + 0;
			//L'ARC QUE L'ON RECUPERE EST EGAL A L'INDEX DU NOMBRE ALEATOIRE
			Arc arc = ListeArcPheromone.get(nombreAleatoire);
			//ON RECUPERE LA PROCHAINE STATION EN PASSANT l'ID DE l'ARRIVE DE L'ARC DONC l'ID DE LA STATION DE DESTINATION
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
