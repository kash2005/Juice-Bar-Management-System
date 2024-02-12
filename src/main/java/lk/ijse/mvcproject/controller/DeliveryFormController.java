package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.CustomerDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.model.CustomerModel;
import lk.ijse.mvcproject.model.DeliveryModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeliveryFormController implements Initializable {
    @FXML
    private TextField deliveryId;

    @FXML
    private TextField orderId;

    @FXML
    private TextField customerId;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerAddress;

    @FXML
    private TextField customerContact;

    @FXML
    private TextField distance;

    @FXML
    private TextField deliveryTot;

    @FXML
    private TextField subTotal;

    @FXML
    private TextField balanceId;

    @FXML
    private TextField cashId;

    @FXML
    private JFXButton purchaseBtn;

    public static OrderDTO orderDTO;

    public static OrderDetailsDTO orderDetailsDTO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateDeliveryId();
        orderId.setText(orderDTO.getOrderId());
        getCustomerDetails();
    }

    private void generateDeliveryId() {
        try {
            String id = DeliveryModel.generateDeliveryId();
            deliveryId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void getCustomerDetails(){
        String customerId1 = orderDTO.getCustomerId();
        try {
            CustomerDTO customerDTO = CustomerModel.searchCustomer(customerId1);
            String name = customerDTO.getName();
            String address = customerDTO.getAddress();
            String contact = customerDTO.getContact();
            customerId.setText(customerId1);
            customerName.setText(name);
            customerAddress.setText(address);
            customerContact.setText(contact);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void distanceOnAction(ActionEvent event) {
        calculateNetTotal();
    }

    void calculateNetTotal(){
        Double subTot = orderDetailsDTO.getAmount();
        System.out.println(subTot);
        int distanceText = Integer.parseInt(distance.getText());
        double deliveryTotal = distanceText * 20;
        deliveryTot.setText(String.valueOf(deliveryTotal));
        double netTotal = subTot + deliveryTotal;
        subTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void cashIdOnAction(ActionEvent event) {

    }

    @FXML
    void purchaseBtnOnAction(ActionEvent event) {

    }
}
