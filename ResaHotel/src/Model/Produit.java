package model;




/**
 * 
 */
public class Produit {

    /**
     * Default constructor
     */
    public Produit() {
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

	/**
     * 
     */
    private double prix;

    /**
     * 
     */
    private String nomProd;

}