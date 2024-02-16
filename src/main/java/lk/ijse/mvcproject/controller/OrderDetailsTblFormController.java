package lk.ijse.mvcproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.mvcproject.dto.DeliveryDTO;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.dto.tm.OrderDetailsTM;
import lk.ijse.mvcproject.model.DeliveryModel;
import lk.ijse.mvcproject.model.ItemModel;
import lk.ijse.mvcproject.model.OrderDetailsModel;
import lk.ijse.mvcproject.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderDetailsTblFormController implements Initializable {
    @FXML
    private TableView<OrderDetailsTM> orderDetailsTbl;

    @FXML
    private TableColumn<String, OrderDetailsTM> colOrderId;

    @FXML
    private TableColumn<String, OrderDetailsTM> colOrderDate;

    @FXML
    private TableColumn<String, OrderDetailsTM> colCustomerId;

    @FXML
    private TableColumn<String, OrderDetailsTM> colItemId;

    @FXML
    private TableColumn<String, OrderDetailsTM> colDescription;

    @FXML
    private TableColumn<String, OrderDetailsTM> colGetQty;

    @FXML
    private TableColumn<String, OrderDetailsTM> colPrice;

    @FXML
    private TableColumn<String, OrderDetailsTM> colDeliveryStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("date"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("customerId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("description"));
        colGetQty.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("getQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("amount"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("deliveryStatus"));
    }
    private void getAll() {
        ObservableList<OrderDetailsTM> observableList = FXCollections.observableArrayList();
        String orderId = null;
        String itemId = null;
        int getQty = 0;
        double amount = 0.0;
        LocalDate date = null;
        String customerId= null;
        String description = null;
        String deliveryStatus = null;
        OrderDetailsTM orderDetailsTM = null;
        try {
            ArrayList<OrderDetailsDTO> orderDetails = OrderDetailsModel.getOrderDetails();
            for (OrderDetailsDTO orderDetailsDTO : orderDetails){
                orderId = orderDetailsDTO.getOrderId();
                itemId = orderDetailsDTO.getItemId();
                getQty = orderDetailsDTO.getGetQty();
                amount = orderDetailsDTO.getAmount();
                try {
                    OrderDTO orderDTO = OrderModel.searchOrderId(orderId);
                    date = orderDTO.getDate();
                    customerId = orderDTO.getCustomerId();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    ItemDTO itemDTO = ItemModel.searchItem(itemId);
                    description = itemDTO.getDescription();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    System.out.println(orderId);
                    DeliveryDTO allDelivery = DeliveryModel.getAllDelivery(orderId);
                    String orderId1 = allDelivery.getOrderId();
                    if (orderId1.equals(orderId)){
                        deliveryStatus = "Yes";
                    }else {
                        deliveryStatus = "No";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                orderDetailsTM = new OrderDetailsTM(orderId,date,customerId,itemId,description,getQty,amount,deliveryStatus);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        observableList.add(orderDetailsTM);
        orderDetailsTbl.setItems(observableList);
    }
}
