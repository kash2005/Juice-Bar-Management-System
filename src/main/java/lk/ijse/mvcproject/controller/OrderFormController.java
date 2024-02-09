package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.dto.CustomerDTO;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.tm.AddToCartTM;
import lk.ijse.mvcproject.model.CustomerModel;
import lk.ijse.mvcproject.model.ItemModel;
import lk.ijse.mvcproject.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableList;

public class OrderFormController implements Initializable {
    @FXML
    private TextField itemDescription;

    @FXML
    private TextField itemPrice;

    @FXML
    private TextField itemQtyOnHand;

    @FXML
    private ComboBox<String> itemIdCmb;

    @FXML
    private TextField itemGettingQty;

    @FXML
    private JFXButton addToCartBtn;

    @FXML
    private TableColumn tblCode;

    @FXML
    private TableColumn tblDescription;

    @FXML
    private TableColumn  tblPrice;

    @FXML
    private TableColumn tblQtyOnHand;

    @FXML
    private TableColumn tblGetQty;

    @FXML
    private TableView<AddToCartTM> tblItemDetails;

    @FXML
    private TextField orderId;

    @FXML
    private TextField orderDate;

    @FXML
    private ComboBox<String> customerIdCmb;

    @FXML
    private TextField customerName;

    @FXML
    private TextField totalId;

    @FXML
    private TextField discountId;

    @FXML
    private TextField subTotalId;

    @FXML
    private JFXButton purchaseBtn;

    @FXML
    private TextField cashId;

    @FXML
    private TextField balanceId;

    @FXML
    private RadioButton yesRadioBtn;

    @FXML
    private RadioButton noRadioBtn;

    private ObservableList<AddToCartTM> observableList = FXCollections.observableArrayList();

