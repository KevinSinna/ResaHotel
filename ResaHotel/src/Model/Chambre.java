package model;


import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.Connexion;

/**
 * 
 */
public class Chambre {

    /**
     * Default constructor
     */
    public Chambre(int e,int num) {
    	etage = e; NumeroCh = num;
    }
    
    public void Ajout() {
    	Connection conn=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("insert into Chambre (etage,numero) values (?,?)");
		ps.setInt(1, this.etage);
		ps.setInt(2,this.NumeroCh);
	
		ps.executeUpdate();
		ps.close();

		} catch (Exception e) {
		System.out.println("error insert to Chambre");
		e.printStackTrace();}
    }

    /**
     * 
     */
    private int NumeroCh;

    

	public int getNumeroCh() {
		return NumeroCh;
	}

	public void setNumeroCh(int numeroCh) {
		NumeroCh = numeroCh;
	}

	/**
     * 
     */
    private int etage;
    
    public int getEtage() {
  		return etage;
  	}

  	public void setEtage(int etage) {
  		this.etage = etage;
  	}



}