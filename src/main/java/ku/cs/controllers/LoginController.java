package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import ku.cs.models.Customer;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.exception.AuthenticationFailedException;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label errorLabel;

    private CustomerList customerList;

    public void onSignInButtonClick(ActionEvent event) throws IOException{
        checkSignUp();
    }

    private void checkSignUp() throws IOException {
        Datasource<CustomerList> datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();

        errorLabel.setText("");
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (usernameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty()) {
            errorLabel.setText("Please Enter your data.");
            return;
        }

        try {
            Customer customer = customerList.authenForLogin(username, password);
            FXRouter.setData("customer", customer);
            FXRouter.goTo("services", customer);

        } catch (AuthenticationFailedException e) {
            errorLabel.setText(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onSignInButtonClick() {
        try {
            FXRouter.goTo("services");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onSignUpButtonClick() {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
