package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private TextField customerId;

    @FXML
    private TextField customerNameId;

    @FXML
    private TextField customerAddressId;

    @FXML
    private TextField customerContactId;

    @FXML
    private TextField customerEmailId;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TableColumn<?, ?> tblCustomerId;

    @FXML
    private TableColumn<?, ?> tblCustomerName;

    @FXML
    private TableColumn<?, ?> tblCustomerAddress;

    @FXML
    private TableColumn<?, ?> tblCustomerEmail;

    @FXML
    private TableColumn<?, ?> tblCustomerContact;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateCustomerId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateCustomerId() throws SQLException {
        String id = CustomerModel.generateCustomerId();
        customerId.setText(id);
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        customerEmailId.requestFocus();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        customerContactId.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
//        customerNameId.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        customerAddressId.requestFocus();
    }
}
