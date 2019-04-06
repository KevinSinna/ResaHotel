package model;



/**
 * 
 */
public class Presidentiel extends Chambre {
	 private static double prix;
	 final String typech = "P";
    /**
     * Default constructor
     */
    public Presidentiel(int e,int num) {
    	super(e,num);
    }
   
    public static double getPrix() {
		return prix;
	}

	/**
     * 
     */
   

}