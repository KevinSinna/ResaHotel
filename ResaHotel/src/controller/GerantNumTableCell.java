package controller;
import java.util.ArrayList;

import javafx.scene.control.TableCell;
import model.Chambre;
import model.Reservation;


public class GerantNumTableCell  extends TableCell<Reservation, ArrayList<Chambre>> {
	 public static enum Flavor {
         FORPRIX, NUMERO;
     }
	 public GerantNumTableCell(Flavor flavor) {
         this.flavor = flavor;
     }
	 private Flavor flavor;
	 protected void updateItem(ArrayList<Chambre> item, boolean empty) {
         super.updateItem(item, empty);
         Chambre tem = item.get(1);
         String text = null;
         if (!empty && tem != null) {
        	 switch(flavor) {
        	 case FORPRIX : text = String.valueOf(tem.getTotalprvt());
        	 case NUMERO : text = String.valueOf(tem.getNumeroCh());
        	 }
             
         }
         setText(text);
     }
}
