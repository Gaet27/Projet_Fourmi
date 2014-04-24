package Model;

import java.util.ArrayList;

public class Station {
	
	int id;
	String nom;
	int x;
	int y;
	ArrayList<Arc> ArcStation;
	
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ArrayList<Arc> getArcStation() {
		return ArcStation;
	}
	public void setArcStation(ArrayList<Arc> arcStation) {
		ArcStation = arcStation;
	}
}
