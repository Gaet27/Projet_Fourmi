package Interfaces;

import java.util.HashSet;

import Model.Arc;
import Model.Station;

public interface MetroInterface {

	//DECLARE FUNCTIONS
	void trouverProchaineStation();
	void evoluer();
	void deposerPheromone();
	HashSet<Arc> getArcsBetweenStations(HashSet<Station> stations);
	Station findNextSearchStation(Station currentStation);
	Arc findArcByStationId(Station current, Station next);
}
