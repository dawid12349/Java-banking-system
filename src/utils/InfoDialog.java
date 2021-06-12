package utils;

import javafx.scene.control.Alert;

public class InfoDialog extends Alert {

    public InfoDialog(String title, String headerText){
        super(AlertType.INFORMATION);
        this.setTitle(title);
        this.setHeaderText(headerText);
    }
}
