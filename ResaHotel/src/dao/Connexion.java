package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connexion {
	Connection conn=null;
	public static Connection ConnexionBD(){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://mysql-sinnakev.alwaysdata.net/sinnakev_resahotel","sinnakev","skevin91");
		System.out.println("Connexion à la base de données de ResaHotel");
			return con;
		
		 }catch(ClassNotFoundException | SQLException e){
	       System.out.println(e);
	         return null;
		 }
}
}
