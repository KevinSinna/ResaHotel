package model;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

import dao.Connexion;

/**
 * 
 */
public class Produit {

    /**
     * Default constructor
     */
    public Produit() {

    }

    /////////// Ajout BSD ////////////////
    public void Ajout(String type,double prix) {
    	Connection conn=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("insert into Chambre (type,prix) values (?,?)");
		ps.setString(1, type);
		ps.setDouble(2,prix);
		ps.executeUpdate();
		ps.close();

		} catch (Exception e) {
		System.out.println("error insert to Chambre");
		e.printStackTrace();}
    }
//////////////////////////////////////
	
    

}