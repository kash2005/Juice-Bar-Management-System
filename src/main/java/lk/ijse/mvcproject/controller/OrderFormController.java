package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.dto.tm.AddToCartTM;
import lk.ijse.mvcproject.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private TableColumn<String, AddToCartTM> tblCode;

    @FXML
    private TableColumn<String, AddToCartTM> tblDescription;

    @FXML
    private TableColumn<String, AddToCartTM> tblPrice;

    @FXML
    private TableColumn<String, AddToCartTM> tblQtyOnHand;

    @FXML
    private TableColumn<String, AddToCartTM> tblGetQty;

    @FXML
    private TableView<AddToCartTM> tblItemDetails;

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
        int onHandQty = Integer.parseInt(itemQtyOnHand.getText());
        int getQty = Integer.parseInt(itemGettingQty.getText());
        if (onHandQty < getQty){
            new Alert(Alert.AlertType.ERROR,"Out of Stock Items").show();
            itemGettingQty.setText("");
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Items Add to Cart !").show();
            setValueFactory();
            getAll();
            itemDescription.clear();
            itemPrice.clear();
            itemQtyOnHand.clear();
            itemGettingQty.clear();
        }
    }

    void setValueFactory(){
        tblCode.setCellValueFactory(new PropertyValueFactory<String, AddToCartTM>("itemCode"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<String, AddToCartTM>("itemDescription"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<String, AddToCartTM>("unitPrice"));
        tblQtyOnHand.setCellValueFactory(new PropertyValueFactory<String, AddToCartTM>("qtyOnHand"));
        tblGetQty.setCellValueFactory(new PropertyValueFactory<String, AddToCartTM>("getQty"));
    }

    void getAll(){
        String selectedItemId = itemIdCmb.getSelectionModel().getSelectedItem();
        String description = itemDescription.getText();
        Double price = Double.valueOf(itemPrice.getText());
        int qtyOnHand = Integer.parseInt(itemQtyOnHand.getText());
        int getQty1 = Integer.parseInt(itemGettingQty.getText());

        boolean isUpdatedNewRow = false;
        int getQty = 0;

        for (AddToCartTM item : observableList) {
            if (item.getItemCode().equals(selectedItemId)) {
                getQty = item.getGetQty();
                getQty += getQty1;
                item.setGetQty(getQty);
                isUpdatedNewRow = true;
            }
        }

        if (!isUpdatedNewRow){
            observableList.add(new AddToCartTM(selectedItemId,description,price,qtyOnHand,getQty1));
            tblItemDetails.setItems(observableList);
        }else {

        }


    }

//    void setItemDetailsToTable(){
//        String selectedItemId = itemIdCmb.getSelectionModel().getSelectedItem();
//        if (selectedItemId.equals(tblCode)){
//            int qty = Integer.parseInt(String.valueOf(itemGettingQty));
//            tblGetQty += qty;
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
    }
}
