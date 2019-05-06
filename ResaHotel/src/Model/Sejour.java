package model;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;

import dao.Connexion;

/**
 * 
 */
public class Sejour {
	
	 private ArrayList<Produit> TabConso;
	 public Reservation Res;

    /**
     * Default constructor
     */
    public Sejour(Reservation resa) {
    	Res = resa;
    	TabConso = new ArrayList<Produit>();
    	
    }
   public Sejour() {
		// TODO Auto-generated constructor stub
	}
	///////////// Ajout , Supr Produit ////////////
    public void AjoutProdList(Produit ProdAj) {
    	TabConso.add(ProdAj);
    }
    public void DelectProdList(Produit ProdAj) {
    	TabConso.remove(ProdAj);
    }
    
    /////////////////////////////////////////
    
  
    public void Facturation() {
        // TODO implement here
    }


	public ArrayList<Produit> getTabConso() {
		return TabConso;
	}

	public void setTabConso(ArrayList<Produit> tabConso) {
		TabConso = tabConso;
	}
	public void AjoutBD() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = Connexion.ConnexionBD();
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into Sejour (IdRes,IdProd) values (?,?)");
		for(int i = 0 ; i < TabConso.size() ; i++) {
		ps.setInt(1,Res.getIdRes());
		ps.setInt(2,TabConso.get(i).getIdProd());
		}
		ps.executeUpdate();
		ps.close();
	}

}