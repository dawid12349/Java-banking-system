package controller;

import exception.EmployeeNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import storage.collection.Employees;
import storage.Storage;
import utils.AlertDialog;
import utils.Constants;
import utils.Preferences;

import java.io.IOException;

public class LoginViewController extends Controller {
    @FXML
    public TextField emailTextField;
    public PasswordField passwordTextField;
    public Button loginButton;
    public Button closeButton;

    private static class FormData {
        public String inputEmail;
        public String inputPassword;
        public FormData(String inputEmail, String inputPassword){ this.inputPassword = inputPassword; this.inputEmail = inputEmail; }
    }

    public void onClose(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void onLogin(ActionEvent actionEvent) {
        try {
            FormData formData = getFormData();
            Employees employees = Storage.INSTANCE.getEmployees();
            Employee employee = employees.findByEmail(formData.inputEmail);
            if (employee == null)
                throw new EmployeeNotFoundException("", formData.inputEmail);
            if (!employee.getPassword().equals(formData.inputPassword))
                throw new Exception("PASSWORD DOES NOT MATCH");
            Preferences.INSTANCE.setEmployee(employee);
            close();
            switchScene(Constants.view.MAIN);
        } catch (EmployeeNotFoundException e) {
            new AlertDialog(e.getPresetErrorMessage()).showAndWait();
        }  catch (Exception e){
            new AlertDialog(e.getMessage()).showAndWait();
        }
    }

    private FormData getFormData(){
        return new FormData(emailTextField.getText(), passwordTextField.getText());
    }

    private void  close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
