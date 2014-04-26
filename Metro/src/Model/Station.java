package Model;

import java.util.ArrayList;
import Controller.StationController;

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
	
	public void lierStation(Station arrivee){
		Arc arc = new Arc(this, arrivee);
		this.getArcStation().add(arc);
		arrivee.getArcStation().add(arc);
	}
}
