package model;



/**
 * 
 */
public class Articles extends Produit {
	private double prix;
	private String nomProd;
	final String type = "article";
    /**
     * Default constructor
     */
    public Articles(double prix , String nom) {
    	this.prix = prix;
    	nomProd = nom;
    } 
    
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}

 
}