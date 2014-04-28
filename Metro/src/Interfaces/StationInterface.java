package Interfaces;

import java.util.HashSet;

import Model.Arc;
import Model.Station;

public interface StationInterface {

	//DECLARE FUNCTIONS
	void lierStation(Station arrivee, int tempsParcours);
	HashSet<Arc> getArcStationId(int id);
	Station getStationId(String id);
}
