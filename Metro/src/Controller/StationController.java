package Controller;

import java.util.ArrayList;

import Model.Arc;
import Model.Station;

public class StationController extends Station{

	public StationController(int id, String nom,
			ArrayList<Arc> arcStation, int nbArcs) {
		super(id, nom, arcStation);
		// TODO Auto-generated constructor stub
	}
	
	
	public void lierStation(){
		//cr�er un arc entre les stations
		//Ajouter l'arc cr�er dans les listes des deux sommets (A et B)
		//avec .add()
	}
	
}
