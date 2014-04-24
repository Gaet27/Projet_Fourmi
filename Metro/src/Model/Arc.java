package Model;

public class Arc {
	Station depart;
	Station arrivee;
	int valeurArc;
	int pheromone;
	float tauxEvaporation;
	
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
	public float getTauxEvaporation() {
		return tauxEvaporation;
	}
	public void setTauxEvaporation(float tauxEvaporation) {
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
	public int getValeurArc() {
		return valeurArc;
	}
	public void setValeurArc(int valeurArc) {
		this.valeurArc = valeurArc;
	}
}
