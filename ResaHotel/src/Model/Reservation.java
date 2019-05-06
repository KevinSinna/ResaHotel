package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Observable;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import dao.Connexion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 */
public class Reservation extends Observable {
	private Client Client;
    private LocalDate DateDeb;
    private LocalDate DateFin;
    public ArrayList<Chambre> Chamb = new ArrayList<Chambre>();
    private int idRes;
    private String Statut;
    /**
     * Default constructor
     */
    public Reservation(Client e, LocalDate debut, LocalDate fin, String att) {
    	Client = e;
    	DateDeb = debut;
    	DateFin = fin;
    	Statut = att;
    }
    public Reservation(int idr, int num, int idclient,String att,Date debut,Date fin,double t) {
    	idRes = idr;
    	Client e = new Client();
    	e.setIdClient(idclient);
    	this.Client = e;
    	Chambre c = new Chambre();
    	c.setNumeroCh(num);
    	c.setTotal(t);
    	Chamb.add(c);
    	DateDeb = debut.toLocalDate();
    	DateFin = fin.toLocalDate();
    	Statut = att;
    }
    public int AjoutBD(double days) throws SQLException {
    	Connection conn=Connexion.ConnexionBD();
    	
    		if(getidclient()==true) {
    			for(int i =0;i<Chamb.size();i++) {
    	try {
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement("INSERT INTO `Reservation`( `numero`, `IdClient`, `Statut`, `DateDeb`, `DateFin`, `Total`) VALUES (?,?,?,?,?,?)");
			ps.setInt(1,Chamb.get(i).getNumeroCh());
			ps.setInt(2, this.Client.getIdClient());
			ps.setString(3, this.Statut);
			ps.setDate(4,getSQLdate(DateDeb));
			ps.setDate(5,getSQLdate(DateFin));
			ps.setDouble(6, ((Chamb.get(i).getTotalprvt())*days));
			ps.executeUpdate();
    	
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
    			return 1;
    		}else{
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Numero Client n'existe pas");
    		alert.showAndWait();
    		return 0;
		}
    	}
    	//verifie si client existant
    private boolean getidclient() throws SQLException {
		// TODO Auto-generated method stub
    	int id = -1;
    	Connection conn=Connexion.ConnexionBD();
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT `IdClient` FROM `Client` WHERE `IdClient`='"+Client.getIdClient()+"'");
    	ResultSet rs=(ResultSet) ps.executeQuery();
    	if(rs.next()) {
    		id = rs.getInt(1);
    	}
    	if(id==Client.getIdClient()) {
    		return true;
    	}else {
		return false;}
	}


    public java.sql.Date getSQLdate(LocalDate e) {
    	java.util.Date deb = Date.from(e.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    	return new java.sql.Date(deb.getTime());
    }
    
    public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public void AjoutChambre(Chambre e) {
    	Chamb.add(e);
    }
	public void SuprChambre(Chambre e) {
    	Chamb.remove(e);
    }

    public Client getClient() {
		return Client;
	}

	public void setClient(Client idClient) {
		this.Client = idClient;
	}

	public LocalDate getDateDeb() {
		return DateDeb;
	}

	public void setDateDeb(LocalDate dateDeb) {
		DateDeb = dateDeb;
	}

	public LocalDate getDateFin() {
		return DateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		DateFin = dateFin;
	}

	public ArrayList<Chambre> getChamb() {
		return Chamb;
	}

	public void setChamb(ArrayList<Chambre> chamb) {
		Chamb = chamb;
	}

	public int getIdRes() {
		return idRes;
	}

	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}

	public String getStatut() {
		return Statut;
	}

	public void setStatut(String statut) {
		Statut = statut;
	}
//methode mise a jour BD du statut de la reservation en annuler
	public void Annul() throws SQLException {
      // TODO implement here
    	this.Statut = "Annuler";    	
    	Connection conn1=Connexion.ConnexionBD();
		PreparedStatement ps=(PreparedStatement) conn1.prepareStatement(
				"UPDATE `Reservation` SET `Statut`= '"+this.Statut+"'WHERE `IdRes`='"+this.idRes+"'");
		ps.executeUpdate();
		ps.close();
    }
//methode mise a jour BD du statut de la reservation en valider
    
    public void Confirme() throws SQLException {
        // TODO implement here
    	this.Statut = "Valider";
    	Connection conn1=Connexion.ConnexionBD();
		PreparedStatement ps=(PreparedStatement) conn1.prepareStatement(
				"UPDATE `Reservation` SET `Statut`= '"+this.Statut+"'WHERE `IdRes`='"+this.idRes+"'");
		ps.executeUpdate();	
		ps.close();
    }

}