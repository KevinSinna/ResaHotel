package model;



/**
 * 
 */
public class Double extends Chambre {
	private double prix = 60;
    final private String typech = "Double";
    ////////////////////////////
   
    public Double(int etag,int num) {
    	super(etag,num); 	
    }
    public Double(int etag,int num,double it) {
    	super(etag,num,it);
    	this.prixTotal = prix;
    }
    public Double() {
    	super();
    }
    

    
    /////GET SET PRIX ///////////
	public double getPrix() {
		return prix;
	}


	public void setPrix(double p) {
		prix = p;
	}
	////////////////////////////////
	public String getTypech() {
		return typech;
	}
    
}