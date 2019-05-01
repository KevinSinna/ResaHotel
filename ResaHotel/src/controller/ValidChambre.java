package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Presidentiel;
import model.Simple;

public class ValidChambre implements Initializable{
	Stage stage;
	ObservableList<String> typeItems = FXCollections.observableArrayList("Normal","Simple","Presidentiel","Double");
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
    	
    	
    	if((idetage.getText().isEmpty())||(idnumero.getText().isEmpty())||(idTypebox.getValue() == null))
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs Vide");
    		alert.showAndWait();
    	}else if((isStringInt(idetage.getText())==false)||(isStringInt(idnumero.getText())==false)) {
    		// ajout control si textfiel numero et etage pas un entier 
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs interdit");
    		alert.showAndWait();
    	}else {
    		// crée objet et ajoute dans BD de differente type 
    	switch(idTypebox.getValue()) {
    	case "Normal":
    	model.Normal n = new model.Normal(Integer.parseInt(idetage.getText()), Integer.parseInt(idnumero.getText()));
    	n.Ajout(n.getPrix(), n.getTypech());
    	break;
    	case "Simple":
        	Simple s = new Simple(Integer.parseInt(idetage.getText()), Integer.parseInt(idnumero.getText()));
        	s.Ajout(s.getPrix(), s.getTypech());
        	break;
    	case "Presidentiel":
        	Presidentiel p = new Presidentiel(Integer.parseInt(idetage.getText()), Integer.parseInt(idnumero.getText()));
        	p.Ajout(p.getPrix(), p.getTypech());
        	break;
    	case "Double" :
        	model.Double d = new model.Double(Integer.parseInt(idetage.getText()), Integer.parseInt(idnumero.getText()));
        	d.Ajout(d.getPrix(), d.getTypech());
        	break;
    	}
    	stage=(Stage) btnValid.getScene().getWindow();   	
    	stage.close();
    	}
    	}
	public boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	
    @FXML
    void close(ActionEvent event) {
    	stage=(Stage) idclose.getScene().getWindow();   	
    	stage.close(); 
    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			initializebox();
		}
    }