    void setItemId(){
        try {
            ArrayList<String> itemId = ItemModel.getItemId();
            ObservableList<String> observableList = FXCollections.observableArrayList(itemId);
            itemIdCmb.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void setItemDetailsToTextFileds(){
        String selectedItem = itemIdCmb.getSelectionModel().getSelectedItem();
        try {
            ItemDTO itemDetails = ItemModel.searchItem(selectedItem);
            if (itemDetails != null){
                itemDescription.setText(itemDetails.getDescription());
                itemPrice.setText(String.valueOf(itemDetails.getPrice()));
                itemQtyOnHand.setText(String.valueOf(itemDetails.getQty()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void itemGettingQtyOnAction(ActionEvent event) {
            addToCartBtn.fire();
    }

    @FXML
    void itemIdCmbOnAction(ActionEvent event) {
        setItemDetailsToTextFileds();
    }

    @FXML
    void addToCartOnAction(ActionEvent event) {
        if (addToCartBtn.getText().equals("Add to Cart")){
            int onHandQty = Integer.parseInt(itemQtyOnHand.getText());
            int getQty = Integer.parseInt(itemGettingQty.getText());
            if (onHandQty < getQty){
                new Alert(Alert.AlertType.ERROR,"Out of Stock Items").show();
                itemGettingQty.setText("");
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Items Add to Cart !").show();
                getAll();
                itemIdCmb.setValue(null);
                itemDescription.clear();
                itemPrice.clear();
                itemQtyOnHand.clear();
                itemGettingQty.clear();
            }
            itemsTotal();
        }else if (addToCartBtn.getText().equals("Remove")){
            boolean isDeleted = false;
            AddToCartTM addToCartTM = tblItemDetails.getSelectionModel().getSelectedItem();
            if (addToCartTM != null){
                observableList.remove(addToCartTM);
                isDeleted = true;
            }
            if (isDeleted){
                addToCartBtn.setText("Add to Cart");
                addToCartBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                new Alert(Alert.AlertType.CONFIRMATION,"Item is removed !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item is not removed !").show();
            }
        }
    }

    void setValueFactory(){
        tblCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblGetQty.setCellValueFactory(new PropertyValueFactory<>("getQty"));
    }

    void getAll() {
        String selectedItemId = itemIdCmb.getValue();
        String description = itemDescription.getText();
        Double price = Double.valueOf(itemPrice.getText());
        int qtyOnHand = Integer.parseInt(itemQtyOnHand.getText());
        int getQty1 = Integer.parseInt(itemGettingQty.getText());

        if (!observableList.isEmpty()){
            for (int i = 0; i < tblItemDetails.getItems().size(); i++) {
                if (tblCode.getCellData(i).equals(selectedItemId)){
                    getQty1 += (int) tblGetQty.getCellData(i);
                    observableList.get(i).setGetQty(getQty1);
                    tblItemDetails.refresh();
                    return;
                }
            }
        }
        AddToCartTM addToCartTM = new AddToCartTM(selectedItemId,description,price,qtyOnHand,getQty1);
        observableList.add(addToCartTM);
        tblItemDetails.setItems(observableList);
    }

    @FXML
    void tblItemDetailsOnMouseClick(MouseEvent event) {
        addToCartBtn.setText("Remove");
        addToCartBtn.setStyle("-fx-background-color:  red mat; -fx-background-radius: 10");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        setItemId();
        generateOrderId();
        orderDateOnAction(new ActionEvent());
        setCustomerId();
    }

    void generateOrderId(){
        try {
            String nextId = OrderModel.generateOrderId();
            orderId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void orderDateOnAction(ActionEvent event) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);
        orderDate.setText(formattedDate);
    }

    void setCustomerId(){
        try {
            ArrayList<String> arrayList = CustomerModel.setCustomerId();
            ObservableList<String> strings = observableList(arrayList);
            customerIdCmb.setItems(strings);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void customerIdCmbOnAction(ActionEvent event) {
        String value = customerIdCmb.getValue();
        try {
            CustomerDTO customerDTO = CustomerModel.searchCustomer(value);
            String name = customerDTO.getName();
            customerName.setText(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void itemsTotal(){
        double fullTot = 0.0;
        if (!observableList.isEmpty()){
            ObservableList<AddToCartTM> observableList1 = observableList;
            for(AddToCartTM addToCartTM : observableList1){
                Double unitPrice = addToCartTM.getUnitPrice();
                int getQty = addToCartTM.getGetQty();
                double itemTot = getQty * unitPrice;
                fullTot += itemTot;
                totalId.setText(String.valueOf(fullTot));
            }
        }
    }

    void calculateDiscount(){
        double fullTot = Double.parseDouble(totalId.getText());
        if (discountId.getText() != null){
            Double discount = Double.valueOf(discountId.getText());
            double subTotal = fullTot-(fullTot * (discount / 100));
            subTotalId.setText(String.valueOf(subTotal));
        }else {
            subTotalId.setText(String.valueOf(fullTot));
        }
    }

    @FXML
    void discountOnAction(ActionEvent event) {
        calculateDiscount();
    }

    @FXML
    void cashOnAction(ActionEvent event) {
        Double subTot = Double.valueOf(subTotalId.getText());
        Double cash = Double.valueOf(cashId.getText());
        double balance = cash - subTot;
        balanceId.setText(String.valueOf(balance));
        if (balanceId.getText() != null){
            new Alert(Alert.AlertType.WARNING,"Please select the delivery status !").show();
        }
    }

    @FXML
    void yesRadioBtnOnAction(ActionEvent event) {
        yesRadioBtn.setSelected(true);
        noRadioBtn.setSelected(false);
    }

    @FXML
    void noRadioBtnOnAction(ActionEvent event) {
        noRadioBtn.setSelected(true);
        yesRadioBtn.setSelected(false);
    }

    @FXML
    void purchaseBtnOnAction(ActionEvent event) {

    }

    void check(){
        String type = null;
        if (yesRadioBtn.isSelected()){
            type = "Yes";
            purchaseBtn.setText("Delivery Form");
            purchaseBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
        }else if (noRadioBtn.isSelected()){
            type = "No";
//            placeOrder();
        }

    }

//    void placeOrder(){
//        String id = orderId.getText();
//        LocalDate date = LocalDate.parse(orderDate.getText());
//        String custId = customerIdCmb.getValue();
//        OrderDTO orderDTO = new OrderDTO(id, date, custId);
//        try {
//            boolean isOrderSave = OrderModel.saveOrder(orderDTO);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
