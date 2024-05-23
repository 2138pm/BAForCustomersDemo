package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Customer;
import ku.cs.models.CustomerList;
import ku.cs.services.CustomerListFileDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class ConfirmTransferController {
    private CustomerList customerList;
    private Datasource<CustomerList> datasource;
    private Customer sender;
    private Customer receiver;
    private double amount;

    @FXML private Label senderLabel;
    @FXML private Label receiverLabel;
    @FXML private Label amountLabel;


    @FXML
    public void initialize() {
        datasource = new CustomerListFileDatasource("data", "customer-list.csv");
        customerList = datasource.readData();
        // รับข้อมูล sender, receiver, และ amount ที่ส่งมาจาก TransferController

        sender = (Customer) FXRouter.getData("sender");
        receiver = (Customer) FXRouter.getData("receiver");
        amount = (double) FXRouter.getData("amount");

        // แสดงข้อมูลบนหน้าจอ
        senderLabel.setText("Sender: " + sender.getName());
        receiverLabel.setText("Receiver: " + receiver.getName());
        amountLabel.setText("Amount: " + amount);
    }

    @FXML
    public void onConfirmButtonClick() {

        try {
            customerList.reduceBalanceFromUsername(sender.getName(), amount);
            FXRouter.setData("sender");
            FXRouter.goTo("customer-profile", sender);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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