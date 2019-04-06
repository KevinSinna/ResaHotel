package model;

import java.util.*;

/**
 * 
 */
public class Reservation {
	private Client idClient;
    private Date DateDeb;
    private Date DateFin;
    public ArrayList<Chambre> Chamb;
    private int idRes;
    private String Statut;

    /**
     * Default constructor
     */
    public Reservation() {
    }


    public void Annul() {
        // TODO implement here
    }

    /**
     * 
     */
    public void Confime() {
        // TODO implement here
    }

}