package model;

import java.util.*;

/**
 * 
 */
public class Reservation extends Observable {
	private Client idClient;
    private Date DateDeb;
    private Date DateFin;
    public ArrayList<Chambre> Chamb;
    private int idRes;
    private String Statut;

    /**
     * Default constructor
     */
    public Reservation(Client e, Date dated, Date datef, String att) {
    	idClient = e;
    	DateDeb = dated;
    	DateFin = datef;
    	Statut = att;
    }
    
    public void AjoutChambre(Chambre e) {
    	Chamb.add(e);
    }


    public void Annul() {
        // TODO implement here
    	this.Statut = "Annuler";    	
    }

    /**
     * 
     */
    public void Confime() {
        // TODO implement here
    	this.Statut = "Confirmer";
    }

}