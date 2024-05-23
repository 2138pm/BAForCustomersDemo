package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Customer;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class SummaryController {
    @FXML private Label usernameLabel;
    @FXML private Label idLabel;
    @FXML private Label balanceLabel;

    private Datasource<CustomerList> datasource;
    private CustomerList customerList;
    private Customer customer;

    @FXML
    public void initialize() {
        datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();
        customer = (Customer) FXRouter.getData();
        showCustomer(customer);

    }

    private void showCustomer(Customer customer) {
        usernameLabel.setText(customer.getName());
        idLabel.setText(customer.getId());
        balanceLabel.setText("" + customer.getBalance());
    }

    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("customer-profile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void onTransferButtonClick() {
        try {
            FXRouter.goTo("transfer", customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
