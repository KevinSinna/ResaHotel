package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Fenetre {
    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnGestch;

    @FXML
    private Button btnGestres;

    @FXML
    private Button btnClient;
    
    @FXML
    private Button btnAjoute;

    @FXML
    private Button btnSupr;

    @FXML
    private Button btnFact;
    
    @FXML
    private Button idclose;
    
    Stage stage;
    
    
    // MenuBar changer scene par boutton
    @FXML
	 public void handleButtonAction(ActionEvent event) throws IOException {
	        

	    	Stage stage;
			Parent root;
			
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
    // ferme la fenetre
    @FXML
    void close(ActionEvent event) {
    	stage=(Stage) idclose.getScene().getWindow();   	
    	stage.close(); 
    }

}
