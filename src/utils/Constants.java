package utils;

public class Constants {
    public static final String PIN = "1234";

    public static class view {
        public static final String LOGIN = "../view/loginView.fxml";
        public static final String PRE = "view/preView.fxml";
        public static final String MAIN = "../view/MainView.fxml";
        public static final String REGISTER = "../view/RegistrationView.fxml";
        public static final String ACCOUNT_DISPLAY = "../view/AccountDisplayView.fxml";
        public static final String ACCOUNT_DETAILS = "../view/AccountDetailsView.fxml";
        public static final String ACCOUNT_REGISTRATION = "../view/AccountRegistrationView.fxml";
        public static final String BALANCE_EDIT = "../view/BalanceEditView.fxml";
        public static final String TRANSACTION_HANDLE = "../view/TransactionHandleView.fxml";
        public static final String EMPLOYEE_DETAILS =  "../view/EmployeeDetailsView.fxml";
    }

    public static class storage {
        public static final String ACCOUNTS_FILE_PATH = "C:\\Users\\DAWID\\IdeaProjects\\bankSystem\\src\\accounts.ser";
        public static final String TRANSACTIONS_FILE_PATH = "C:\\Users\\DAWID\\IdeaProjects\\bankSystem\\src\\transactions.ser";
        public static final String EMPLOYEES_FILE_PATH = "C:\\Users\\DAWID\\IdeaProjects\\bankSystem\\src\\employees.ser";
    }

    private Constants(){}
}