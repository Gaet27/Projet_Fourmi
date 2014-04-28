package Controller;

import java.util.HashSet;

import Interfaces.StationInterface;
import Model.Arc;
import Model.Station;


public class StationController extends Station implements StationInterface{

	
	//IMPLEMENTS FUNTIONS
	public StationController() {
		super();
	}
	
	//CREER LES ARCS CORRESPONDANT ET IMPLEMENTE LA LISTE DES ARCS DE LA CLASS STATION
	public void lierStation(Station arrivee, int tempsParcours){
		//INITIALISATION DES PHEROMONES POUR CHAQUE ARC
		int pheromone = 0;
		Arc arc = new ArcController(this.getNom(), arrivee.getNom(), tempsParcours, pheromone);
		this.setArcStation(arc);
		//arrivee.setArcStation(arc);
	}
	
	
	//RECUPERE LA LISTE DES ARCS EN FONCTIONS DE L'ID DE LA	STATION
	public HashSet<Arc> getArcStationId(int id) {
		for (Station key : Station.ListeStation)
		{
			if(id == key.getId()){
				ArcStation = key.getArcStation();
			}
		}
		return ArcStation;
	}
	
	public Station getStationId(String id) {
		Station station = new StationController();
		for (Station key : Station.ListeStation)
		{
			if(id == key.getNom()){
				station = key;
			}
		}
		return station;
	}
	
}
