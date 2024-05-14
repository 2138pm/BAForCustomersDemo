package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class ServicesController {


    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("hello");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onDepositButtonClick(){
        try{
            FXRouter.goTo("login");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
