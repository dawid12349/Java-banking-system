package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Account;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountDetailsViewController extends Controller implements Initializable {

    @FXML
    public Label IDLabelValue;
    public Label nameLabelValue;
    public Label surnameLabelValue;
    public Label peselLabelValue;
    public Label emailLabelValue;
    public Label telephoneNumberLabelValue;
    public Label countryLabelValue;
    public Label cityLabelValue;
    public Label streetAddressLabelValue;
    public Label balanceLabelValue;

    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.IDLabelValue.setText(account.getAccountId().toString());
        this.nameLabelValue.setText(account.getOwner().getFirstName().toString());
        this.surnameLabelValue.setText(account.getOwner().getSecondName().toString());
        this.peselLabelValue.setText(account.getOwner().getPesel());
        this.emailLabelValue.setText(account.getOwner().getEmail().toString());
        this.telephoneNumberLabelValue.setText(account.getOwner().getTelephoneNumber());
        this.countryLabelValue.setText(account.getAddress().getState());
        this.cityLabelValue.setText(account.getAddress().getCity());
        this.streetAddressLabelValue.setText(account.getAddress().getStreetAddress());
        this.balanceLabelValue.setText(account.getBalance().toString());
    }


}
