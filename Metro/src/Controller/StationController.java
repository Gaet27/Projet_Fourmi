package Controller;

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
		int pheromone = 3;
		Arc arc = new ArcController(this.getNom(), arrivee.getNom(), tempsParcours, pheromone);
		this.setArcStation(arc);
		arrivee.setArcStation(arc);
	}
}
