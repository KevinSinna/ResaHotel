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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Articles;
import model.Produit;
import model.Reservation;
import model.Sejour;
import model.Service;

public class PageFacturation extends Fenetre implements Initializable{
	
	ObservableList<String> typeItems = FXCollections.observableArrayList("Article","Service");

    
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
    		// ajout control si textfiel id est pas un entier 
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


        

 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		typeServ.setItems(typeItems);
		colNom.setCellValueFactory(new PropertyValueFactory<>("NomProd"));
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    	colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
	}


}