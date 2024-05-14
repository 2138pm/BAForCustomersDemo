package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class RegisterController {
    private Datasource<CustomerList> datasource;

    @FXML private TextField idTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField balanceTextField;
    @FXML private Label errorLabel;

    private CustomerList customerList;

    public void onSignUpButtonClick() throws IOException {
        checkRegister();
    }

    private void checkRegister() throws IOException {
        datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();

        errorLabel.setText("");
        String id = idTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String balanceString = balanceTextField.getText();




        if(idTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() ||
                passwordTextField.getText().isEmpty() || balanceTextField.getText().isEmpty()){
            errorLabel.setText("Please enter your data.");
            return;
        }

        if(customerList.isExists(username,password)){
            errorLabel.setText("The data has been used!");
            return;
        }

        double balance = Double.parseDouble(balanceString);
        if (balance < 0) {
            errorLabel.setText("balance must be positive number");
            return;
        }

        customerList.addNewCustomer(id, username, password, balance);
        datasource.writeData(customerList);
        onSignUpButtonClick();
        FXRouter.goTo("customer-list");
    }

    @FXML public void onBackButtonClick(){
        try{
            FXRouter.goTo("login");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
