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
import lk.ijse.mvcproject.dto.SupplierDTO;
import lk.ijse.mvcproject.model.SupplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    @FXML
    private TextField supplierId;

    @FXML
    private TextField supplierName;

    @FXML
    private TextField supplierContact;

    @FXML
    private TextField supplierCompany;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblContact;

    @FXML
    private TableColumn<?, ?> tblCompnay;

    @FXML
    private TextField searchId;

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = supplierId.getText();
        String name = supplierName.getText();
        String contact = supplierContact.getText();
        String company = supplierCompany.getText();
        SupplierDTO supplierDTO = new SupplierDTO(id,name,contact,company);
        if (saveBtn.getText().equals("Save")){
            try {
                boolean isSave = SupplierModel.saveSupplier(supplierDTO);
                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION,"Supplier is saved !").show();
                    clearTextFileds();
                    generateSupplierId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Supplier is not saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (saveBtn.getText().equals("Update")){
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
            try {
                boolean isUpdate = SupplierModel.updateSupplier(supplierDTO);
                if (isUpdate){
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                    new Alert(Alert.AlertType.CONFIRMATION,"Supplier is update !").show();
                    clearTextFileds();
                    generateSupplierId();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Supplier is not update !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    private void clearTextFileds() {
        supplierId.clear();
        supplierName.clear();
        supplierContact.clear();
        supplierCompany.clear();
    }

    @FXML
    void searchIdOnAction(ActionEvent event) {
        String id = searchId.getText();
        try {
            SupplierDTO supplierDTO = SupplierModel.searchSupplier(id);
            if (supplierDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
                String supplierId1 = supplierDTO.getSupplierId();
                String name = supplierDTO.getName();
                String contact = supplierDTO.getContact();
                String company = supplierDTO.getCompany();
                supplierId.setText(supplierId1);
                supplierName.setText(name);
                supplierContact.setText(contact);
                supplierCompany.setText(company);
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchImgOnAction(ActionEvent event) {
        String id = searchId.getText();
        try {
            SupplierDTO supplierDTO = SupplierModel.searchSupplier(id);
            if (supplierDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
                String supplierId1 = supplierDTO.getSupplierId();
                String name = supplierDTO.getName();
                String contact = supplierDTO.getContact();
                String company = supplierDTO.getCompany();
                supplierId.setText(supplierId1);
                supplierName.setText(name);
                supplierContact.setText(contact);
                supplierCompany.setText(company);
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void supplierCompany(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void supplierContactOnAction(ActionEvent event) {
        supplierCompany.requestFocus();
    }

    @FXML
    void supplierNameOnAction(ActionEvent event) {
        supplierContact.requestFocus();
    }

    @FXML
    void tblSupplierOnMouseClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateSupplierId();
    }

    private void generateSupplierId(){
        try {
            String id = SupplierModel.generateId();
            supplierId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
