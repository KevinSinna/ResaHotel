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
   
    public Chambre() {
		// TODO Auto-generated constructor stub
	}
    public void getTotal(double prix,double days) {
        this.prixTotal = prix * days;
    }
 

	//////////////// AJOUT BSD ////////////////////
    public void Ajout(double prix, String type) {
    	Connection conn=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("insert into Chambre (etage,numero,prix,type) values (?,?,?,?)");
		ps.setInt(1, this.etage);
		ps.setInt(2,this.NumeroCh);
		ps.setDouble(3,prix);
		ps.setString(4,type);
		ps.executeUpdate();
		ps.close();

		} catch (Exception e) {
		System.out.println("error insert to Chambre");
		e.printStackTrace();}
    }
    /////////////////////////////////////////////// 
   
   

    /**
     * 
     */
    private int NumeroCh;
    //public ArrayList<Reservation> TabRes;
    


	/**
     * 
     */
    private int etage;
    private double prixTotal;



	public int getNumeroCh() {
		return NumeroCh;
	}

	public void setNumeroCh(int numeroCh) {
		NumeroCh = numeroCh;
	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public double getTotalprvt() {
		return prixTotal;
	}

	public void setTotal(double total) {
		prixTotal = total;
	}
    
   


}