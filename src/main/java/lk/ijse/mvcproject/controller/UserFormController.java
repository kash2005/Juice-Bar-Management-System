package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton saveBtn1;

    @FXML
    private TableView<?> tblAttendance;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TextField searchId;

    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

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
    void tblUserOnMouseClick(MouseEvent event) {

    }

    @FXML
    void userNameOnAction(ActionEvent event) {

    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {

    }

    void getCashierId(){
        try {
            ArrayList<String> cashierId = EmployeeModel.getCashierId();
            ObservableList<String> observableList = FXCollections.observableArrayList(cashierId);
            cmbUserId.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCashierId();
    }
}
