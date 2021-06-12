package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Employee;
import utils.Preferences;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDetailsViewController extends Controller implements Initializable {
    public Label firstNameLabel;
    public Label secondNameLabel;
    public Label emailLabel;

    public EmployeeDetailsViewController(Stage stage){
        super(stage);
    }

    public EmployeeDetailsViewController(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee = Preferences.INSTANCE.getEmployee();
        firstNameLabel.setText(" " + employee.getFirstName());
        secondNameLabel.setText(" " + employee.getSecondName());
        emailLabel.setText(" " + employee.getEmail());
    }
}
