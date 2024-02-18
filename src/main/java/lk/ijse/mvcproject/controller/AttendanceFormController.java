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
import lk.ijse.mvcproject.dto.AttendanceDTO;
import lk.ijse.mvcproject.dto.tm.AttendanceTM;
import lk.ijse.mvcproject.model.AttendanceModel;
import lk.ijse.mvcproject.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceFormController implements Initializable {
    @FXML
    private TableView<AttendanceTM> tblAttendance;

    @FXML
    private TableColumn<String, AttendanceTM> colId;

    @FXML
    private TableColumn<LocalTime, AttendanceTM> colEntryTime;

    @FXML
    private TableColumn<LocalTime, AttendanceTM> colDepartTime;

    @FXML
    private TableColumn<String, AttendanceTM> colEId;

    @FXML
    private ComboBox<String> cmbEId;

    @FXML
    private TextField searchId;

    @FXML
    private TextField attendanceId;

    @FXML
    private TextField entryTime;

    @FXML
    private TextField departTime;

    @FXML
    private JFXButton saveBtn;


    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = attendanceId.getText();
        try {
            boolean delete = AttendanceModel.delete(id);
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Attendance is deleted !").show();
                clear();
                generateId();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.ERROR,"Attendance is not deleted !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void departOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void entryTimeOnAction(ActionEvent event) {
        departTime.requestFocus();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) throws SQLException {
        String id = attendanceId.getText();
        String eId = cmbEId.getValue();
        LocalTime entry = LocalTime.parse(entryTime.getText());
        LocalTime depart = LocalTime.parse(departTime.getText());
        AttendanceDTO attendanceDTO = new AttendanceDTO(id, depart, eId, entry);

        if (saveBtn.getText().equals("Save")){
            try {
                boolean save = AttendanceModel.save(attendanceDTO);
                if (save){
                    new Alert(Alert.AlertType.CONFIRMATION,"Attendance is saved !").show();
                    clear();
                    generateId();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Attendance is not saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            boolean update = AttendanceModel.update(attendanceDTO);
            if (update){
                new Alert(Alert.AlertType.CONFIRMATION,"Attendance is updated !").show();
                clear();
                generateId();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.ERROR,"Attendance is not updated !").show();
            }
        }
    }

    @FXML
    void searchIdOnAction(ActionEvent event) {
        String id = searchId.getText();
        try {
            AttendanceDTO attendanceDTO = AttendanceModel.searchId(id);
            attendanceId.setText(attendanceDTO.getAttendanceId());
            entryTime.setText(String.valueOf(attendanceDTO.getEntryTime()));
            departTime.setText(String.valueOf(attendanceDTO.getDepartTime()));
            cmbEId.setValue(attendanceDTO.getEId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveBtn.setText("Update");
        saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void searchImgOnAction(ActionEvent event) {
        String id = searchId.getText();
        try {
            AttendanceDTO attendanceDTO = AttendanceModel.searchId(id);
            attendanceId.setText(attendanceDTO.getAttendanceId());
            entryTime.setText(String.valueOf(attendanceDTO.getEntryTime()));
            departTime.setText(String.valueOf(attendanceDTO.getDepartTime()));
            cmbEId.setValue(attendanceDTO.getEId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveBtn.setText("Update");
        saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void tblAttendanceOnMouseClick(MouseEvent event) {
        AttendanceTM selectedItem = (AttendanceTM) tblAttendance.getSelectionModel().getSelectedItem();
        try {
            AttendanceDTO attendanceDTO = AttendanceModel.searchId(selectedItem.getAttendanceId());
            attendanceId.setText(attendanceDTO.getAttendanceId());
            entryTime.setText(String.valueOf(attendanceDTO.getEntryTime()));
            departTime.setText(String.valueOf(attendanceDTO.getDepartTime()));
            cmbEId.setValue(attendanceDTO.getEId());
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
        setEmployeeId();
        setValueFactory();
        getAll();
    }

    void generateId(){
        try {
            String id = AttendanceModel.generateId();
            attendanceId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        tblAttendance.setItems(observableList);
    }

    void setEmployeeId(){
        try {
            ArrayList<String> cmbEmployeeId = EmployeeModel.getCmbEmployeeId();
            ObservableList<String> observableList = FXCollections.observableArrayList(cmbEmployeeId);
            cmbEId.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clear(){
        attendanceId.clear();
        cmbEId.setValue("");
        entryTime.clear();
        departTime.clear();
    }

    @FXML
    void cmbEIdeOnAction(ActionEvent event) {
        entryTime.requestFocus();
    }
}
