module ku.cs.baforcustomerdemo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs.baforcustomerdemo1 to javafx.fxml;
    exports ku.cs.baforcustomerdemo1;

    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;

    exports ku.cs.models;
    opens ku.cs.models to javafx.base;
}