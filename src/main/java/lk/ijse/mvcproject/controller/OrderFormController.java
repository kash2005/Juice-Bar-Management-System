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
            String selectedItemId = itemIdCmb.getSelectionModel().getSelectedItem();
            String description = itemDescription.getText();
            Double price = Double.valueOf(itemPrice.getText());
            String qtyOnHand = itemQtyOnHand.getText();
            String getQty1 = itemGettingQty.getText();

            ObservableList<AddToCartTM> observableList = FXCollections.observableArrayList();
            observableList.add(new AddToCartTM(selectedItemId,description,price,qtyOnHand,getQty1));
            tblItemDetails.setItems(observableList);

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

//    void getAll(){
//        ObservableList<AddToCartTM> addToCartTMS = FXCollections.observableArrayList();
//
//        // Assuming you have data structures to hold your item details
//        List<String> selectedItemIds = itemIdCmb.getItems();
//        List<String> descriptions = itemDescription.getText() ; // get descriptions from somewhere
//        List<Double> prices = itemPrice.getText(); // get prices from somewhere
//        List<String> qtyOnHands = itemQtyOnHand.getText(); // get qty on hand from somewhere
//        List<String> getQtys = itemGettingQty.getText(); // get getting qty from somewhere
//
//        // Assuming all lists have the same size
//        int size = selectedItemIds.size();
//        for (int i = 0; i < size; i++) {
//            // Create a new AddToCartTM object for each set of item details
//            AddToCartTM addToCartTM = new AddToCartTM(
//                    selectedItemIds.get(i),
//                    descriptions.get(i),
//                    prices.get(i),
//                    qtyOnHands.get(i),
//                    getQtys.get(i)
//            );
//            addToCartTMS.add(addToCartTM);
//        }
//
//        // Set the items of the TableView to the ObservableList
//        tblItemDetails.setItems(addToCartTMS);
//    }


    void getAll(){
        String selectedItemId = itemIdCmb.getSelectionModel().getSelectedItem();
        String description = itemDescription.getText();
        String price = itemPrice.getText();
        String qtyOnHand = itemQtyOnHand.getText();
        String getQty = itemGettingQty.getText();
        ObservableList<AddToCartTM> addToCartTMS = FXCollections.observableArrayList();
        AddToCartTM addToCartTM = new AddToCartTM(selectedItemId, description, Double.valueOf(price), qtyOnHand, getQty);
        addToCartTMS.add(addToCartTM);
        tblItemDetails.setItems(addToCartTMS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
    }
}
