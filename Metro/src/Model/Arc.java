package Model;

import Interfaces.ArcInterface;

public abstract class Arc implements ArcInterface{

	
	//ATTRIBUTS
	public int depart;
	public int arrivee;
	public double pheromone;
	public int tempsParcours;    //En secondes
	
	
	
	//CONSTRUCTOR
	public Arc(){
		
	}
	
	public Arc(int station1, int station2, int tempsParcours, int pheromone) {
		this.depart = station1;
		this.arrivee = station2;
		this.tempsParcours = tempsParcours;
		this.pheromone = pheromone;
	}
	
	
	//GETTERS AND SETTERS
	public double getPheromone() {
		return pheromone;
	}
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
	}
	public int getDepart() {
		return depart;
	}
	public void setDepart(int depart2) {
		this.depart = depart2;
	}
	public int getArrivee() {
		return arrivee;
	}
	public void setArrivee(int arrivee2) {
		this.arrivee = arrivee2;
	}
	public int gettempsParcours() {
		return tempsParcours;
	}
	public void settempsParcours(int tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
}
