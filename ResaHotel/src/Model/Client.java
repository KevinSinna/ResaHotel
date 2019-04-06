package model;


import java.sql.Connection;


import com.mysql.jdbc.PreparedStatement;


import dao.Connexion;

/**
 * 
 */
public class Client {
    private int idClient;
    private String nom;
    private String prenom;
    /**
     * Default constructor
     */
    public Client(int id, String n,String p) {
    	idClient = id; nom = n; prenom = p;
    }
    //////////////AJOUTE BSD ///////////////
    public void AjoutClient() {
    	Connection conn=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("insert into Chambre (IdClient,Nom,Prenom) values (?,?,?)");
		ps.setInt(1, this.idClient);
		ps.setString(2,this.nom);
		ps.setString(3,this.prenom);
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