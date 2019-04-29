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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    // ajoute a ma liste de chambre selectionnée
    @FXML
    void AjoutTab(ActionEvent event) {
    	//verifie si une chambre est selectionné
    	if((tabDispo.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre ");
    		alert.showAndWait();
    	}else {
    		// initialise tableau de selection chambre
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	colType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	colEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	colNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
    	// chambre dispo selectionnée
    	Chambre n = tabDispo.getSelectionModel().getSelectedItem();	
    	// ajoute dans tab chambre selectionner
    	tabSelect.getItems().add(n);
    	// retire dans tab chambre dispo
    	tabDispo.getItems().remove(n);
    }}

    @FXML
    void Suivant(ActionEvent event){
    	System.out.println(fin);
		System.out.println(debut);

    }

    @FXML
    void SuprTab(ActionEvent event) {
     	//verifie si une chambre est selectionnée
    	if((tabSelect.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre à retirer");
    		alert.showAndWait();
    	}else {
    	Chambre n = tabSelect.getSelectionModel().getSelectedItem();	
    	tabDispo.getItems().add(n);
    	tabSelect.getItems().remove(n);
    }
    }
    //Fermer la page
    @FXML
    void close(ActionEvent event) {
    	stage=(Stage) btnAnnul.getScene().getWindow();   	
    	stage.close();
    }
    // recuperation des date
  //initialisation tableau Chambre disponible
	public void initChamb(LocalDate debut, LocalDate fin) throws SQLException {
		// TODO Auto-generated method stub
		this.debut = debut;
		this.fin = fin;
		System.out.println(fin);
		System.out.println(debut);
		dispPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	dispType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	dispEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	dispNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
    	tabDispo.setItems(getChambreLibre(debut,fin));
	}
	
      // renvoye Tableau Chambre disponible
	public ObservableList<Chambre> getChambreLibre(LocalDate debut, LocalDate fin) throws SQLException {
		System.out.println(fin);
		System.out.println(debut);
		ObservableList<Chambre> c = FXCollections.observableArrayList();
		Connection conn=Connexion.ConnexionBD();
		System.out.println("'"+this.debut+"','"+this.fin+"'");
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM `Chambre` WHERE `numero` NOT IN (SELECT `numero` FROM `Reservation` WHERE (`DateDeb` BETWEEN '"+this.debut+"' AND '"+this.fin+"') OR (`DateFin` BETWEEN '"+this.debut+"' AND '"+this.fin+"') )");
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
		
	}

}