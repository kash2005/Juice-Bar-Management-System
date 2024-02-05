package lk.ijse.mvcproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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


    void setItemId(){
        try {
            ArrayList<String> itemId = ItemModel.getItemId();
            ObservableList<String> observableList = FXCollections.observableArrayList(itemId);
            itemIdCmb.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    void setItemDetailsToTextFileds()

    @FXML
    void addToCartOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
    }
}
