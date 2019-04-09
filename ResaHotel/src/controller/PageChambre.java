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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    private Button btnAjout;
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
    private TableView<Chambre> tabChamb;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableColumn<?, ?> colEtage;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colPrix;


    void init() throws SQLException {
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	colType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	colEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	colNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
    	tabChamb.setItems(getChambre());
    }
    
    public ObservableList<Chambre> getChambre() throws SQLException {
    	ObservableList c = FXCollections.observableArrayList();
    	Connection conn=Connexion.ConnexionBD();
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM Chambre");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	
    	try {
			while(rs.next()) {
				String type=rs.getString(4);
				switch(type){
					case "D": model.Double d = new model.Double(rs.getInt(1),rs.getInt(2));
					c.add(d);
					break;
					case "S": Simple s = new Simple(rs.getInt(1),rs.getInt(2));
				    c.add(s);
					case "N": Normal n = new Normal(rs.getInt(1),rs.getInt(2));
				    c.add(n);
					case "P": Presidentiel p = new Presidentiel(rs.getInt(1),rs.getInt(2));
				    c.add(p);
				}
	
			 
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return c;
    }
    private void swicht(String type) {
		// TODO Auto-generated method stub
		
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


