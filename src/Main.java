import controller.PreViewController;
import exception.StorageIOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.Storage;
import utils.AlertDialog;
import utils.Constants;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource(Constants.view.PRE));
            root = loader.load();
        } catch(IOException e){
            new AlertDialog("COULD NOT LOAD THE MAIN VIEW: " + e.getMessage() + " APPLICATION SHUTDOWN").showAndWait();
            return;
        }
        PreViewController mvc = loader.getController();
        mvc.setPrimaryStage(primaryStage);
        try {
            Storage.INSTANCE.load();
        } catch (StorageIOException e){
            new AlertDialog(e.getMessage()).showAndWait();
            if(e.getStatusCode() == StorageIOException.StatusCode.CRITICAL)
                return;
        }
        primaryStage.setResizable(false);
        primaryStage.setTitle("Banking System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            Storage.INSTANCE.save();
        } catch (StorageIOException e){
            new AlertDialog(e.getMessage()).showAndWait();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
