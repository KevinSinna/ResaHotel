package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import dao.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Chambre;
import model.Client;
import model.Reservation;

public class PageReservation implements Observer, Initializable {
	Stage stage; 	
	Parent root;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnGestch;
    @FXML
    private Button btnGestres;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnFact;
    @FXML
    private TableView<Reservation> tabReserv;
    @FXML
    private TableColumn<?, ?> colRes;
    @FXML
    private TableColumn<Reservation, Client> colClient;
    @FXML
    private TableColumn<Reservation, ArrayList<Chambre>> colNum;
    @FXML
    private TableColumn<?, ?> colDebut;
    @FXML
    private TableColumn<?, ?> colFin;
    @FXML
    private TableColumn<?, ?> colStatut;
    @FXML
    private TableColumn<Reservation, ArrayList<Chambre>> colTotal;
    @FXML
    private DatePicker getDebut;
    @FXML
    private DatePicker getFin;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnAnnuler;

    @FXML
    void AjoutReserv(ActionEvent event) throws SQLException{
    	  LocalDate aujourd = LocalDate.now();  	  
    	  //aujourd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	  
    	  // teste si la date est pas passer 
    	if(getDebut.getValue().isBefore(aujourd)) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erreur");
    		alert.setHeaderText("Date passer");
    		alert.showAndWait();
    		
    	}
    	// teste si la date de fin est plus petit que la date de debut
    	else if((getDebut.getValue().isAfter(getFin.getValue()))) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erreur");
    		alert.setHeaderText("Date incorret");
    		alert.showAndWait();
    		//Ouvre la fenetre pour selectionner chambre
    	}else {
    		LocalDate debut = getDebut.getValue();
    		LocalDate fin = getFin.getValue();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/chambdispo.fxml"));
            Parent root;
            
            try {
				root = loader.load();
				//lance methode dans le controller Etape Reserv avec les argument des date selectionner
				EtapeReserv etapereserv = loader.getController();
				etapereserv.initChamb(debut,fin);
				//lancer nouvelle fenetre Etape 1
				Stage etape = new Stage();
		        etape.setScene(new Scene(root));
		        etape.initStyle(StageStyle.UNDECORATED);
		        etape.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
    		
            
    	

    }
    
    

    @FXML
    void Annuler(ActionEvent event) {
    	//Reservation c = tabReserv.getSelectionModel().getSelectedItem();
    	
    }

    @FXML
    void Confirme(ActionEvent event) {

    }

    @FXML
 public void handleButtonAction(ActionEvent event) throws IOException {
        

    	if(event.getSource()==btnAccueil){
    	    	stage=(Stage) btnAccueil.getScene().getWindow();
    	        root = FXMLLoader.load(getClass().getResource("/View/accueil.fxml"));
    	        Scene accueil = new Scene(root);
    	 	   stage.setScene(accueil);
    	 stage.show();
    	   }

    	if(event.getSource()==btnGestch){
    		stage=(Stage) btnGestch.getScene().getWindow();
    	    root = FXMLLoader.load(getClass().getResource("/View/gestionchambre.fxml"));
    	    Scene gestionchambre = new Scene(root);
    		   stage.setScene(gestionchambre);
    	stage.show();
    	}
    	if(event.getSource()==btnGestres){
    		stage=(Stage) btnGestres.getScene().getWindow();
    	    root = FXMLLoader.load(getClass().getResource("/View/gestionreservation.fxml"));
    	    Scene gestionres = new Scene(root);
    		   stage.setScene(gestionres);
    	stage.show();
    	}
    	if(event.getSource()==btnClient){
    		stage=(Stage) btnClient.getScene().getWindow();
    	    root = FXMLLoader.load(getClass().getResource("/View/client.fxml"));
    	    Scene client = new Scene(root);
    		   stage.setScene(client);
    	stage.show();
    	}
    	if(event.getSource()==btnFact){
    		stage=(Stage) btnFact.getScene().getWindow();
    	    root = FXMLLoader.load(getClass().getResource("/View/facturation.fxml"));
    	    Scene facture = new Scene(root);
    		   stage.setScene(facture);
    	stage.show();
    	}
    	}
	@Override
	public void update(java.util.Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		init();
	}
	public void init() {
		colRes.setCellValueFactory(new PropertyValueFactory<>("idRes"));
		colClient.setCellValueFactory(new PropertyValueFactory<>("Client"));
        colClient.setCellFactory(tableColumn -> new GerantIdTableCell());
    	//colNum.setCellValueFactory(new PropertyValueFactory<>("Chamb"));
    	//colNum.setCellFactory(tableColumn -> new GerantNumTableCell(GerantNumTableCell.Flavor.NUMERO));
    	colDebut.setCellValueFactory(new PropertyValueFactory<>("DateDeb"));
    	colFin.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
    	colStatut.setCellValueFactory(new PropertyValueFactory<>("Statut"));
    	//colTotal.setCellValueFactory(new PropertyValueFactory<>("Chamb"));
    	//colTotal.setCellFactory(tableColumn -> new GerantNumTableCell(GerantNumTableCell.Flavor.FORPRIX));
    	tabReserv.setItems(getReserv());
	}



	private ObservableList<Reservation> getReserv() {
		ObservableList<Reservation> c = FXCollections.observableArrayList();
		
    	Connection con = Connexion.ConnexionBD();
    	PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT * FROM `Reservation`");
			ResultSet rs=(ResultSet) ps.executeQuery();
			while(rs.next()) {
				Reservation r = new Reservation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getDate(5),rs.getDate(6),rs.getDouble(7));
				c.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return c;
	}
	}


