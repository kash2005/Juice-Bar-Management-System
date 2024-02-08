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
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.dto.tm.AddToCartTM;
import lk.ijse.mvcproject.model.ItemModel;
import lk.ijse.mvcproject.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            ItemDTO itemDetails = ItemModel.getItemDetails(selectedItem);
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
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Items Add to Cart !").show();
                getAll();
                itemDescription.clear();
                itemPrice.clear();
                itemQtyOnHand.clear();
                itemGettingQty.clear();
            }
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

                }
            }
        }
        AddToCartTM addToCartTM = new AddToCartTM(selectedItemId,description,price,qtyOnHand,getQty1);
        observableList.add(addToCartTM);
        tblItemDetails.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        setItemId();
        generateOrderId();
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
    void tblItemDetailsOnMouseClick(MouseEvent event) {
        addToCartBtn.setText("Remove");
        addToCartBtn.setStyle("-fx-background-color:  red mat; -fx-background-radius: 10");
    }

}
