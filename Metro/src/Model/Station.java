package Model;

import java.util.ArrayList;

public class Station {
	
	public int id;
	public String nom;
	public ArrayList<Arc> ArcStation;
	

	public Station() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Arc> getArcStation() {
		return ArcStation;
	}
	public void setArcStation(ArrayList<Arc> arcStation) {
		ArcStation = arcStation;
	}
}
