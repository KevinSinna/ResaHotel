package model;

/**
 * 
 */
public class Normal extends Chambre {

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

	public static String getTypech() {
		return typech;
	}

	public static void setTypech(String typech) {
		Normal.typech = typech;
	}

	/**
     * 
     */
    private static double prix = 50;
    private static String typech = "N";

}