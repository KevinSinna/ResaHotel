package controller;


import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Articles;
import model.Produit;
import model.Reservation;
import model.Sejour;
import model.Service;

public class PageFacturation implements Initializable{
	Stage stage; 
	Parent root;
	ObservableList<String> typeItems = FXCollections.observableArrayList("Article","Service");

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
    private TextField inpId;

    @FXML
    private Button btnIdClient;

    @FXML
    private TableView<Produit> tabprod;

    @FXML
    private TableColumn<Produit, String> colType;

    @FXML
    private TableColumn<Produit, String> colNom;

    @FXML
    private TableColumn<Produit, Double> colPrix;
    

    @FXML
    private ChoiceBox<String> typeServ;

    @FXML
    private Button btnAjout;

    @FXML
    private TextField inPrix;

    @FXML
    private TextField inpNom;

    @FXML
    void AjoutProd(ActionEvent event) {
    	String type;
    	type = typeServ.getValue();
    	switch(type) {
    	case "Service"  : Service s = new Service(Double.parseDouble(inPrix.getText()),inpNom.getText()); 
    	tabprod.getItems().add(s);
    	break;
    	case "Article"  : Articles a = new Articles(Double.parseDouble(inPrix.getText()),inpNom.getText());
    	tabprod.getItems().add(a);
    	break;
    	}
    }
    
    @FXML
    void RechercheClient(ActionEvent event) throws SQLException {
    	if(inpId.getText().isEmpty())
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs Vide");
    		alert.showAndWait();
    	}else if((isStringInt(inpId.getText())==false)) {
    		// ajout control si textfiel numero et etage pas un entier 
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs interdit");
    		alert.showAndWait();
    	}else {
    	Connection con = Connexion.ConnexionBD();
    	PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT `IdRes`,`Numero`,`Total` FROM `Reservation` WHERE `IdClient`='"+inpId.getText()+"'");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	while(rs.next()) {
    		Service s = new Service(rs.getDouble(3),String.valueOf(rs.getInt(2)));
    		//s.Ajout(s.getType(),s.getNomProd(),s.getPrix(),rs.getInt(1));
    		// Boutton facturé
    		//Reservation r = new Reservation();
    		//r.setIdRes(rs.getInt(1));
    		//Sejour sej = new Sejour(r);
    		//sej.AjoutProdList(s);
    		//sej.AjoutBD();
    		tabprod.getItems().add(s);
    	}
    	ps.close();
    	
    	
    }}

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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		typeServ.setItems(typeItems);
		colNom.setCellValueFactory(new PropertyValueFactory<>("NomProd"));
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    	colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
	}


}