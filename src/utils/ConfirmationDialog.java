package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ConfirmationDialog extends Alert {

    public ConfirmationDialog(String title, String headerText ) {
        super(AlertType.CONFIRMATION);
        this.setTitle(title);
        this.setHeaderText(headerText);
    }

    public boolean awaitResponse(){
        Optional<ButtonType> result = this.showAndWait();
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }

}
