package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.Customer;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class CustomerController {
    @FXML private Label nameLabel;
    @FXML private Label idLabel;
    @FXML private Label balanceLabel;
    @FXML private TextField balanceTextField;
    @FXML private Label errorLabel;

    private Datasource<CustomerList> datasource;
    private CustomerList customerList;
    private Customer customer;

    @FXML
    public void initialize() {
        errorLabel.setText("");
        datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        //Customer customer = new Customer("6410400001", "Tony Stark", "Tony");
        customerList = datasource.readData();
        customer = (Customer) FXRouter.getData();
        showCustomer(customer);
    }

    private void showCustomer(Customer customer) {
        nameLabel.setText(customer.getName());
        idLabel.setText(customer.getId());
        balanceLabel.setText("" + customer.getBalance());
    }


    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void GiveBalanceButton() {
        String balanceString = balanceTextField.getText().trim();

        if (balanceString.equals("")) {
            errorLabel.setText("balance is required");
            return;
        }
        try {
            double balance = Double.parseDouble(balanceString);
            if (balance < 0) {
                errorLabel.setText("balance must be positive number");
                return;
            }
            errorLabel.setText("");
            customerList.giveBalanceToId(customer.getId(), balance);
            balanceTextField.clear();
            datasource.writeData(customerList);
            showCustomer(customer);

        } catch (NumberFormatException e) {
            errorLabel.setText("balance must be number");
        }
    }

    @FXML
    public void onGoButton() {
        try {
            FXRouter.goTo("summary", customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
