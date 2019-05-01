package model;



/**
 * 
 */
public class Normal extends Chambre {
	   private double prix = 50;
	   final private String typech = "Normal";

    /**
     * Default constructor
     */
    public Normal(int e,int num) {
    	super(e,num);
    }
    public Normal(int e,int num,double it) {
    	super(e,num,it);
    	this.prixTotal = prix;
    }
    
   
    public Normal() {
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