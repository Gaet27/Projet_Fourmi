package Controller;

import java.util.HashSet;

import Interfaces.MetroInterface;
import Model.Metro;
import Model.Station;


public class MetroController extends Metro implements MetroInterface {

	//IMPLEMENTS FUNTIONS
	public MetroController()
	{
		super();
	}
	
	public MetroController(Etat etat, int tempsTrajet, int nbStationVisitees,
			HashSet<Station> stationsVisitees,
			HashSet<Station> stationsAVisitees, int currentArcSize,
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
