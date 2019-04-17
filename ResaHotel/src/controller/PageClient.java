package controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Chambre;
import model.Client;

public class PageClient implements Initializable, Observer {
	Stage stage; 
	Parent root;
	@FXML
	private TableView<Client> TabViewCleint;

	@FXML
	private TableColumn<?, ?> ColIdCleint;

	@FXML
	private TableColumn<?, ?> ColNom;

	@FXML
	private TableColumn<?, ?> ColPrenom;	

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
    private TextField idprenom;
    @FXML
    private TextField idnom;
    
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
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		
	}
    @FXML
    void AjouteClient(ActionEvent event) {
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
    	TabViewCleint.getItems().add(c);
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
    void SuprClient(ActionEvent event) throws SQLException {
    	if((TabViewCleint.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre à suprimer");
    		alert.showAndWait();
    	}else {
    	Client n = TabViewCleint.getSelectionModel().getSelectedItem();
    	n.SuprClient();
    	TabViewCleint.getItems().remove(n);
    	}	
    }
    void init() throws SQLException {
    	ColNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
    	ColPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
    	ColIdCleint.setCellValueFactory(new PropertyValueFactory<>("IdClient"));
    	TabViewCleint.setItems(getClient());
    }
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
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return c;
    	
    }

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
