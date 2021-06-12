package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import utils.Constants;
import utils.Modal;
import utils.Preferences;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends Controller implements Initializable {

    @FXML
    public Button accountAddButton;
    public Button displayAccountButton;
    public Button transactionButton;
    public Button employeeInfoButton;
    public Label welcomeLabel;

    @FXML
    public void handleButtonClick(ActionEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == accountAddButton){
           switchScene(Constants.view.ACCOUNT_REGISTRATION);
        } else if(mouseEvent.getSource() == displayAccountButton){
            switchScene(Constants.view.ACCOUNT_DISPLAY);
        } else if(mouseEvent.getSource() == transactionButton){
            switchScene(Constants.view.TRANSACTION_HANDLE);
        } else if(mouseEvent.getSource() == employeeInfoButton){
            EmployeeDetailsViewController employeeDetailsViewController = new EmployeeDetailsViewController(this.getPrimaryStage());
            Modal modal = new Modal(Constants.view.EMPLOYEE_DETAILS, employeeDetailsViewController, "Employee info");
            modal.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String employeeCredentials = Preferences.INSTANCE.getEmployee().getCredientials();
        this.welcomeLabel.setText("Welcome: " + employeeCredentials);
    }

}
