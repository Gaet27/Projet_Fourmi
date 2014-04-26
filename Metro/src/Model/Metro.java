package Model;
import java.util.ArrayList;
import java.util.HashSet;

import Interfaces.MetroInterface;


public abstract class Metro implements MetroInterface{
	
	//ATTRIBUTS
	public Etat etat;
	public int tempsTrajet;
	
	public enum Etat{
		premierNoeud,
		cherche,
		rentre
	}
	
	public int nbStationVisitees;
	public HashSet<Station> stationsVisitees = new HashSet<Station>();
	public HashSet<Station> stationsAVisitees = new HashSet<Station>();
	
	public int currentArcSize;           // Sa position en temps sur l'arc par rapport au temps du trajet
	public int stationOrigin;            // Point de départ
	public int stationCurrent;	         // Dernière station atteinte
	public int stationDestination;       // Prochaine station à atteindre
	public int stationDestinationFinal;  // Destination finale
	
	
	
	//CONSTRUCTOR
	public Metro(){
	}
	
	public Metro(Etat etat, int tempsTrajet, int nbStationVisitees,HashSet<Station> stationsVisitees,HashSet<Station> stationsAVisitees, int currentArcSize, int stationOrigin, int stationDestination){
		this.etat = etat;
		this.tempsTrajet = tempsTrajet;
		this.nbStationVisitees = nbStationVisitees;
		this.stationsVisitees = stationsVisitees;
		this.stationsAVisitees = stationsAVisitees;
		this.currentArcSize = currentArcSize;
		this.stationOrigin = stationOrigin;
		this.stationDestination = stationDestination;
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
	public HashSet<Station> getStationsVisitees() {
		return stationsVisitees;
	}
	public void setStationsVisitees(Station stationsVisitees) {
		this.stationsVisitees.add(stationsVisitees);
	}
	public HashSet<Station> getStationsAVisitees() {
		return stationsAVisitees;
	}
	public void setStationsAVisitees(Station stationsAVisitees) {
		this.stationsAVisitees.add(stationsAVisitees);
	}
	public long getCurrentArcSize() {
		return currentArcSize;
	}
	public void setCurrentArcSize(int currentArcSize) {
		this.currentArcSize = currentArcSize;
	}
	public int getstationOrigin() {
		return stationOrigin;
	}
	public void setstationOrigin(int stationOrigin) {
		this.stationOrigin = stationOrigin;
	}
	public int getstationDestination() {
		return stationDestination;
	}
	public void setstationDestination(int stationDestination) {
		this.stationDestination = stationDestination;
	}
	public int getStationCurrent() {
		return stationCurrent;
	}
	public void setStationCurrent(int stationCurrent) {
		this.stationCurrent = stationCurrent;
	}
	public int getStationDestinationFinal() {
		return stationDestinationFinal;
	}
	public void setStationDestinationFinal(int stationDestinationFinal) {
		this.stationDestinationFinal = stationDestinationFinal;
	}



	//LISTE DE TOUTES LES INSTANCES DE CETTE CLASSE
	public static final ArrayList<Metro> ListeMetro = new ArrayList<Metro>();
	{
		Metro.ListeMetro.add(this);
	}
}
