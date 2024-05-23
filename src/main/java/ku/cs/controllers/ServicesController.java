package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.models.Customer;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class ServicesController {
    private Datasource<CustomerList> datasource;
    private CustomerList customerList;
    private Customer customer;

    @FXML
    public void initialize() {
        datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();
        customer = (Customer) FXRouter.getData();

    }

    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("login");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onDepositButtonClick(){
        try{
            FXRouter.goTo("customer", customer);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onTransferButtonClick(){
        try{
            FXRouter.goTo("transfer", customer);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
