package Model;

import java.util.ArrayList;

import Interfaces.*;

public abstract class Arc implements ArcInterface{

	
	//ATTRIBUTS
	public String depart;
	public String arrivee;
	public double pheromone;
	public int tempsParcours;    //En secondes
	
	
	
	//CONSTRUCTOR
	public Arc(){
		
	}
	
	public Arc(String station1, String station2, int tempsParcours, int pheromone) {
		this.depart = station1;
		this.arrivee = station2;
		this.tempsParcours = tempsParcours;
		this.pheromone = pheromone;
	}
	
	public Arc(){
		
	}
	
	
	//GETTERS AND SETTERS
	public double getPheromone() {
		return pheromone;
	}
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getArrivee() {
		return arrivee;
	}
	public void setArrivee(String arrivee) {
		this.arrivee = arrivee;
	}
	public int gettempsParcours() {
		return tempsParcours;
	}
	public void settempsParcours(int tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
	
	
	//LISTE DE TOUTES LES INSTANCES DE CETTE CLASSE
	public static final ArrayList<Arc> ListeArc = new ArrayList<Arc>();
	{
		Arc.ListeArc.add(this);
	}
}
