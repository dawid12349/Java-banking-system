package utils;

import javafx.scene.control.Alert;

public class AlertDialog extends Alert{
    public AlertDialog(String headerText) {
        super(AlertType.ERROR);
        this.setTitle("ERROR");
        this.setHeaderText(headerText);
    }
}
