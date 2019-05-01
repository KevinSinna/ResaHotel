package controller;

import java.util.ArrayList;

import javafx.scene.control.TableCell;
import model.Chambre;
import model.Reservation;

public class GerantprixTableCell extends TableCell<Reservation, ArrayList<Chambre>> {
	 protected void updateItem(ArrayList<Chambre> item, boolean empty) {
         super.updateItem(item, empty);
         java.lang.String text = null;
         if (!empty && item != null) {

        	 text = java.lang.String.valueOf(item.get(0).getTotalprvt());
        	 
             
         }
         setText(text);
     }
}
