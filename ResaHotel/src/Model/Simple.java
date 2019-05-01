package model;



/**
 * 
 */
public class Simple extends Chambre {

	 private double prix = 40;
	 final private static String typech = "Simple";

	//////////////////////////
    public Simple(int e, int num) {
    	super(e,num);
    }
    public Simple(int e, int num,double it) {
    	super(e,num,it);
    	this.prixTotal = prix;
    }


    public Simple() {
		// TODO Auto-generated constructor stub
	}


	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}


	public String getTypech() {
		return typech;
	}

	
	/**
     * 
     */
   
}