package lk.ijse.mvcproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsTblFormController implements Initializable {
    @FXML
    private TableView<?> orderDetailsTbl;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colGetQty;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colDeliveryStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {

    }
    private void getAll() {

    }
}
