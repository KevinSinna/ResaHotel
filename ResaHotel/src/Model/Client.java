package model;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Observable;

import com.mysql.jdbc.PreparedStatement;


import dao.Connexion;

/**
 * 
 */
public class Client extends Observable {
    private int idClient = 0;
    private String nom;
    private String prenom;
    /**
     * Default constructor
     */
    public Client( String n,String p) {
    	 nom = n; prenom = p;
    }
    //////////////AJOUTE BSD ///////////////
    public void AjoutClient() {
    	Connection conn=Connexion.ConCnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("insert into Client (Nom,Prenom) values (?,?)");
		ps.setString(1,this.nom);
		ps.setString(2,this.prenom);
		ps.executeUpdate();
		ps.close();

		} catch (Exception e) {
		System.out.println("error insert to Client");
		e.printStackTrace();}
    }
///////////////// SUPRIMER //////////
   
   
    public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
     * 
     */


}