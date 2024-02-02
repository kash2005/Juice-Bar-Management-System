package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.dto.UserDTO;
import lk.ijse.mvcproject.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    @FXML
    private TableView<?> tblItem;

    @FXML
    private TableColumn<?, ?> tblItemCode;

    @FXML
    private TableColumn<?, ?> tblItemDescription;

    @FXML
    private TableColumn<?, ?> tblItemQty;

    @FXML
    private TableColumn<?, ?> tblItemPerPrice;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TextField itemCode;

    @FXML
    private TextField itemDescription;

    @FXML
    private TextField itemQtyId;

    @FXML
    private TextField itemPerPriceId;

    @FXML
    private TextField searchId;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void itemDescriptionOnAction(ActionEvent event) {
        itemQtyId.requestFocus();
    }

    @FXML
    void itemPerPriceOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void itemQtyOnAction(ActionEvent event) {
        itemPerPriceId.requestFocus();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String code = itemCode.getText();
        String description = itemDescription.getText();
        int qty = Integer.parseInt(itemQtyId.getText());
        double price = Double.parseDouble(itemPerPriceId.getText());
        if (saveBtn.getText().equals("Save")){
            try {
                ItemDTO itemDTO = new ItemDTO(code, description, qty, price);
                boolean isSave = ItemModel.saveItem(itemDTO);
                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION,"Item is saved !");
                    clearTextFields();
                    generateItemId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Item is not saved !");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; fx-background-radius: 10;");
            ItemDTO itemDTO = new ItemDTO(code, description, qty, price);
            try {
                boolean isUpdate = ItemModel.updateItem(itemDTO);
                if (isUpdate){
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color:  green; -fx-background-radius: 10;");
                    new Alert(Alert.AlertType.CONFIRMATION,"item is updated !");
                    clearTextFields();
                    generateItemId();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void searchImgOnAction(ActionEvent event) {
        String searchIdText = searchId.getText();
        try {
            ItemDTO itemDTO = ItemModel.searchItem(searchIdText);
            if (itemDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; fx-background-radius: 10;");
                String itemId = itemDTO.getItemId();
                String description = itemDTO.getDescription();
                int qty = itemDTO.getQty();
                double price = itemDTO.getPrice();
                itemCode.setText(itemId);
                itemDescription.setText(description);
                itemQtyId.setText(String.valueOf(qty));
                itemPerPriceId.setText(String.valueOf(price));
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        String searchIdText = searchId.getText();
        try {
            ItemDTO itemDTO = ItemModel.searchItem(searchIdText);
            if (itemDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
                String itemId = itemDTO.getItemId();
                String description = itemDTO.getDescription();
                int qty = itemDTO.getQty();
                double price = itemDTO.getPrice();
                itemCode.setText(itemId);
                itemDescription.setText(description);
                itemQtyId.setText(String.valueOf(qty));
                itemPerPriceId.setText(String.valueOf(price));
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblItemOnMouseClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateItemId();
    }

    void generateItemId(){
        try {
            String id = ItemModel.generateItemId();
            itemCode.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clearTextFields(){
        itemCode.clear();
        itemDescription.clear();
        itemQtyId.clear();
        itemPerPriceId.clear();
    }
}
