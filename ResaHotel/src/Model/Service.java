package model;




/**
 * 
 */
public class Service extends Produit {

   private String nomProd;
   private double prix;
   final String type = "service";
    public Service(double prix , String nom) {
    	this.prix = prix;
    	nomProd = nom;
    }
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}



}