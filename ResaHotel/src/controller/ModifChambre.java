package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ModifChambre {
	

	   @FXML
	    private Button idclose;

	    @FXML
	    private TextField idnumMD;

	    @FXML
	    private ChoiceBox<?> idTypebox;

	    @FXML
	    private TextField idetageMD;

	    @FXML
	    private Button btnVMod;
	    
	    public void transferMessage(int num) {
	        //Display the message
	       System.out.println(num);
	    }

    @FXML
    void ValideChambre(ActionEvent event) {
    	
    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void initializebox(MouseEvent event) {

    }
    
    public void getnum(int num) {
    	System.out.print(num);
    }
    

}

