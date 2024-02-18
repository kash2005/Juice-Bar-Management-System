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
import lk.ijse.mvcproject.dto.CustomerDTO;
import lk.ijse.mvcproject.dto.tm.CustomerTM;
import lk.ijse.mvcproject.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private JFXButton saveBtn;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<CustomerTM, String> tblCustomerId;

    @FXML
    private TableColumn<CustomerTM, String> tblCustomerName;

    @FXML
    private TableColumn<CustomerTM, String> tblCustomerAddress;

    @FXML
    private TableColumn<CustomerTM, String> tblCustomerEmail;

    @FXML
    private TableColumn<CustomerTM, String> tblCustomerContact;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = customerId.getText();
        try {
            boolean isDeleted = CustomerModel.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is deleted !").show();
                clearTextFields();
                generateCustomerId();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is not deleted !").show();
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
        CustomerDTO customerDTO = new CustomerDTO(id,name,address,email,contact);
        if (saveBtn.getText().equals("Save")){
            try {
                boolean isSaved = CustomerModel.saveCustomer(customerDTO);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is saved !").show();
                    clearTextFields();
                    generateCustomerId();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Customer is not saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            try {
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
                boolean isUpdated = CustomerModel.updateCustomer(customerDTO);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is updated !").show();
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10;");
                    clearTextFields();
                    generateCustomerId();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is not updated !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            generateCustomerId();
            setValueFactory();
            getAll();
    }

    public void generateCustomerId() {
        try {
            String id = CustomerModel.generateCustomerId();
            customerId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    void searchImgOnAction(ActionEvent event) {
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

    void getAll(){
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<CustomerDTO> all = CustomerModel.getAll();
            for (CustomerDTO customerDTO:all) {
                observableList.add(new CustomerTM(
                    customerDTO.getCustomerId(),
                    customerDTO.getName(),
                    customerDTO.getAddress(),
                    customerDTO.getEmail(),
                    customerDTO.getContact()
                ));
            }
            tblCustomer.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void setValueFactory(){
        tblCustomerId.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("customerId"));
        tblCustomerName.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("name"));
        tblCustomerAddress.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("address"));
        tblCustomerEmail.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("email"));
        tblCustomerContact.setCellValueFactory(new PropertyValueFactory<CustomerTM,String>("contact"));
    }

    @FXML
    void tblCustomerOnMouseClick(MouseEvent event) {
        CustomerTM selectedCustomer = (CustomerTM) tblCustomer.getSelectionModel().getSelectedItem();
        try {
            CustomerDTO customer = CustomerModel.searchCustomer(selectedCustomer.getCustomerId());
            customerId.setText(customer.getCustomerId());
            customerNameId.setText(customer.getName());
            customerAddressId.setText(customer.getAddress());
            customerEmailId.setText(customer.getEmail());
            customerContactId.setText(customer.getEmail());
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    void txtNameOnAction(ActionEvent event) {
        customerAddressId.requestFocus();
    }
}
