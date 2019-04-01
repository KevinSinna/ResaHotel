package model;


import java.util.*;

/**
 * 
 */
public class Hotel {
	private ArrayList<Reservation> TabRes;
	
	public ArrayList<Reservation> getTabRes() {
		return TabRes;
	}

	public void setTabRes(ArrayList<Reservation> tabRes) {
		TabRes = tabRes;
	}

	private ArrayList<Chambre> TabChambre;
	

	public ArrayList<Chambre> getTabChambre() {
		return TabChambre;
	}

	public void setTabChambre(ArrayList<Chambre> tabChambre) {
		TabChambre = tabChambre;
	}

	/**
     * Default constructor
     */
    public Hotel() {
    }

    public void AjoutChambre(Chambre newch){
    	TabChambre.add(newch);
    }
    
    public void SuprChambre(Chambre rmvch){
    	TabChambre.remove(rmvch);
    }

    public void AjoutRes(Reservation newrs){
    	TabRes.add(newrs);
    }


    /**
     * 
     */

    public void ChercherChamb() {
        // TODO implement here
    }

}