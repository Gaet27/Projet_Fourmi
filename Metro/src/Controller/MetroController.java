package Controller;

import java.util.ArrayList;

import Model.*;


public class MetroController extends Metro implements MetroInterface {

	public MetroController(Etat etat, int tempsTrajet, int nbStationVisitees,
			ArrayList<Station> stationsVisitees,
			ArrayList<Station> stationsAVisitees, int currentArcSize,
			int currentOrigin, int currentDestination) {
		super(etat, tempsTrajet, nbStationVisitees, stationsVisitees,
				stationsAVisitees, currentArcSize, currentOrigin, currentDestination);
	}
	
	public void trouverProchaineStation(){
		
	}
	
	public void evoluer(){
		
	}
	
	public void deposerPheromone(){
		
	}
}
