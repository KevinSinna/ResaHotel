package controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EtapeReserv {

    @FXML
    private TableView<?> tabDispo;

    @FXML
    private TableColumn<?, ?> dispNum;

    @FXML
    private TableColumn<?, ?> dispEtage;

    @FXML
    private TableColumn<?, ?> dispType;

    @FXML
    private TableColumn<?, ?> dispPrix;

    @FXML
    private TableView<?> tabSelect;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableColumn<?, ?> colEtage;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colPrix;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnSupr;

    @FXML
    private Button btnSuivant;

    @FXML
    private Button btnAnnul;

    @FXML
    void AjoutTab(ActionEvent event) {

    }

    @FXML
    void Suivant(ActionEvent event) {

    }

    @FXML
    void SuprTab(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

	public void initChamb(LocalDate debut, LocalDate fin) {
		// TODO Auto-generated method stub
		System.out.println(fin);
		System.out.println(debut);
	}

}