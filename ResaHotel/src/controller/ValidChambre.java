package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    void ValideChambre(ActionEvent event) {
    	if(idetage==null) {
    		System.out.println("remplir");
    	}
    	stage=(Stage) btnValid.getScene().getWindow();   	
    	stage.close();   	
    }

    @FXML
    void initializebox() {
    	idTypebox.setItems(typeItems);
    	System.out.println("initialisé box");
    }
    }
