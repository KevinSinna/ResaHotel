package model;



/**
 * 
 */
public class Simple extends Chambre {

	 private static double prix;
	 final String typech = "S";

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

	
	/**
     * 
     */
   
}