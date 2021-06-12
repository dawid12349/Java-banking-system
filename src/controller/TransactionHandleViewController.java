package controller;

import exception.AccountNotFoundException;
import exception.NotEnoughMoneyException;
import exception.StorageIOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaction;
import model.utility.Money;
import storage.Storage;
import utils.AlertDialog;
import utils.Constants;
import exception.ModelSetterException;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionHandleViewController extends Controller implements Initializable {

    @FXML
    public Button goBackButton;
    public TextField senderIDTextField;
    public TextField receiverIDTextField;
    public TextField moneyTextField;
    public Button submitButton;
    public Button resetButton;
    public TableView<Transaction> dataTable;
    public TableColumn<Transaction, String> IDColumn;
    public TableColumn<Transaction, String> senderIDColumn;
    public TableColumn<Transaction, String> receiverIDColumn;
    public TableColumn<Transaction, String> moneyColumn;
    public TableColumn<Transaction, String> creationDateColumn;

    private final ObservableList<Transaction> rowList = FXCollections.observableList(Storage.INSTANCE.getTransactions());

    public void onGoBack(ActionEvent actionEvent){
        try {
            switchScene(Constants.view.MAIN);
        } catch (Exception e){
            new AlertDialog(e.getMessage()).showAndWait();
        }
    }

    public void onSubmit(ActionEvent actionEvent){
        try {
            Transaction transaction = getFormData();
            Storage storage = Storage.INSTANCE;
            storage.getAccounts().applyTransaction(transaction);
            storage.getTransactions().add(transaction);
            storage.save();
            tableAddRow(transaction);
        } catch (ModelSetterException e){
            new AlertDialog(e.getPresetErrorMessage()).showAndWait();
        } catch (AccountNotFoundException e) {
            new AlertDialog(e.getMessage() + e.getUserID()).showAndWait();
        } catch (NotEnoughMoneyException e){
            new AlertDialog(e.getPresetErrorMessage()).showAndWait();
        } catch (Exception e) {
            new AlertDialog(e.getMessage()).showAndWait();
        }
    }

    public void onReset(ActionEvent actionEvent){
        resetForm();
    }

    private Transaction getFormData() throws Exception, ModelSetterException {
        Transaction transaction = new Transaction();
        transaction.setSenderID(Integer.parseInt(senderIDTextField.getText()));
        transaction.setReceiverID(Integer.parseInt(receiverIDTextField.getText()));
        transaction.setMoney(new Money(moneyTextField.getText()));
        transaction.setTransactionID(Storage.INSTANCE.getTransactions().getFreeID());
        return transaction;
    }

    private void tableAddRow(Transaction transaction){
        rowList.add(transaction);
        rowList.remove(rowList.size() - 1);
    }

    private void resetForm(){
        this.senderIDTextField.setText("");
        this.moneyTextField.setText("");
        this.receiverIDTextField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dataTable.setPlaceholder(new Label("No transactions found"));
        this.IDColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        this.senderIDColumn.setCellValueFactory(new PropertyValueFactory<>("senderID"));
        this.receiverIDColumn.setCellValueFactory(new PropertyValueFactory<>("receiverID"));
        this.moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        this.creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        this.dataTable.setItems(rowList);
    }
}
