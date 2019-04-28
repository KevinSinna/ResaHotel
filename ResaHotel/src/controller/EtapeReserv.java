package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import dao.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Chambre;
import model.Normal;
import model.Presidentiel;
import model.Simple;

public class EtapeReserv implements Initializable {
	Stage stage;
	public LocalDate debut;
	public LocalDate fin;
	@FXML
	private TextField inpID;

    @FXML
    private TableView<Chambre> tabDispo;

    @FXML
    private TableColumn<?, ?> dispNum;

    @FXML
    private TableColumn<?, ?> dispEtage;

    @FXML
    private TableColumn<?, ?> dispType;

    @FXML
    private TableColumn<?, ?> dispPrix;

    @FXML
    private TableView<Chambre> tabSelect;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableColumn<?, ?> colEtage;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colPrix;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnSupr;

    @FXML
    private Button btnSuivant;

    @FXML
    private Button btnAnnul;

    @FXML
    void AjoutTab(ActionEvent event) {

    }

    @FXML
    void Suivant(ActionEvent event) {

    }

    @FXML
    void SuprTab(ActionEvent event) {

    }
    //Fermer la page
    @FXML
    void close(ActionEvent event) {
    	stage=(Stage) btnAnnul.getScene().getWindow();   	
    	stage.close();
    }
    // recuperation des date
	public void initChamb(LocalDate debut, LocalDate fin) {
		// TODO Auto-generated method stub
		this.debut = debut;
		this.fin = fin;
		System.out.println(fin);
		System.out.println(debut);
	}
	//initialisation tableau Chambre disponible
	void initTabDispo() throws SQLException {
		dispPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	dispType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	dispEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	dispNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
    	tabDispo.setItems(getChambreLibre());
	}
      // renvoye Tableau Chambre disponible
	private ObservableList<Chambre> getChambreLibre() throws SQLException {
		ObservableList<Chambre> c = FXCollections.observableArrayList();
		Connection conn=Connexion.ConnexionBD();
		System.out.println("'"+this.debut+"','"+this.fin+"'");
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM `Chambre` WHERE `numero` NOT IN (SELECT `numero` FROM `Reservation` WHERE (`DateDeb` BETWEEN '"+debut+"' AND '"+fin+"') AND (`DateFin` BETWEEN '"+debut+"' AND '"+fin+"') )");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	while(rs.next()) {
			String type=rs.getString(4);
			switch(type){
				case "Double": model.Double d = new model.Double(rs.getInt(1),rs.getInt(2));
				c.add(d);
				break;
				case "Simple": Simple s = new Simple(rs.getInt(1),rs.getInt(2));
			    c.add(s);
			    break;
				case "Normal": Normal n = new Normal(rs.getInt(1),rs.getInt(2));
			    c.add(n);
			    break;
				case "Presidentiel": Presidentiel p = new Presidentiel(rs.getInt(1),rs.getInt(2));
			    c.add(p);
			    break;
			}		 
    	}
		ps.close();
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			initTabDispo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}