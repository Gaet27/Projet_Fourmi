package Interfaces;

import java.util.ArrayList;
import java.util.HashSet;

import Model.Arc;
import Model.Station;

public interface StationInterface {

	//DECLARE FUNCTIONS
	void lier(Station station);
	HashSet<Arc> getArcStationId(int id);
	Station getStationId(int id);
	static final ArrayList<Station> ListeStation = new ArrayList<Station>();
}
