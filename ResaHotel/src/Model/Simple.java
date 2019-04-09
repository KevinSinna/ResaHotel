package model;



/**
 * 
 */
public class Simple extends Chambre {

	 private static double prix = 40;
	 final private static String typech = "Simple";

	//////////////////////////
    public Simple(int e, int num) {
    	super(e,num);
    }


    public Simple() {
		// TODO Auto-generated constructor stub
	}


	public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Simple.prix = prix;
	}


	public String getTypech() {
		return typech;
	}

	
	/**
     * 
     */
   
}