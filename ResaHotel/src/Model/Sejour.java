package model;


import java.util.*;

/**
 * 
 */
public class Sejour {
	
	 private ArrayList<Produit> TabConso;
	 public Reservation Res;

    /**
     * Default constructor
     */
    public Sejour(Reservation resa) {
    	Res = resa;
    	TabConso = new ArrayList<Produit>();
    	
    }
   ///////////// Ajout , Supr Produit ////////////
    public void AjoutProdList(Produit ProdAj) {
    	TabConso.add(ProdAj);
    }
    public void DelectProdList(Produit ProdAj) {
    	TabConso.remove(ProdAj);
    }
    
    /////////////////////////////////////////
    
  
    public void Facturation() {
        // TODO implement here
    }


	public ArrayList<Produit> getTabConso() {
		return TabConso;
	}

	public void setTabConso(ArrayList<Produit> tabConso) {
		TabConso = tabConso;
	}

}