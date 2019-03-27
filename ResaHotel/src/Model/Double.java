package model;


import java.util.*;

/**
 * 
 */
public class Double extends Chambre {

    /**
     * Default constructor
     */
    public Double() {
    	
    }

    /**
     * 
     */
    private static double prix = 60;

	public static double getPrix() {
		return prix;
	}

	public static void setPrix(double prix) {
		Double.prix = prix;
	}
    
}