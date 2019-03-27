package model;

/**
 * 
 */
public class Double extends Chambre {

    /**
     * Default constructor
     */
    public Double(int e,int num) {
    	super(e,num); 	
    }

    /**
     * 
     */
    private static double prix = 60;
    private static String typech = "D";
	public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Double.prix = prix;
	}

	public static String getTypech() {
		return typech;
	}

	public static void setTypech(String typech) {
		Double.typech = typech;
	}
    
}