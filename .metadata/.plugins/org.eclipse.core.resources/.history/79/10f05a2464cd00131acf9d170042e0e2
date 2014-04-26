package Model;

import Interfaces.*;

public abstract class Arc implements ArcInterface{

	
	//ATTRIBUTS
	public String depart;
	public String arrivee;
	public int pheromone;
	public int tempsParcours;
	
	
	
	//CONSTRUCTOR
	public Arc(String station1, String station2, int tempsParcours, int pheromone) {
		this.depart = station1;
		this.arrivee = station2;
		this.tempsParcours = tempsParcours;
		this.pheromone = pheromone;
	}
	
	
	
	//GETTERS AND SETTERS
	public int getPheromone() {
		return pheromone;
	}
	public void setPheromone(int pheromone) {
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
}
