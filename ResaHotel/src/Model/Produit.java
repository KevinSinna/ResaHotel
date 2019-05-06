package model;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import dao.Connexion;

/**
 * 
 */
public class Produit {
	private int idProd;
	private String nomProd;
	  private double prix;
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
	/**
     * Default constructor
     */
    public Produit() {
    	 
    }
    Produit(String nom, double prix){
    	nomProd = nom;
    	this.prix = prix;
    }

    /////////// Ajout BSD ////////////////
    public void Ajout(String type,String nom,double prix, int idRes) {
    	Connection conn=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("INSERT INTO `Produit`(`prix`, `nom`, `type`) values (?,?,?)");
		ps.setString(3, type);
		ps.setString(2, nom);
		ps.setDouble(1,prix);
		ps.executeUpdate();
		ps.close();
		initID();
		
		} catch (Exception e) {
		System.out.println("error insert to Chambre");
		e.printStackTrace();}
    }
    //initialisé id de l'objet
private void initID() throws SQLException {
		// TODO Auto-generated method stub
	Connection conn=Connexion.ConnexionBD();
	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT MAX(`IdProd`) FROM `Produit`WHERE `nom`='"+this.nomProd+"' AND `prix`='"+this.prix+"' ");
	ResultSet rs = (ResultSet) ps.executeQuery();
	if(rs.next()) {
	this.idProd = rs.getInt(1);
	ps.close();
	}}
//////////////////////////////////////
	public int getIdProd() {
		return idProd;
	}
	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	
    

}