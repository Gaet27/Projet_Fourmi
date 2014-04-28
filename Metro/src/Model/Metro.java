package Model;
import java.util.ArrayList;
import java.util.HashSet;

import Interfaces.MetroInterface;


public abstract class Metro implements MetroInterface{
	
	//ATTRIBUTS
	public Etat etat;
	public int tempsTrajetArc;
	
	public enum Etat{
		premierNoeud,
		cherche,
		rentre
	}
	
	public int nbStationVisitees;
	public HashSet<Station> stationsVisitees = new HashSet<Station>();
	public HashSet<Station> stationsAVisitees = new HashSet<Station>();
	
	public int currentArcSize;               // Sa position en temps sur l'arc par rapport au temps du trajet
	public Station stationOrigin;            // Point de départ
	public Station stationCurrent;	         // Dernière station atteinte
	public Station stationDestination;       // Prochaine station à atteindre
	public Station stationDestinationFinal;  // Destination finale
	
	
	
	//CONSTRUCTOR
	public Metro(){
	}
	
	public Metro(Etat etat, int tempsTrajetArc, int nbStationVisitees,HashSet<Station> stationsVisitees,HashSet<Station> stationsAVisitees, int currentArcSize, Station stationOrigin, Station stationDestination){
		this.etat = etat;
		this.tempsTrajetArc = tempsTrajetArc;
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

	public int getTempsTrajetArc() {
		return tempsTrajetArc;
	}

	public void setTempsTrajetArc(int tempsTrajetArc) {
		this.tempsTrajetArc = tempsTrajetArc;
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

	public int getCurrentArcSize() {
		return currentArcSize;
	}

	public void setCurrentArcSize(int currentArcSize) {
		this.currentArcSize = currentArcSize;
	}

	public Station getStationOrigin() {
		return stationOrigin;
	}

	public void setStationOrigin(Station stationOrigin) {
		this.stationOrigin = stationOrigin;
	}

	public Station getStationCurrent() {
		return stationCurrent;
	}

	public void setStationCurrent(Station stationCurrent) {
		this.stationCurrent = stationCurrent;
	}

	public Station getStationDestination() {
		return stationDestination;
	}

	public void setStationDestination(Station stationDestination) {
		this.stationDestination = stationDestination;
	}

	public Station getStationDestinationFinal() {
		return stationDestinationFinal;
	}

	public void setStationDestinationFinal(Station stationDestinationFinal) {
		this.stationDestinationFinal = stationDestinationFinal;
	}
	
	
	
	//LISTE DE TOUTES LES INSTANCES DE CETTE CLASSE
	public static final ArrayList<Metro> ListeMetro = new ArrayList<Metro>();
	{
		Metro.ListeMetro.add(this);
	}
}
