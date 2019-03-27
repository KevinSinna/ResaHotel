package model;

/**
 * 
 */
public class Presidentiel extends Chambre {

    /**
     * Default constructor
     */
    public Presidentiel(int e,int num) {
    	super(e,num);
    }

    public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Presidentiel.prix = prix;
	}

	public static String getTypech() {
		return typech;
	}

	public static void setTypech(String typech) {
		Presidentiel.typech = typech;
	}

	/**
     * 
     */
    private static double prix;
    private static String typech = "P";

}