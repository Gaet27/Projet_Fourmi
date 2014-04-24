package Model;

public class Arc {
	int id;
	int idSommet1;
	int idSommet2;
	int tempsParcours;
	
	int pheromone;
	float tauxEvaporation;
	
	public Arc(int idSommet1, int idSommet2, int tempsParcours, int pheromone, float tauxEvaporation) {
		this.idSommet1 = idSommet1;
		this.idSommet2 = idSommet2;
		this.tempsParcours = tempsParcours;
		this.pheromone = pheromone;
		this.tauxEvaporation = tauxEvaporation;
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
	public int getIdSommet1() {
		return idSommet1;
	}
	public void setIdSommet1(int idSommet1) {
		this.idSommet1 = idSommet1;
	}
	public int getIdSommet2() {
		return idSommet2;
	}
	public void setIdSommet2(int idSommet2) {
		this.idSommet2 = idSommet2;
	}
	public int gettempsParcours() {
		return tempsParcours;
	}
	public void settempsParcours(int tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
}
