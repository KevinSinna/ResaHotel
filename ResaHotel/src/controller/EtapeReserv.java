package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Chambre;
import model.Client;
import model.Normal;
import model.Presidentiel;
import model.Reservation;
import model.Simple;

public class EtapeReserv implements Initializable {
	Stage stage;
	public LocalDate debut;
	public LocalDate fin;
	@FXML
	private TextField inpID;
	
	@FXML
	private Label Prenom;

	@FXML
	private Label Nom;

    @FXML
    private TableView<Chambre> tabDispo;

    @FXML
    private TableColumn<Chambre, Integer> dispNum;

    @FXML
    private TableColumn<Chambre, Integer> dispEtage;

    @FXML
    private TableColumn<Chambre, String> dispType;

    @FXML
    private TableColumn<Chambre, Double> dispPrix;

    @FXML
    private TableView<Chambre> tabSelect;

    @FXML
    private TableColumn<Chambre, Integer> colNum;

    @FXML
    private TableColumn<Chambre, Integer> colEtage;

    @FXML
    private TableColumn<Chambre, String> colType;

    @FXML
    private TableColumn<Chambre,Double> colPrix;
    
    @FXML
    private TableColumn<Chambre, Double> colTotal;
    
    @FXML
    private TableColumn<Chambre, Double> dispTotal;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnSupr;

    @FXML
    private Button btnSuivant;

    @FXML
    private Button btnAnnul;
    // ajoute a ma liste de chambre selectionnée
    @FXML
    void AjoutTab(ActionEvent event) {
    	//verifie si une chambre est selectionné
    	if((tabDispo.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre ");
    		alert.showAndWait();
    	}else {
    		// initialise tableau de selection chambre
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	colType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	colEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	colNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
    	// chambre dispo selectionnée
    	Chambre n = tabDispo.getSelectionModel().getSelectedItem();	
    	// ajoute dans tab chambre selectionner
    	System.out.println(n.getTotalprvt());
    	tabSelect.getItems().add(n);
    	// retire dans tab chambre dispo
    	tabDispo.getItems().remove(n);
    }}


	@FXML
    void Suivant(ActionEvent event){
    	// verification des information entrée 
    	if((inpID.getText().isEmpty())||(tabSelect.getItems()==null)) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error");
    		alert.setContentText("Champs vide");
    		alert.showAndWait();	
    	}else if(isStringInt(inpID.getText())==false) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs interdit");
    		alert.showAndWait();
    	}else {
    		Client client = new Client(Integer.parseInt(inpID.getText()),Nom.getText(),Prenom.getText());
    		Reservation res = new Reservation(client,debut,fin,"En Attente");
    		// Ajout dans objet toute les chambre reservé
    		for(int i=0; i<tabSelect.getItems().size();i++) {
    		Chambre n = tabSelect.getItems().get(i);
    		res.AjoutChambre(n);
    		}
    		res.AjoutBD();
    	}
    }
    @FXML
    void RechClient(ActionEvent event) {
    	// verification des information entrée 
    	if(inpID.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error");
    		alert.setContentText("Champs vide");
    		alert.showAndWait();	
    	}else if(isStringInt(inpID.getText())==false) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Champs interdit");
    		alert.showAndWait();
    	}else {
    		Connection con = Connexion.ConnexionBD();
    		System.out.println(inpID.getText());
			try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM `Client` WHERE `IdClient`='"+inpID.getText()+"'");
    		ResultSet rs = (ResultSet) ps.executeQuery();
    		if (rs.next()) {
    		Nom.setText(rs.getString(2));
    		Prenom.setText(rs.getString(3));
    		}
    		ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}
    	
    	
    }


    @FXML
    void SuprTab(ActionEvent event) {
     	//verifie si une chambre est selectionnée
    	if((tabSelect.getSelectionModel().getSelectedItem())==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Information");
    		alert.setHeaderText("Information");
    		alert.setContentText("Selectionner une chambre à retirer");
    		alert.showAndWait();
    	}else {
    	Chambre n = tabSelect.getSelectionModel().getSelectedItem();	
    	tabDispo.getItems().add(n);
    	tabSelect.getItems().remove(n);
    }
    }
    //Fermer la page
    @FXML
    void close(ActionEvent event) {
    	stage=(Stage) btnAnnul.getScene().getWindow();   	
    	stage.close();
    }
    // recuperation des date
  //initialisation tableau Chambre disponible
	public void initChamb(LocalDate debut, LocalDate fin) throws SQLException {
		// TODO Auto-generated method stub
		this.debut = debut;
		this.fin = fin;
		System.out.println(fin);
		System.out.println(debut);
		dispPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	dispType.setCellValueFactory(new PropertyValueFactory<>("typech"));
    	dispEtage.setCellValueFactory(new PropertyValueFactory<>("Etage"));
    	dispNum.setCellValueFactory(new PropertyValueFactory<>("NumeroCh"));
    	dispTotal.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
    	tabDispo.setItems(getChambreLibre(debut,fin));
	}
	
      // renvoye Tableau Chambre disponible
	public ObservableList<Chambre> getChambreLibre(LocalDate debut, LocalDate fin) throws SQLException {
		System.out.println(fin);
		System.out.println(debut);
		ObservableList<Chambre> c = FXCollections.observableArrayList();
		Connection conn=Connexion.ConnexionBD();
		System.out.println("'"+this.debut+"','"+this.fin+"'");
		double days = ChronoUnit.DAYS.between(debut,fin);
		System.out.println(days);
    	PreparedStatement ps=(PreparedStatement) conn.prepareStatement("SELECT * FROM `Chambre` WHERE `numero` NOT IN (SELECT `numero` FROM `Reservation` WHERE (`DateDeb` BETWEEN '"+this.debut+"' AND '"+this.fin+"') OR (`DateFin` BETWEEN '"+this.debut+"' AND '"+this.fin+"') )");
    	ResultSet rs = (ResultSet) ps.executeQuery();
    	while(rs.next()) {
			String type=rs.getString(4);
			switch(type){
				case "Double": model.Double d = new model.Double(rs.getInt(1),rs.getInt(2));
				d.getTotal(model.Double.getPrix(), days);
				System.out.println(d.getTotalprvt());
				c.add(d);
				break;
				case "Simple": Simple s = new Simple(rs.getInt(1),rs.getInt(2));
				s.getTotal(Simple.getPrix(), days);
			    c.add(s);
			    break;
				case "Normal": Normal n = new Normal(rs.getInt(1),rs.getInt(2));
				n.getTotal(Normal.getPrix(), days);
			    c.add(n);
			    break;
				case "Presidentiel": Presidentiel p = new Presidentiel(rs.getInt(1),rs.getInt(2));
				p.getTotal(Presidentiel.getPrix(), days);
			    c.add(p);
			    break;
			}		 
    	}
		ps.close();
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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