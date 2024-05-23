package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.Customer;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.exception.AuthenticationFailedException;

import java.io.IOException;

public class TransferController {
    @FXML private TextField usernameTextField;
    @FXML private TextField amountTextField;
    @FXML private Label errorLabel;
    @FXML private Label  nameLabel;
    @FXML private Label  balanceLabel;

    private Datasource<CustomerList> datasource;
    private CustomerList customerList;
    private Customer customer;


    @FXML
    public void initialize() throws IOException {
        datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();
        customer = (Customer) FXRouter.getData();
        //showCustomer(customer);

    }

    @FXML public void onOKButtonClick(ActionEvent e) throws IOException{
        checkUsername();
    }

    private void checkUsername() throws IOException{
        Datasource<CustomerList> datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();
        customer = (Customer) FXRouter.getData();

        errorLabel.setText("");
        String username = usernameTextField.getText();
        String amountString = amountTextField.getText().trim();

        if (usernameTextField.getText().isEmpty()) {
            errorLabel.setText("Please Enter the data.");
            return;
        }

        if (amountString.equals("")) {
            errorLabel.setText("Amount is required");
            return;
        }

        try {
            Customer customer2 = customerList.authen(username);
            double amount = Double.parseDouble(amountString);


            if (amount < 0) {
                errorLabel.setText("Amount must be positive number");
                return;
            }
            errorLabel.setText("");
            // ส่งข้อมูลผู้ที่โอนเงิน, จำนวนเงินที่โอน, และผู้รับเงินไปยัง ConfirmTransferController
            FXRouter.setData("sender", customer);
            FXRouter.setData("amount", amount);
            FXRouter.setData("receiver", customer2);

            // นำข้อมูลไปยังหน้า ConfirmTransferController
            //FXRouter.goTo("confirm");


            amountTextField.clear();

            customerList.reduceBalanceFromUsername(customer.getName(), amount);
            customerList.giveBalanceToId(customer2.getId(), amount);



            datasource.writeData(customerList);
            FXRouter.goTo("confirm");


        } catch (AuthenticationFailedException e) {
            errorLabel.setText(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCustomer(Customer customer) {
        nameLabel.setText(customer.getName());
        balanceLabel.setText("" + customer.getBalance());
    }


    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("customer");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
