package controller;

import exception.StorageIOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import model.utility.*;
import storage.Storage;
import utils.AlertDialog;
import utils.Constants;
import exception.ModelSetterException;
import utils.InfoDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountRegistrationViewController extends Controller implements Initializable {
    @FXML
    public Button submitButton;
    public Button resetButton;
    public Button goBackButton;
    public TextField nameTextField;
    public TextField surnameTextField;
    public TextField emailTextField;
    public TextField peselTextField;
    public TextField telephoneNumberTextField;
    public TextField balanceTextField;
    public TextField streetTextField;
    public TextField cityTextField;
    public TextField countryTextField;


    public void onReset(ActionEvent actionEvent)  {
        resetFormData();
    }

    public void onSubmit(ActionEvent actionEvent) {
        try {
            Storage storage = Storage.INSTANCE;
            storage.getAccounts().add(getFormData());
            storage.save();
            new InfoDialog("Success", "Account has been registered").showAndWait();
            resetFormData();
        } catch (ModelSetterException e){
            new AlertDialog( e.getPresetErrorMessage()).showAndWait();
        } catch (Exception e){
            new AlertDialog(e.getMessage()).showAndWait();
        }
    }

    public void onGoBack(ActionEvent actionEvent) {
        try {
            switchScene(Constants.view.MAIN);
        } catch (Exception e){
            new AlertDialog( e.getMessage()).showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    private void resetFormData(){
        this.nameTextField.setText("");
        this.surnameTextField.setText("");
        this.emailTextField.setText("");
        this.peselTextField.setText("");
        this.telephoneNumberTextField.setText("");
        this.streetTextField.setText("");
        this.countryTextField.setText("");
        this.balanceTextField.setText("");
        this.cityTextField.setText("");
    }

    private Account getFormData() throws ModelSetterException {
        Account account = new Account();
        account.setOwner(getPersonFormData());
        account.setAddress(getAddressFormData());
        account.setBalance(new Money(balanceTextField.getText()));
        account.setAccountId(Storage.INSTANCE.getAccounts().getFreeID());
        return account;
    }

    private Address getAddressFormData() throws ModelSetterException {
        Address address = new Address();
        address.setState(countryTextField.getText());
        address.setStreetAddress(streetTextField.getText());
        address.setCity(cityTextField.getText());
        return address;
    }

    private Person getPersonFormData() throws ModelSetterException {
        Person client = new Person();
        client.setFirstName(new Name(nameTextField.getText()));
        client.setSecondName(new Name(surnameTextField.getText()));
        client.setEmail(new Email(emailTextField.getText()));
        client.setPesel(peselTextField.getText());
        client.setTelephoneNumber(telephoneNumberTextField.getText());
        return client;
    }

}
