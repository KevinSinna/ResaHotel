package model;


import java.util.*;

/**
 * 
 */
public class Sejour {

    /**
     * Default constructor
     */
    public Sejour() {
    }

    /**
     * 
     */
    private Set<Produit> TabConso;
    public Reservation Res;


    /**
     * 
     */
    private Set<Reservation> TabChambre;

    /**
     * 
     */
    public void Facturation() {
        // TODO implement here
    }

    /**
     * 
     */
    public void Stats() {
        // TODO implement here
    }

	public Set<Reservation> getTabChambre() {
		return TabChambre;
	}

	public void setTabChambre(Set<Reservation> tabChambre) {
		TabChambre = tabChambre;
	}

	public Set<Produit> getTabConso() {
		return TabConso;
	}

	public void setTabConso(Set<Produit> tabConso) {
		TabConso = tabConso;
	}

}