package dao;

import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Chambre;

public class main extends Application {

	private static Stage stage;
	private AnchorPane root;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		stage=arg0;
		//stage.initStyle(StageStyle.UNDECORATED);
		root = FXMLLoader.load(getClass().getResource("/View/accueil.fxml"));
		Scene scene = new Scene(root);
        stage.setScene(scene);
stage.show();
	}

}
