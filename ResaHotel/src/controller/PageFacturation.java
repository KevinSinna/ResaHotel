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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Articles;
import model.Chambre;
import model.Produit;
import model.Reservation;
import model.Sejour;
import model.Service;

public class PageFacturation extends Fenetre implements Initializable{
	
	ObservableList<String> typeItems = FXCollections.observableArrayList("Article","Service");

    double total;
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
    private Button btnFacturer;

    @FXML
    private TextField inpNom;
    

    @FXML
    private Label valTotal;

    @FXML
    void AjoutProd(ActionEvent event) {
    	if((inpNom.getText().isEmpty())||(inPrix.getText().isEmpty())||(typeServ.getValue() == null))
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs Vide");
    		alert.showAndWait();
    	}else if((isStringInt(inpNom.getText())==true)||(isStringInt(inPrix.getText())==false)) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs interdit");
    		alert.showAndWait();
    	}else {
    	String type;
    	//creation objet par rapport au choix du ChoiceBox
    	type = typeServ.getValue();
    	switch(type) {
    	case "Service"  : Service s = new Service(Double.parseDouble(inPrix.getText()),inpNom.getText()); 
    	//ajout dans le tableau
    	tabprod.getItems().add(s);
    	//initialisetion du prix par rapport au prix du Service ajouter
    	total += s.getPrix();
		String totaltxt = String.valueOf(total);
    	valTotal.setText(totaltxt);
    	break;
    	case "Article"  : Articles a = new Articles(Double.parseDouble(inPrix.getText()),inpNom.getText());
    	//ajout dans le tableau
    	tabprod.getItems().add(a);
    	//initialisation du prix par rapport a l'article ajoute 
    	total += a.getPrix();
		String totaltxt2 = String.valueOf(total);
    	valTotal.setText(totaltxt2);
    	break;
    	}}
    }
    
    @FXML
    void RechercheClient(ActionEvent event) throws SQLException {
    	ObservableList<Produit> c = FXCollections.observableArrayList();
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
    	PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT * FROM `Produit` WHERE `IdProd`IN (SELECT `IdProd` FROM `Sejour` WHERE `IdRes` IN ( SELECT `IdRes` FROM `Reservation` WHERE `IdClient`='"+inpId.getText()+"' AND `Statut`='Valider'))");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	while(rs.next()) {
    		Service s = new Service(rs.getDouble(2),String.valueOf(rs.getInt(3)));
    		//initialisé le prix par prix ajouté
    		total += rs.getDouble(2);
    		String totaltxt = String.valueOf(total);
        	valTotal.setText(totaltxt);
        		//ajouter dans le tableau
    		c.add(s);
    	}
    	ps.close();
    	//appel fonction pour initialisé le tableauview
    	init(c);
    	
    }}
// initialisé la tableau dans le tableauview
    private void init(ObservableList<Produit> c) {
		// TODO Auto-generated method stub
		tabprod.setItems(c);
	}

	public boolean isStringInt(String s)
	{
	    try
	    {
	    	// si c'est convertible en entier retourne vrai
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
	
	  @FXML
	    int Facture(ActionEvent event) {
		  if(tabprod.getItems().isEmpty()) {
				 Alert alert = new Alert(AlertType.INFORMATION);
		    		alert.setTitle("Information");
		    		alert.setHeaderText("Information");
		    		alert.setContentText("Tableau Produit Vide");
		    		alert.showAndWait();
		    		return 0;
			 }
		  
		  for(int i = 0 ; i < tabprod.getItems().size();i++) {
			  if(isStringInt(tabprod.getItems().get(i).getNomProd())==true) {
				Connection conn1=Connexion.ConnexionBD();
				PreparedStatement ps;
				try {
					ps = (PreparedStatement) conn1.prepareStatement(
							"UPDATE `Reservation` SET `Statut`= 'Payer' WHERE `IdRes`='"+tabprod.getItems().get(i).getNomProd()+"'");
					ps.executeUpdate();	
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		  
	    }
		  }
		return 1;}

}