package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.AlertDialog;
import utils.Constants;
import utils.Modal;


public class PreViewController extends Controller {

    @FXML
    public Button loginButton;
    public Button registerButton;

    public void onLogin(ActionEvent actionEvent) {
        LoginViewController loginViewController = new LoginViewController();
        loginViewController.setPrimaryStage(this.getPrimaryStage());
        try {
            Modal modal = new Modal(Constants.view.LOGIN, loginViewController, "LOGIN");
            modal.showAndWait();
        } catch (Exception e){
            new AlertDialog("COULD NOT OPEN THE DIALOG: " + e.getMessage()).showAndWait();
        }
    }

    public void onRegister(ActionEvent actionEvent) {
        RegistrationViewController registrationViewController = new RegistrationViewController();
        registrationViewController.setPrimaryStage(this.getPrimaryStage());
        try {
            Modal modal = new Modal(Constants.view.REGISTER, registrationViewController, "REGISTER");
            modal.showAndWait();
        } catch (Exception e){
            new AlertDialog("COULD NOT OPEN THE DIALOG: " + e.getMessage()).showAndWait();
        }
    }
}
