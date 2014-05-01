package Interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import Model.Arc;
import Model.Metro;
import Model.Station;

public interface MetroInterface {

	//DECLARE FUNCTIONS
	HashSet<Arc> getArcsBetweenStations(HashMap<Integer, Station>  stations);
	Station findNextSearchStation(Metro metro);
	Arc findArcByStationId(Station current, Station next);
	static final ArrayList<Metro> ListeMetro = new ArrayList<Metro>();
}
