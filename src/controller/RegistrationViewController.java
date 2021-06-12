package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import model.utility.Email;
import model.utility.Name;
import storage.collection.Employees;
import storage.Storage;
import utils.AlertDialog;
import utils.Constants;
import exception.ModelSetterException;
import utils.Preferences;

public class RegistrationViewController extends Controller {
    @FXML
    public Button submitButton;
    public TextField pinTextField;
    public PasswordField passwordTextField;
    public TextField emailTextField;
    public TextField secondNameTextField;
    public TextField fistNameTextField;
    public Button closeButton;

    public void onClose(ActionEvent actionEvent) {
        close();
    }

    public void onSubmit(ActionEvent actionEvent) {
        try {
            Storage storage = Storage.INSTANCE;
            if (!pinTextField.getText().equals(Constants.PIN))
                throw new Exception("Wrong authentication PIN!");
            Employee newEmployee = getFormData();
            Employees employees = storage.getEmployees();
            Employee found = employees.findByEmail(newEmployee.getEmail().toString());
            if(found != null)
                throw new Exception("Employee already exists!");
            employees.add(newEmployee);
            storage.save();
            Preferences.INSTANCE.setEmployee(newEmployee);
            close();
            switchScene(Constants.view.MAIN);
        } catch (ModelSetterException e) {
           new AlertDialog(e.getPresetErrorMessage()).showAndWait();
        } catch (Exception e){
            new AlertDialog( e.getMessage()).showAndWait();
        }
    }

    private Employee getFormData() throws ModelSetterException {
        String fistNameInput = this.fistNameTextField.getText();
        String secondNameInput = this.secondNameTextField.getText();
        String emailInput = this.emailTextField.getText();
        String passwordInput = this.passwordTextField.getText();
        return new Employee(new Name(fistNameInput), new Name(secondNameInput), new Email(emailInput), passwordInput);
    }

    private void close(){
        Stage  stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }
}
