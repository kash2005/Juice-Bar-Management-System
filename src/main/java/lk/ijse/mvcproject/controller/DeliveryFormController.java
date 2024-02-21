package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.*;
import lk.ijse.mvcproject.model.CustomerModel;
import lk.ijse.mvcproject.model.DeliveryModel;
import lk.ijse.mvcproject.model.OrderModel;
import lk.ijse.mvcproject.model.PlaceOrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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

    public static OrderDTO orderDTO;

    public static OrderDetailsDTO orderDetailsDTO;
    public static List<OrderDetailsDTO> orderDetailsDTOList;

    public static List<CartDTO> cartDTOList;


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
        double cash = Double.parseDouble(cashId.getText());
        double tot = Double.parseDouble(subTotal.getText());
        double balance = cash - tot;
        balanceId.setText(String.valueOf(balance));
    }

    @FXML
    void purchaseBtnOnAction(ActionEvent event) {
        String deliveryIdText = deliveryId.getText();
        String distanceText = distance.getText();
        double deliveryTotText = Double.parseDouble(deliveryTot.getText());
        String ordersId = orderId.getText();

        DeliveryDTO deliveryDTO = new DeliveryDTO(deliveryIdText, distanceText, deliveryTotText, ordersId);

        try {
            boolean isSaved = PlaceOrderModel.savePlaceOrderWithDelivery(orderDTO, cartDTOList, orderDetailsDTOList, deliveryDTO);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Place Order Success !").show();
                deliveryId.clear();
                orderId.clear();
                customerId.clear();
                customerName.clear();
                customerAddress.clear();
                customerContact.clear();
                distance.clear();
                deliveryTot.clear();
                subTotal.clear();
                cashId.clear();
                balanceId.clear();
                generateDeliveryId();
            }else {
                new Alert(Alert.AlertType.ERROR,"Place Order not Success !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
