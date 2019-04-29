package model;

import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Reservation extends Observable {
	private Client idClient;
    private LocalDate DateDeb;
    private LocalDate DateFin;
    public ArrayList<Chambre> Chamb = new ArrayList<Chambre>();
    private int idRes;
    private String Statut;

    /**
     * Default constructor
     */
    public Reservation(Client e, LocalDate debut, LocalDate fin, String att) {
    	idClient = e;
    	DateDeb = debut;
    	DateFin = fin;
    	Statut = att;
    }
    
    public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public void AjoutChambre(Chambre e) {
    	Chamb.add(e);
    }
	public void SuprChambre(Chambre e) {
    	Chamb.remove(e);
    }

    public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	public LocalDate getDateDeb() {
		return DateDeb;
	}

	public void setDateDeb(LocalDate dateDeb) {
		DateDeb = dateDeb;
	}

	public LocalDate getDateFin() {
		return DateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		DateFin = dateFin;
	}

	public ArrayList<Chambre> getChamb() {
		return Chamb;
	}

	public void setChamb(ArrayList<Chambre> chamb) {
		Chamb = chamb;
	}

	public int getIdRes() {
		return idRes;
	}

	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}

	public String getStatut() {
		return Statut;
	}

	public void setStatut(String statut) {
		Statut = statut;
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