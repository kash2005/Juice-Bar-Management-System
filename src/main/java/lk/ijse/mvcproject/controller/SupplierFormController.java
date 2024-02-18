package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.dto.SupplierDTO;
import lk.ijse.mvcproject.dto.tm.SupplierTM;
import lk.ijse.mvcproject.model.SupplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<String, SupplierTM> tblId;

    @FXML
    private TableColumn<String, SupplierTM> tblName;

    @FXML
    private TableColumn<String, SupplierTM> tblContact;

    @FXML
    private TableColumn<String, SupplierTM> tblCompnay;

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
                    getAll();
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
                    getAll();
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
        String id = supplierId.getText();
        try {
            boolean isDelete = SupplierModel.deleteSupplier(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier is deleted !").show();
                clearTextFileds();
                generateSupplierId();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier is not deleted !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        SupplierTM selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
        try {
            SupplierDTO supplierDTO = SupplierModel.searchSupplier(selectedSupplier.getSupplierId());
            supplierId.setText(supplierDTO.getSupplierId());
            supplierName.setText(supplierDTO.getName());
            supplierContact.setText(supplierDTO.getContact());
            supplierCompany.setText(supplierDTO.getCompany());
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateSupplierId();
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        tblId.setCellValueFactory(new PropertyValueFactory<String, SupplierTM>("supplierId"));
        tblName.setCellValueFactory(new PropertyValueFactory<String, SupplierTM>("name"));
        tblContact.setCellValueFactory(new PropertyValueFactory<String, SupplierTM>("contact"));
        tblCompnay.setCellValueFactory(new PropertyValueFactory<String, SupplierTM>("company"));
    }

    private void getAll(){
        ObservableList<SupplierTM> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<SupplierDTO> all = SupplierModel.getAll();
            for (SupplierDTO supplierDTO : all){
                observableList.add(new SupplierTM(
                        supplierDTO.getSupplierId(),
                        supplierDTO.getName(),
                        supplierDTO.getContact(),
                        supplierDTO.getCompany()
                ));
            }
            tblSupplier.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
