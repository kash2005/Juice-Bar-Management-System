package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
        int onHandQty = Integer.parseInt(itemQtyOnHand.getText());
        int getQty = Integer.parseInt(itemGettingQty.getText());
        if (onHandQty < getQty){
            new Alert(Alert.AlertType.ERROR,"Out of Stock Items").show();
            itemGettingQty.setText("");
        }

        System.out.println(onHandQty);
        System.out.println(getQty);
    }

    @FXML
    void itemIdCmbOnAction(ActionEvent event) {
        setItemDetailsToTextFileds();
    }

    @FXML
    void addToCartOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
    }
}
