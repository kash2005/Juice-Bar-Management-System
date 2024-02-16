package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<?> cmbEType;

    @FXML
    private TextField onePerHour;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TextField searchId;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

//        EmployeeModel.save();
    }

    @FXML
    void cmbETypeOnAction(ActionEvent event) {

    }

    @FXML
    void eAddressOnAction(ActionEvent event) {

    }

    @FXML
    void eContactOnAction(ActionEvent event) {

    }

    @FXML
    void eEmailOnAction(ActionEvent event) {

    }

    @FXML
    void eNameOnAction(ActionEvent event) {

    }

    @FXML
    void onePerHourOnAction(ActionEvent event) {

    }

    @FXML
    void searchImgOnAction(ActionEvent event) {

    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {

    }

    @FXML
    void tblEmployeeOnMouseClick(MouseEvent event) {

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
    }
}
