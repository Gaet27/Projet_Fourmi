package Controller;

import Model.Arc;
import Model.Station;

public class StationController extends Station{

	public void lierStation(Station arrivee){
		Arc arc = new Arc(this, arrivee);
		this.getArcStation().add(arc);
		arrivee.getArcStation().add(arc);
	}
	
}
