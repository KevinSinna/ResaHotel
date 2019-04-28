package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Chambre;
import model.Normal;
import model.Presidentiel;
import model.Simple;

public class ModifChambre implements Initializable {
	public int numch;
	Stage stage;
	
	ObservableList<String> typeItems = FXCollections.observableArrayList("Normal","Simple","Presidentiel","Double");
	   @FXML
	    private Button idclose;
	   
	    @FXML
	    private Label idtypeactu;

	    @FXML
	    private TextField idnumMD;

	    @FXML
	    private ChoiceBox<String> idTypebox;

	    @FXML
	    private TextField idetageMD;

	    @FXML
	    private Button btnVMod;

    @FXML
    void ValideChambre(ActionEvent event) {
    	// test si textfield vide ou pas
    	if((idetageMD.getText().isEmpty())||(idnumMD.getText().isEmpty())||(idTypebox.getValue() == null))
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs Vide");
    		alert.showAndWait();
    	}else if((isStringInt(idetageMD.getText())==false)||(isStringInt(idnumMD.getText())==false)) {
    		// ajout control si textfiel numero et etage pas un entier 
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs interdit");
    		alert.showAndWait();
    	}else {
    		try{ 
    			// initialisation prix par rapport au type de chambre
    			double prix =0;
    			switch(idTypebox.getValue()) {
    			case "Simple" : prix = Simple.getPrix();
    			case "Normal" : prix = Normal.getPrix();
    			case "Double" : prix = model.Double.getPrix();
    			case "Presidentiel" : prix = Presidentiel.getPrix();
    			}
    			// Modification dans la base de donnée
    			Connection conn1=Connexion.ConnexionBD();
    		PreparedStatement ps=(PreparedStatement) conn1.prepareStatement(
"UPDATE `Chambre` SET `etage`='"+idetageMD.getText()+"',`numero`='"+idnumMD.getText()+"',`prix`='"+prix+"',`type`='"+idTypebox.getValue()+"'WHERE numero = '"+numch+"'");  	
    		ps.executeUpdate();
    		ps.close();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		//fermeture de la fenetre
    		stage=(Stage) btnVMod.getScene().getWindow();   	
        	stage.close();
    	}
    }

    @FXML
    void initializebox() {
    	idTypebox.setItems(typeItems);
    	System.out.println("initialisé box");
    }
    
    /// initialisé les textfield avec les valeur de la chambre selectionnée
    public void getnum(Chambre m) {
    	try {
    	System.out.print("initalisation des valeur à modifier");
    	idnumMD.setText(Integer.toString(m.getNumeroCh()));
    	idetageMD.setText(Integer.toString(m.getEtage()));
    	this.numch = m.getNumeroCh();
    	// connection base de donnée pour recupere le type et le prix de la chambre selectionnée
    	Connection conn=Connexion.ConnexionBD();
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM `Chambre` WHERE numero = '"+m.getNumeroCh()+"'");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	while(rs.next()) {
    	idTypebox.setVisible(true);
    	idtypeactu.setText(rs.getString(4));
    	//type actuel
    	System.out.println(rs.getString(4));
    	}			
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
    // fermé fenetre
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
	
	// teste String
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
    }

