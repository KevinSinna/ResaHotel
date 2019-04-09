package model;



/**
 * 
 */
public class Double extends Chambre {
	private static double prix = 60;
    final private String typech = "D";
    ////////////////////////////
   
    public Double(int etag,int num) {
    	super(etag,num); 	
    }
    public Double() {
    	
    }
    

    
    /////GET SET PRIX ///////////
	public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Double.prix = prix;
	}
	////////////////////////////////
	public String getTypech() {
		return typech;
	}
    
}