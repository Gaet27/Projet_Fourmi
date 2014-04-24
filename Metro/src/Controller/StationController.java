package Controller;

import java.util.ArrayList;

import Model.Arc;
import Model.Station;

public class StationController extends Station{

	public StationController(int id, String nom, int x, int y,
			ArrayList<Arc> arcStation, int nbArcs) {
		super(id, nom, x, y, arcStation);
		// TODO Auto-generated constructor stub
	}
	
	
	public void lierStation(){
		//créer un arc entre les stations
		//Ajouter l'arc créer dans les listes des deux sommets (A et B)
		//avec .add()
	}
	
}
