package Model;


import java.util.HashSet;

import Interfaces.StationInterface;

public abstract class Station implements StationInterface{
	
	
	//ATRIBUTS
	public int id;
	public String nom;
	public HashSet<Arc> ArcStation;  //HASHSET NE PERMET PAS LES DOUCBLONS
	
	
	//CONSTRUCTOR
	public Station() {

	}

	
	//GETTERS AND SETTERS
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
	public HashSet<Arc> getArcStation() {
		return ArcStation;
	}
	public void setArcStation(HashSet<Arc> arcStation) {
		ArcStation = arcStation;
	}
}
