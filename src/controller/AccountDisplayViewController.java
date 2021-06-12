package controller;

import exception.StorageIOException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import storage.collection.Accounts;
import storage.Storage;
import utils.AlertDialog;
import utils.ConfirmationDialog;
import utils.Constants;
import utils.Modal;

import java.net.URL;
import java.util.*;

public class AccountDisplayViewController extends Controller implements Initializable {

    @FXML
    public Button searchButton;
    public Button goBackButton;
    public ComboBox<String> comboBox;
    public TextField searchTextField;
    public TableView<Account> dataTable;
    public TableColumn<Account, String> IDColumn;
    public TableColumn<Account, String> nameColumn;
    public TableColumn<Account, String> surnameColumn;
    public TableColumn<Account, String> peselColumn;
    public TableColumn<Account, String> addressColumn;

    private final String[] comboBoxItemList = {"ALL", "ID", "NAME", "SURNAME", "PESEL", "COUNTRY", "CITY", "STREETADDRESS"};
    private String comboBoxValue = "";
    private void setComboBoxValue(String comboBoxValue) {
        this.comboBoxValue = comboBoxValue;
    }

    public void onGoBack(ActionEvent actionEvent){
        try {
            switchScene(Constants.view.MAIN);
        } catch (Exception e){
            new AlertDialog(e.getMessage()).showAndWait();
        }
    }

    public void onSearch(ActionEvent actionEvent){
        this.searchAndSetTable();
        this.resetForm();
    }

    public void comboBoxChoiceEvent(ActionEvent event)  {
        this.setComboBoxValue(this.comboBox.getValue());
    }

    private void searchAndSetTable(){
        Accounts accounts = Storage.INSTANCE.getAccounts();
        List<Account> res = accounts.SearchByKeyAndValue( this.comboBoxValue, this.searchTextField.getText());
        if(res.size() == 0)
            dataTable.getItems().clear();
        else
            dataTable.setItems(FXCollections.observableArrayList(res));
    }

    private void resetForm(){
        this.resetComboBox();
        this.searchTextField.setText("");
    }

    private void resetComboBox(){
        this.comboBoxValue = "ALL";
        this.comboBox.getItems().clear();
        this.comboBox.setItems(FXCollections.observableArrayList(this.comboBoxItemList));
        this.comboBox.getSelectionModel().selectFirst();
    }

    private void openDetailsModal(Account account)  {
        AccountDetailsViewController accountDetailsViewController = new AccountDetailsViewController();
        accountDetailsViewController.setPrimaryStage(this.getPrimaryStage());
        accountDetailsViewController.setAccount(account);
        try {
            Modal balanceEditModal = new Modal(Constants.view.ACCOUNT_DETAILS, accountDetailsViewController, "Account/" + account.getAccountId() + "/details");
            balanceEditModal.showAndWait();
        } catch (Exception e ) {
            new AlertDialog("COULD NOT OPEN THE MODAL WINDOW").showAndWait();
        }
    }

    private void openBalanceEditModal(Account account) {
        AccountBalanceEditViewController balanceEditViewController = new AccountBalanceEditViewController();
        balanceEditViewController.setPrimaryStage(this.getPrimaryStage());
        balanceEditViewController.setAccount(account);
        try {
            Modal balanceEditModal = new Modal(Constants.view.BALANCE_EDIT, balanceEditViewController ,"Account/" + account.getAccountId() + "/balance");
            balanceEditModal.showAndWait();
        } catch (Exception e ) {
            new AlertDialog("COULD NOT OPEN THE MODAL WINDOW").showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resetComboBox();
        this.dataTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.dataTable.setPlaceholder(new Label("No results from Query"));
        this.IDColumn.setCellValueFactory((new PropertyValueFactory<>("accountId")));
        this.nameColumn.setCellValueFactory( account -> {
            if (account.getValue() != null && account.getValue().getOwner() != null)
                return new SimpleStringProperty(account.getValue().getOwner().getFirstName().toString());
             else
                return new SimpleStringProperty("<No name>");
        });
        this.surnameColumn.setCellValueFactory(account -> {
            if (account.getValue() != null && account.getValue().getOwner() != null)
                return new SimpleStringProperty(account.getValue().getOwner().getSecondName().toString());
            else
                return new SimpleStringProperty("<No Surname>");
        });
        this.peselColumn.setCellValueFactory(account -> {
            if (account.getValue() != null && account.getValue().getOwner() != null)
                return new SimpleStringProperty(account.getValue().getOwner().getPesel());
            else
                return new SimpleStringProperty("<No pesel>");
        });
        this.addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        dataTable.setRowFactory(accountTableView -> {
            final TableRow<Account> tableRow = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Remove account");
            final MenuItem detailsMenuItem = new MenuItem("Show details");
            final MenuItem editBalanceMenuItem = new MenuItem("Withdraw / insert cash");

            removeMenuItem.setOnAction(actionEvent -> {
                Account account =  tableRow.getItem();
                ConfirmationDialog dialog = new ConfirmationDialog("Remove account", "Are you sure?");
                boolean ok = dialog.awaitResponse();
                if(ok)
                    this.dataTable.getItems().remove(account);
                Storage.INSTANCE.getAccounts().remove(tableRow.getItem());
                try {
                    Storage.INSTANCE.save();
                } catch (StorageIOException e) {
                    new AlertDialog(e.getMessage()).showAndWait();
                }
            });

            detailsMenuItem.setOnAction(actionEvent -> openDetailsModal(tableRow.getItem()));
            editBalanceMenuItem.setOnAction(actionEvent -> openBalanceEditModal(tableRow.getItem()));

            contextMenu.getItems().add(removeMenuItem);
            contextMenu.getItems().add(detailsMenuItem);
            contextMenu.getItems().add(editBalanceMenuItem);
            tableRow.contextMenuProperty().bind(
                    Bindings.when(tableRow.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return tableRow;
        });
    }
}
