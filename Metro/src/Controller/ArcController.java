package Controller;

import java.util.ArrayList;

import Interfaces.ArcInterface;
import Model.*;


public class ArcController extends Arc implements ArcInterface{

	//IMPLEMENTS FUNTIONS
	public ArcController(String station1, String station2, int tempsParcours, int pheromone) {
		super(station1, station2, tempsParcours, pheromone);
	}
	
	public ArcController(){
		super();
	}
	
	
	//LISTE DE TOUTES LES INSTANCES DE CETTE CLASSE
	public static final ArrayList<Arc> ListeArc = new ArrayList<Arc>();
	{
		ListeArc.add(this);
	}
}
