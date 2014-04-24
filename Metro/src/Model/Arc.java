package Model;

public class Arc {
	int id;
	int idSommet1;
	int idSommet2;
	int valeurArc;
	
	int pheromone;
	float tauxEvaporation;
	
	public Arc(int id, int idSommet1, int idSommet2, int valeurArc, int pheromone, float tauxEvaporation) {
		this.id = id;
		this.idSommet1 = idSommet1;
		this.idSommet2 = idSommet2;
		this.valeurArc = valeurArc;
		this.pheromone = pheromone;
		this.tauxEvaporation = tauxEvaporation;
	}
	
	public int getPheromone() {
		return pheromone;
	}
	public void setPheromone(int pheromone) {
		this.pheromone = pheromone;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
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
	public int getValeurArc() {
		return valeurArc;
	}
	public void setValeurArc(int valeurArc) {
		this.valeurArc = valeurArc;
	}
}
