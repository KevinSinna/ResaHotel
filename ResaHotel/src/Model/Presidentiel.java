package model;



/**
 * 
 */
public class Presidentiel extends Chambre {
	 private static double prix = 90;
	 final private String typech = "Presidentiel";
    /**
     * Default constructor
     */
    public Presidentiel(int e,int num) {
    	super(e,num);
    }
   
    public static double getPrix() {
		return prix;
	}

	public String getTypech() {
		return typech;
	}

	/**
     * 
     */
   

}