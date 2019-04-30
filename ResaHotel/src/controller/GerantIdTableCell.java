package controller;

import javafx.scene.control.TableCell;
import model.Client;
import model.Reservation;

public class GerantIdTableCell  extends TableCell<Reservation, Client>{
	  @Override
      protected void updateItem(Client item, boolean empty) {
          super.updateItem(item, empty);
          String text = null;
          if (!empty && item != null) {
              text = String.valueOf(item.getIdClient());
          }
          setText(text);
      }
}
