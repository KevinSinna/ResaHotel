package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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
    private TableView<?> tabReserv;
    @FXML
    private TableColumn<?, ?> colRes;
    @FXML
    private TableColumn<?, ?> colClient;
    @FXML
    private TableColumn<?, ?> colNum;
    @FXML
    private TableColumn<?, ?> colDebut;
    @FXML
    private TableColumn<?, ?> colFin;
    @FXML
    private TableColumn<?, ?> colStatut;
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
    void AjoutReserv(ActionEvent event) {
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
    	else if(getDebut.getValue().isAfter(getFin.getValue())) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erreur");
    		alert.setHeaderText("Date incorret");
    		alert.showAndWait();
    	}

    }
    
    

    @FXML
    void Annuler(ActionEvent event) {
    	
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
		
	}

}
