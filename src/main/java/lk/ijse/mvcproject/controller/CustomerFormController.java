package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.CustomerDTO;
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
    private TextField searchId;

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
        String id = searchId.getText();
        try {
            boolean isDeleted = CustomerModel.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is deleted !");
                clearTextFields();
                generateCustomerId();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is not deleted !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = customerId.getText();
        String name = customerNameId.getText();
        String address = customerAddressId.getText();
        String email = customerEmailId.getText();
        String contact = customerContactId.getText();

        if (saveBtn.getText().equals("Save")){
            try {
                boolean isSaved = CustomerModel.saveCustomer(id,name,address,email,contact);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is saved !");
                    clearTextFields();
                    generateCustomerId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Customer is not saved !");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            try {
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
                CustomerDTO customerDTO = new CustomerDTO(id, name, address, email, contact);
                boolean isUpdated = CustomerModel.updateCustomer(customerDTO);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is updated !");
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color:  green; -fx-background-radius: 10;");
                    clearTextFields();
                    generateCustomerId();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is not updated !");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
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
    void searchTxtOnAction(ActionEvent event) {
        String searchIdText = searchId.getText();
        try {
            CustomerDTO customerDTO = CustomerModel.searchCustomer(searchIdText);
            if (customerDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
                String id = customerDTO.getCustomerId();
                String name = customerDTO.getName();
                String address = customerDTO.getAddress();
                String email = customerDTO.getEmail();
                String contact = customerDTO.getContact();
                customerId.setText(id);
                customerNameId.setText(name);
                customerAddressId.setText(address);
                customerEmailId.setText(email);
                customerContactId.setText(contact);
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTextFields(){
        customerId.clear();
        customerNameId.clear();
        customerAddressId.clear();
        customerEmailId.clear();
        customerContactId.clear();
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
