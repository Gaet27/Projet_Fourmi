package Controller;

import interfaces.*;
import Model.*;


public class StationController extends Station implements StationInterface{

	
	//IMPLEMENTS FUNTIONS
	public StationController() {
		super();
	}
	
	//CREER LES ARCS CORRESPONDANT ET IMPLEMENTE LA LISTE DES ARCS DE LA CLASS STATION
	public void lierStation(Station arrivee){
		Arc arc = new ArcController(this, arrivee);
		this.getArcStation().add(arc);
		arrivee.getArcStation().add(arc);
	}
	
}
