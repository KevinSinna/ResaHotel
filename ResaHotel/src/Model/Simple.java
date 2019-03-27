package model;

/**
 * 
 */
public class Simple extends Chambre {

    /**
     * Default constructor
     */
    public Simple(int e, int num) {
    	super(e,num);
    }

    public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Simple.prix = prix;
	}

	public static String getTypech() {
		return typech;
	}

	public static void setTypech(String typech) {
		Simple.typech = typech;
	}

	/**
     * 
     */
    private static double prix;
    private static String typech = "S";

}