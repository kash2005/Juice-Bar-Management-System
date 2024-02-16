package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.dto.EmployeeDTO;
import lk.ijse.mvcproject.dto.tm.EmployeeTM;
import lk.ijse.mvcproject.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    @FXML
    private TextField eId;

    @FXML
    private TextField eName;

    @FXML
    private TextField eAddress;

    @FXML
    private TextField eEmail;

    @FXML
    private TextField eContact;

    @FXML
    private ComboBox<String> cmbEType;

    @FXML
    private TextField onePerHour;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TableColumn<String, EmployeeTM> colId;

    @FXML
    private TableColumn<String, EmployeeTM> colName;

    @FXML
    private TableColumn<String, EmployeeTM> colType;

    @FXML
    private TableColumn<String, EmployeeTM> colAddress;

    @FXML
    private TableColumn<String, EmployeeTM> colContact;

    @FXML
    private TableColumn<String, EmployeeTM> colEmail;

    @FXML
    private TableColumn<String, EmployeeTM> colPerHour;

    @FXML
    private TextField searchId;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = eId.getText();
        try {
            boolean delete = EmployeeModel.delete(id);
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is deleted !").show();
                clear();
                generateId();
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee is not deleted !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = eId.getText();
        String name = eName.getText();
        String type = cmbEType.getValue();
        String address = eAddress.getText();
        String contact = eContact.getText();
        String email = eEmail.getText();
        String perHour = onePerHour.getText();
        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, address, email, contact, type, perHour);

        if (btnSave.getText().equals("Save")){
            try {
                boolean save = EmployeeModel.save(employeeDTO);
                if (save){
                    new Alert(Alert.AlertType.CONFIRMATION,"Employee is saved !").show();
                    clear();
                    generateId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Employee is not saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (btnSave.getText().equals("Update")){
            try {
                boolean update = EmployeeModel.update(employeeDTO);
                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION,"Employee is updated !").show();
                    btnSave.setText("Save");
                    btnSave.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                    clear();
                    generateId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Employee is not updated !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void cmbETypeOnAction(ActionEvent event) {
        eAddress.requestFocus();
    }

    @FXML
    void eAddressOnAction(ActionEvent event) {
        eContact.requestFocus();
    }

    @FXML
    void eContactOnAction(ActionEvent event) {
        eEmail.requestFocus();
    }

    @FXML
    void eEmailOnAction(ActionEvent event) {
        onePerHour.requestFocus();
    }

    @FXML
    void onePerHourOnAction(ActionEvent event) {
        btnSave.fire();
    }

    @FXML
    void searchImgOnAction(ActionEvent event) {
        String id = searchId.getText();
        System.out.println(id);
        try {
            EmployeeDTO employeeDTO = EmployeeModel.searchEmployeeId(id);
            String name = employeeDTO.getName();
            String jobRoll = employeeDTO.getJobRoll();
            String address = employeeDTO.getAddress();
            String contact = employeeDTO.getContact();
            String email = employeeDTO.getEmail();
            String onePerHour1 = employeeDTO.getOnePerHour();
            eId.setText(id);
            eName.setText(name);
            cmbEType.setValue(jobRoll);
            eAddress.setText(address);
            eContact.setText(contact);
            eEmail.setText(email);
            onePerHour.setText(onePerHour1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnSave.setText("Update");
        btnSave.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        String id = searchId.getText();
        System.out.println(id);
        try {
            EmployeeDTO employeeDTO = EmployeeModel.searchEmployeeId(id);
            String name = employeeDTO.getName();
            String jobRoll = employeeDTO.getJobRoll();
            String address = employeeDTO.getAddress();
            String contact = employeeDTO.getContact();
            String email = employeeDTO.getEmail();
            String onePerHour1 = employeeDTO.getOnePerHour();
            eId.setText(id);
            eName.setText(name);
            cmbEType.setValue(jobRoll);
            eAddress.setText(address);
            eContact.setText(contact);
            eEmail.setText(email);
            onePerHour.setText(onePerHour1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnSave.setText("Update");
        btnSave.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void tblEmployeeOnMouseClick(MouseEvent event) {

    }

    void searchId(){

    }

    void clear(){
        eId.clear();
        eName.clear();
        cmbEType.setValue("");
        eAddress.clear();
        eContact.clear();
        eEmail.clear();
        onePerHour.clear();
    }

    void setCmbJobRollTypes(){
        ObservableList<String> options = FXCollections.observableArrayList("Admin", "Cashier", "Shop keeper", "Driver");
        cmbEType.setItems(options);
    }

    void generateId(){
        try {
            String id = EmployeeModel.generateId();
            eId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
        setCmbJobRollTypes();
        eName.requestFocus();
        setValueFactory();
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<String, EmployeeTM>("eId"));
        colName.setCellValueFactory(new PropertyValueFactory<String,EmployeeTM>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<String,EmployeeTM>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<String,EmployeeTM>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<String,EmployeeTM>("contact"));
        colType.setCellValueFactory(new PropertyValueFactory<String,EmployeeTM>("jobRoll"));
        colPerHour.setCellValueFactory(new PropertyValueFactory<String,EmployeeTM>("onePerHour"));
    }
}
