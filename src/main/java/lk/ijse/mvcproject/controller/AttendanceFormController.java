package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.model.AttendanceModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AttendanceFormController implements Initializable {
    @FXML
    private TableView<?> tblAttendance;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colEntryTime;

    @FXML
    private TableColumn<?, ?> colDepartTime;

    @FXML
    private TableColumn<?, ?> colEId;

    @FXML
    private TextField searchId;

    @FXML
    private TextField attendanceId;

    @FXML
    private TextField entryTime;

    @FXML
    private TextField departTime;

    @FXML
    private TextField eId;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void departOnAction(ActionEvent event) {

    }

    @FXML
    void eIdOnAction(ActionEvent event) {

    }

    @FXML
    void entryTimeOnAction(ActionEvent event) {

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
    void tblAttendanceOnMouseClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
    }

    void generateId(){
        try {
            String id = AttendanceModel.generateId();
            attendanceId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
