package controller;


import exception.NotEnoughMoneyException;
import exception.StorageIOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.utility.Money;
import storage.Storage;
import utils.AlertDialog;
import utils.InfoDialog;

import java.net.URL;
import java.util.ResourceBundle;




public class AccountBalanceEditViewController extends Controller implements Initializable {

    private enum MessageType {
        SUCCESS,
        ERROR,
    }

    @FXML
    public Label balanceLabel;
    public Label messageLabel;
    public Label currentBalanceLabel;
    public TextField balanceTextField;
    public Button withdrawButton;
    public Button insertButton;
    public Button closeButton;
    public Button saveButton;

    private Account account;
    private Money editableBalance = new Money();

    public Account getAccount() { return account; }
    public void setAccount(Account account) {
        this.account = account;
    }

    public void onOperation(ActionEvent actionEvent){
        hideMessageLabel();
        try {
            Money input = new Money(this.balanceTextField.getText());
            if(actionEvent.getSource() == insertButton)
                this.editableBalance.insert(input);
            else if(actionEvent.getSource() == withdrawButton)
                this.editableBalance.withDraw(input);
            this.setEditableBalance(this.editableBalance);
            this.displayMessageLabel("Withdraw successful (save your changes)", MessageType.SUCCESS);
        } catch (NotEnoughMoneyException e){
            this.displayMessageLabel(e.getPresetErrorMessage(), MessageType.ERROR);
        } catch(Exception e) {
            this.displayMessageLabel(e.getMessage(), MessageType.ERROR);
        }
    }

    public void onSave(ActionEvent actionEvent){
        try {
            Storage.INSTANCE.save();
        } catch (StorageIOException e){
            new AlertDialog(e.getMessage()).showAndWait();
        }
        new InfoDialog("Success", "Changes have been saved!").showAndWait();
        currentBalanceLabel.setText("Saved: " + this.account.getBalance().toString());
    }

    public void onCancel(ActionEvent actionEvent){
        close(closeButton);
    }


    private Money getEditableBalance() { return editableBalance; }
    private void setEditableBalance(Money editableBalance) {
        this.editableBalance = (editableBalance);
        balanceLabel.setText("Now: " + getEditableBalance().toString());
    }

    private void close(Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private void displayMessageLabel(String message, MessageType type){
        if(type == MessageType.ERROR) {
            this.messageLabel.setStyle("-fx-text-fill: red");
            this.balanceTextField.setStyle("-fx-border-color: red");
        }
        else {
            this.balanceTextField.setStyle("");
            this.messageLabel.setStyle("-fx-text-fill: green");
        }
        this.messageLabel.setVisible(true);
        this.messageLabel.setText(message);
    }

    private void hideMessageLabel(){
        this.messageLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentBalanceLabel.setText("before: " + this.account.getBalance().toString());
        try {
            this.setEditableBalance(this.account.getBalance());
        } catch (Exception e) {
            new AlertDialog("wrong balance status!").showAndWait();
            this.close(this.closeButton);
        }
    }


}
