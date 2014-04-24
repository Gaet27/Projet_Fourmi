package Model;

public class Arc {

	Station depart;
	Station arrivee;
	int valeurArc;
	int pheromone;
	double tauxEvaporation;
	int tempsParcours;
	
	public Arc(Station station1, Station station2) {
		this.depart = station1;
		this.arrivee = station2;

	}
	
	public int getPheromone() {
		return pheromone;
	}
	public void setPheromone(int pheromone) {
		this.pheromone = pheromone;
	}
	public double getTauxEvaporation() {
		return tauxEvaporation;
	}
	public void setTauxEvaporation(double tauxEvaporation) {
		this.tauxEvaporation = tauxEvaporation;
	}
	public Station getDepart() {
		return depart;
	}
	public void setDepart(Station depart) {
		this.depart = depart;
	}
	public Station getArrivee() {
		return arrivee;
	}
	public void setArrivee(Station arrivee) {
		this.arrivee = arrivee;
	}
	public int gettempsParcours() {
		return tempsParcours;
	}
	public void settempsParcours(int tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
}
