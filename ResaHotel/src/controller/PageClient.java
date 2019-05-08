package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import dao.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;

public class PageClient extends Fenetre implements Initializable, Observer {
	Stage stage; 
	Parent root;
	@FXML
	private TableView<Client> TabViewClient;

	@FXML
	private TableColumn<Client, Integer> ColIdClient;

	@FXML
	private TableColumn<Client, String> ColNom;

	@FXML
	private TableColumn<Client, String> ColPrenom;	
    
    @FXML
    private Button btnAjoute;

    @FXML
    private Button btnSupr;

    @FXML
    private TextField idprenom;
    @FXML
    private TextField idnom;
    // methode changer les page
   
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Ajotuer un client
    @FXML
    void AjouteClient(ActionEvent event) throws SQLException {
    	if ((idnom.getText().isEmpty())||(idprenom.getText().isEmpty())) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erreur");
    		alert.setHeaderText("Saisie incorect");
    		alert.setContentText("Champs vide ");
    		alert.showAndWait();
    	}else if(((isStringInt(idnom.getText())==true)|| (isStringInt(idprenom.getText()))==true)){
    		{
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Erreur");
        		alert.setHeaderText("Saisie incorect");
        		alert.setContentText("Champs interdit");
        		alert.showAndWait();
    		}
    			
    	}else {
    	Client c = new Client(idnom.getText(),idprenom.getText());
    	c.AjoutClient();
    	
    	//TabViewCleint.getItems().add(c);
    	init();
    	}
    }
    // methode de verification String
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
	
	// Suprimer client
    @FXML
    void SuprClient(ActionEvent event) throws SQLException {
    	if((TabViewClient.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner un client à suprimer");
    		alert.showAndWait();
    	}else {
    	Client n = TabViewClient.getSelectionModel().getSelectedItem();
    	n.SuprClient();
    	TabViewClient.getItems().remove(n);
    	}	
    }
    //initialisé le tableauview
    void init() throws SQLException {
    	ColNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
    	ColPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
    	ColIdClient.setCellValueFactory(new PropertyValueFactory<>("IdClient"));
    	TabViewClient.setItems(getClient());
    }
    
    // recuperer base de donnée les donnée afin de initialiser le tableau
    public ObservableList<Client> getClient() {
    	ObservableList<Client> c = FXCollections.observableArrayList();
    	Connection con = Connexion.ConnexionBD();
    	try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement("SELECT * FROM `Client` ");
			ResultSet rs=(ResultSet) ps.executeQuery();
			while(rs.next()) {
				Client p = new Client(rs.getInt(1),rs.getString(2),rs.getString(3));
				c.add(p);
				
			}
			ps.close();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return c;
    	
    }
    
    // initalise la page
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
