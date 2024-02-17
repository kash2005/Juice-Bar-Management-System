package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mvcproject.dto.AttendanceDTO;
import lk.ijse.mvcproject.dto.tm.AttendanceTM;
import lk.ijse.mvcproject.model.AttendanceModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceFormController implements Initializable {
    @FXML
    private TableView<?> tblAttendance;

    @FXML
    private TableColumn<String, AttendanceTM> colId;

    @FXML
    private TableColumn<LocalTime, AttendanceTM> colEntryTime;

    @FXML
    private TableColumn<LocalTime, AttendanceTM> colDepartTime;

    @FXML
    private TableColumn<String, AttendanceTM> colEId;

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
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<String, AttendanceTM>("attendanceId"));
        colEntryTime.setCellValueFactory(new PropertyValueFactory<LocalTime, AttendanceTM>("departTime"));
        colDepartTime.setCellValueFactory(new PropertyValueFactory<LocalTime, AttendanceTM>("entryTime"));
        colEId.setCellValueFactory(new PropertyValueFactory<String, AttendanceTM>("eId"));
    }

    private void getAll(){
        ObservableList<AttendanceTM> observableList =FXCollections.observableArrayList();
        try {
            ArrayList<AttendanceDTO> all = AttendanceModel.getAll();
            for (AttendanceDTO attendanceDTO : all){
                observableList.add(new AttendanceTM(
                        attendanceDTO.getAttendanceId(),
                        attendanceDTO.getEntryTime(),
                        attendanceDTO.getDepartTime(),
                        attendanceDTO.getEId()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
