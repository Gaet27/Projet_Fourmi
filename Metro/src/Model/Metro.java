package Model;
import java.util.ArrayList;

public class Metro {
	
	String etat; //à l'arret, en déplacement, retour
	int tempsTrajet;
	
	int nbStationVisitees;
	ArrayList<Station> stationsVisitees;
	ArrayList<Station> stationsAVisitees;
	
	long currentArcSize;    // longueur de l'arc actuellement parcouru
	long currentArcPos;    // position sur l'arc actuellement parcouru
	int currentOrigin;        // première extrémité de l'arc actuellement parcouru 
	int currentDestination;    // seconde extrémité de l'arc actuellement parcouru
	
	public Metro(String etat, int tempsTrajet, int nbStationVisitees,ArrayList<Station> stationsVisitees,ArrayList<Station> stationsAVisitees, long currentArcSize,long currentArcPos, int currentOrigin, int currentDestination){
		this.etat = etat;
		this.tempsTrajet = tempsTrajet;
		this.nbStationVisitees = nbStationVisitees;
		this.stationsVisitees = stationsVisitees;
		this.stationsAVisitees = stationsAVisitees;
		this.currentArcSize = currentArcSize;
		this.currentArcPos = currentArcPos;
		this.currentOrigin = currentOrigin;
		this.currentDestination = currentDestination;
	}
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
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
	public void setCurrentArcSize(long currentArcSize) {
		this.currentArcSize = currentArcSize;
	}
	public long getCurrentArcPos() {
		return currentArcPos;
	}
	public void setCurrentArcPos(long currentArcPos) {
		this.currentArcPos = currentArcPos;
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
