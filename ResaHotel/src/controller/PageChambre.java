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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Chambre;
import model.Normal;
import model.Presidentiel;
import model.Simple;

public class PageChambre implements Initializable{
	
	Stage stage; 
	
	Parent root;
	@FXML
	private Button btnModif;
	@FXML
    private Button btnAjout;
    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnGestch;
    
    @FXML
    private Button btnSupr;

    @FXML
    private Button btnGestres;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnFact;
    @FXML
	protected TableView<Chambre> tabChamb;

    @FXML
    protected TableColumn<?, ?> colNum;

    @FXML
    protected TableColumn<?, ?> colEtage;

    @FXML
    protected TableColumn<?, ?> colType;

    @FXML
    protected TableColumn<?, ?> colPrix;
    	
    void init() throws SQLException {
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	colType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	colEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	colNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
    	tabChamb.setItems(getChambre());
    }
    
    public ObservableList<Chambre> getChambre() throws SQLException {
    	ObservableList<Chambre> c = FXCollections.observableArrayList();
    	Connection conn=Connexion.ConnexionBD();
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM Chambre");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	
    	try {
			while(rs.next()) {
				String type=rs.getString(4);
				switch(type){
					case "Double": model.Double d = new model.Double(rs.getInt(1),rs.getInt(2));
					c.add(d);
					break;
					case "Simple": Simple s = new Simple(rs.getInt(1),rs.getInt(2));
				    c.add(s);
				    break;
					case "Normal": Normal n = new Normal(rs.getInt(1),rs.getInt(2));
				    c.add(n);
				    break;
					case "Presidentiel": Presidentiel p = new Presidentiel(rs.getInt(1),rs.getInt(2));
				    c.add(p);
				    break;
				}
	
			 
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return c;
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
    @FXML
    void AjoutChambre(ActionEvent event) throws IOException {
    	Stage windaj = new Stage();
    	windaj.initStyle(StageStyle.UNDECORATED);
    	root = FXMLLoader.load(getClass().getResource("/View/formchambre.fxml"));
    	Scene form = new Scene(root);
    	windaj.setScene(form);
    	windaj.show(); 	
    }
    @FXML
    void Suprimer(ActionEvent event) throws SQLException {
    	if((tabChamb.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre à suprimer");
    		alert.showAndWait();
    	}else {
    	Chambre n = tabChamb.getSelectionModel().getSelectedItem();	
    	try {
    		Connection conn=Connexion.ConnexionBD();
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement("DELETE FROM `Chambre` WHERE numero = '"+n.getNumeroCh()+"'");		
			ps.executeUpdate();			
				ps.close();
			} catch (Exception e3) {
				e3.printStackTrace();
	}
    	init();
    	}
    	
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
    @FXML
    void ModifChambre(ActionEvent event) {
    	if((tabChamb.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre à modifier");
    		alert.showAndWait();
    	}else {
    	Chambre mod = tabChamb.getSelectionModel().getSelectedItem();	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/formmodif.fxml"));
        Parent root;
		try {
			root = loader.load();       
        //Controller ModifChambre
        ModifChambre modifchambre = loader.getController();
        //Envoye de l'element
        modifchambre.getnum(mod.getNumeroCh());
                  
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Second Window");
        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }}
    
}


