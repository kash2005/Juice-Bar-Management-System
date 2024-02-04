package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.model.SupplierModel;

import java.net.URL;
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

    }

    @FXML
    void searchIdOnAction(ActionEvent event) {

    }

    @FXML
    void searchImgOnAction(ActionEvent event) {

    }

    @FXML
    void supplierCompany(ActionEvent event) {

    }

    @FXML
    void supplierContactOnAction(ActionEvent event) {

    }

    @FXML
    void supplierNameOnAction(ActionEvent event) {

    }

    @FXML
    void tblSupplierOnMouseClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateSupplierId();
    }

    private void generateSupplierId() {

        SupplierModel.generateId();
    }
}
