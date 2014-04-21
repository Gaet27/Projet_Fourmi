package Model;

public class Arc {
	int idSommet1;
	int idSommet2;
	int valeurArc;
	
	float minPh;
	float maxPh;
	float tauxEvaporation;
	
	public Arc(int idSommet1, int idSommet2, int valeurArc, float minPh, float maxPh, float tauxEvaporation) {
		this.idSommet1 = idSommet1;
		this.idSommet2 = idSommet2;
		this.valeurArc = valeurArc;
		this.minPh = minPh;
		this.maxPh = maxPh;
		this.tauxEvaporation = tauxEvaporation;
	}

	public float getMinPh() {
		return minPh;
	}
	public void setMinPh(float minPh) {
		this.minPh = minPh;
	}
	public float getMaxPh() {
		return maxPh;
	}
	public void setMaxPh(float maxPh) {
		this.maxPh = maxPh;
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
