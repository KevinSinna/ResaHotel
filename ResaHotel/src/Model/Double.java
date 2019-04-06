package model;



/**
 * 
 */
public class Double extends Chambre {
	private static double prix = 60;
    final String typech = "D";
    ////////////////////////////
   
    public Double(int etag,int num) {
    	super(etag,num); 	
    }
    

    
    /////GET SET PRIX ///////////
	public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Double.prix = prix;
	}
	////////////////////////////////
    
}