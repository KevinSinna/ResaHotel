package model;




/**
 * 
 */
public class Service extends Produit {

    /**
     * Default constructor
     */
    public Service() {
    }

    public int getNbServ() {
		return nbServ;
	}

	public void setNbServ(int nbServ) {
		this.nbServ = nbServ;
	}

	/**
     * 
     */
    private int nbServ;

}