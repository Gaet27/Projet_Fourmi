package Model;
import java.util.ArrayList;

import Controller.*;


public abstract class Metro implements MetroInterface{
	
	//ATTRIBUTS
	public Etat etat;//� l'arret, en d�placement, retour
	public int tempsTrajet;
	
	public enum Etat{
		premierNoeud,
		cherche,
		rentre
	}
	
	public int nbStationVisitees;
	public ArrayList<Station> stationsVisitees;
	public ArrayList<Station> stationsAVisitees;
	
	public int currentArcSize;    // longueur de l'arc actuellement parcouru
	public int currentOrigin;        // premi�re extr�mit� de l'arc actuellement parcouru 
	public int currentDestination;    // seconde extr�mit� de l'arc actuellement parcouru
	
	
	
	//CONSTRUCTOR
	public Metro(Etat etat, int tempsTrajet, int nbStationVisitees,ArrayList<Station> stationsVisitees,ArrayList<Station> stationsAVisitees, int currentArcSize, int currentOrigin, int currentDestination){
		this.etat = etat;
		this.tempsTrajet = tempsTrajet;
		this.nbStationVisitees = nbStationVisitees;
		this.stationsVisitees = stationsVisitees;
		this.stationsAVisitees = stationsAVisitees;
		this.currentArcSize = currentArcSize;
		this.currentOrigin = currentOrigin;
		this.currentDestination = currentDestination;
	}
	
	
	
	//GETTERS END SETTERS
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public int getTempsTrajet() {
		return tempsTrajet;
	}
	public void setTempsTrajet(int tempsTrajet) {
		this.tempsTrajet = tempsTrajet;
	}
	public int getNbStationVisitees() {
		return nbStationVisitees;
	}
	public void setNbStationVisitees(int nbStationVisitees) {
		this.nbStationVisitees = nbStationVisitees;
	}
	public ArrayList<Station> getStationsVisitees() {
		return stationsVisitees;
	}
	public void setStationsVisitees(ArrayList<Station> stationsVisitees) {
		this.stationsVisitees = stationsVisitees;
	}
	public ArrayList<Station> getStationsAVisitees() {
		return stationsAVisitees;
	}
	public void setStationsAVisitees(ArrayList<Station> stationsAVisitees) {
		this.stationsAVisitees = stationsAVisitees;
	}
	public long getCurrentArcSize() {
		return currentArcSize;
	}
	public void setCurrentArcSize(int currentArcSize) {
		this.currentArcSize = currentArcSize;
	}
	public int getCurrentOrigin() {
		return currentOrigin;
	}
	public void setCurrentOrigin(int currentOrigin) {
		this.currentOrigin = currentOrigin;
	}
	public int getCurrentDestination() {
		return currentDestination;
	}
	public void setCurrentDestination(int currentDestination) {
		this.currentDestination = currentDestination;
	}
}
