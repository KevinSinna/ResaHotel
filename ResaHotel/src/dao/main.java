package dao;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("kev");
		
		//pour inserer
		Connection conn=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn.prepareStatement("insert into Chambre (etage,numero) values (?,?)");
		ps.setString(1, "etage4");
		ps.setInt(2,45);
	
		ps.executeUpdate();
		ps.close();

		} catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
}
		//fin
		
		//pour modifier
		String e="etagechanger";
		int a=4;
String user="etage2";
		Connection conn1=Connexion.ConnexionBD();
		try {PreparedStatement ps=(PreparedStatement) conn1.prepareStatement(
				"UPDATE `Chambre` SET `etage`='"+e+"',`numero`='"+a+"' WHERE etage='"+user+"'");
	
		ps.executeUpdate();
		ps.close();

		} catch (Exception e1) {
		System.out.println(e);
		e1.printStackTrace();
}
		//fin 
		
		
		//pour selectionner
		try {
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT `etage`,`numero`  FROM `Chambre`");
			
			ResultSet rs=(ResultSet) ps.executeQuery();
			while(rs.next()){
					System.out.println(rs.getString(1)+" "+rs.getInt(2));
				};
				ps.close();
			
			} catch (Exception e3) {
				e3.printStackTrace();
	}
		//fin
		
		//pour delete
		try {
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement("DELETE FROM `Chambre` WHERE 1");
			
			ps.executeUpdate();
			
				ps.close();
			
			} catch (Exception e3) {
				e3.printStackTrace();
	}
		
		//fin
		
	}

}
