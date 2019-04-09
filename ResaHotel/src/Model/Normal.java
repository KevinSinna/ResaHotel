package model;



/**
 * 
 */
public class Normal extends Chambre {
	   private static double prix = 50;
	   final private String typech = "Normal";

    /**
     * Default constructor
     */
    public Normal(int e,int num) {
    	super(e,num);
    }
    
   
    public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Normal.prix = prix;
	}

	public String getTypech() {
		return typech;
	}



	/**
     * 
     */
 

}