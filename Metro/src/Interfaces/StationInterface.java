package Interfaces;

import java.util.HashSet;

import Model.Arc;
import Model.Station;

public interface StationInterface {

	//DECLARE FUNCTIONS
	void lierStation(Station arrivee, int tempsParcours);
	HashSet<Arc> getArcStationId(int id);
<<<<<<< HEAD
	Station getStationId(String id);
=======
	HashSet<Arc> getArcsBetweenStations(HashSet<Station> stations);
>>>>>>> b9e0dbe114820f4143a4ba74268c311ab941088c
}
