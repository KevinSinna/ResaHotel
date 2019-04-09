package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Normal;

public class ValidChambre {
	Stage stage;
	ObservableList<String> typeItems = FXCollections.observableArrayList("Normal","Simple","Presidenciel","Double");
    @FXML
    private TextField idnumero;

    @FXML
    private ChoiceBox<String> idTypebox;

    @FXML
    private TextField idetage;

    @FXML
    private Button btnValid;
    
    @FXML
    private Button idclose;
    
	@FXML
    void initializebox() {
    	idTypebox.setItems(typeItems);
    	System.out.println("initialisé box");
    }
    
    @FXML
    void ValideChambre(ActionEvent event) {
    	
    	
    	if((idetage.getText().isEmpty())||(idnumero.getText().isEmpty()))
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur Saisie");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs Vide");
    		alert.showAndWait();
    	}else{}
    		
    	}
    		
    	
    	/**Normal chambre = new Normal(Integer.parseInt(idetage.getText()), Integer.parseInt(idnumero.getText()));
    	chambre.Ajout(Normal.getPrix(), Normal.getTypech());
    	stage=(Stage) btnValid.getScene().getWindow();   	
    	stage.close();   **/
    
	
    @FXML
    void close(ActionEvent event) {
    	stage=(Stage) idclose.getScene().getWindow();   	
    	stage.close(); 
    }
    }
