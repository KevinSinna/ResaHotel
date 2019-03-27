package dao;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import model.Chambre;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chambre kev = new Chambre(43,333);
		kev.Ajout();
		
	}

}
