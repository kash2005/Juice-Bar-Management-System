package lk.ijse.mvcproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.CustomerDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
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

    public static OrderDTO orderDTO;

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

    void calculateNetTotal(){

    }
}
