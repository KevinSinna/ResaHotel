package model;



/**
 * 
 */
public class Presidentiel extends Chambre {
	 private double prix = 90;
	 final private String typech = "Presidentiel";
   
	 

	/**
     * Default constructor
     */
    public Presidentiel(int e,int num) {
    	super(e,num);
    }
    public Presidentiel(int e,int num,double it) {
    	super(e,num,it);
    	this.prixTotal = prix;
    }
   
    public Presidentiel() {
		// TODO Auto-generated constructor stub
	}
	public double getPrix() {
		return prix;
	}

	public String getTypech() {
		return typech;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	

	/**
     * 
     */
   

}