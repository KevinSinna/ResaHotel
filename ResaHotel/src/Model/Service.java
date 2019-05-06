package model;




/**
 * 
 */
public class Service extends Produit {

  
  private final String type = "service";
    public Service(double prix , String nom) {
    	super(nom,prix);
    }
	public String getType() {
		return type;
	}
	



}