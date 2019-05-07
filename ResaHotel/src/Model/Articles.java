package model;



/**
 * 
 */
public class Articles extends Produit {
	private final String type = "article";
    /**
     * Default constructor
     */
    public Articles(double prix , String nom) {
    	super(nom,prix);
    }
	public String getType() {
		return type;
	} 
    

 
}