package utils;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Modal extends Stage {

    public Modal(String fxml, Controller controller, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxml)));
        loader.setController(controller);
        initOwner(controller.getPrimaryStage());
        initModality(Modality.APPLICATION_MODAL);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        this.setTitle(title);
    }

}
