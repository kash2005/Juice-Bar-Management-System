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
import lk.ijse.mvcproject.dto.UserDTO;
import lk.ijse.mvcproject.dto.tm.UserTM;
import lk.ijse.mvcproject.model.EmployeeModel;
import lk.ijse.mvcproject.model.UserModel;

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
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<String, UserTM> colId;

    @FXML
    private TableColumn<String, UserTM> colName;

    @FXML
    private TableColumn<String, UserTM> colPassword;

    @FXML
    private TextField searchId;

    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = cmbUserId.getValue();
        try {
            boolean delete = UserModel.delete(id);
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,"User is Deleted !").show();
                clear();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.ERROR,"User is not Deleted !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = cmbUserId.getValue();
        String name = userName.getText();
        String password1 = password.getText();
        UserDTO userDTO = new UserDTO(id, name, password1);
        if (saveBtn.getText().equals("Save")) {
            try {
                boolean save = UserModel.save(userDTO);
                if (save){
                    new Alert(Alert.AlertType.CONFIRMATION,"User is Saved !").show();
                    clear();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"User is not Saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            try {
                boolean update = UserModel.update(userDTO);
                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION,"User is Updated !").show();
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                    clear();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"User is not Updated !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void searchIdOnAction(ActionEvent event) {
        String id = searchId.getText();
        try {
            UserDTO userDTO = UserModel.searchId(id);
            cmbUserId.setValue(id);
            userName.setText(userDTO.getUserName());
            password.setText(userDTO.getPassword());
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
            UserDTO userDTO = UserModel.searchId(id);
            cmbUserId.setValue(id);
            userName.setText(userDTO.getUserName());
            password.setText(userDTO.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveBtn.setText("Update");
        saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void tblUserOnMouseClick(MouseEvent event) {
        UserTM selectedItem = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        try {
            UserDTO userDTO = UserModel.searchId(selectedItem.getUserId());
            cmbUserId.setValue(userDTO.getUserId());
            userName.setText(userDTO.getUserName());
            password.setText(userDTO.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveBtn.setText("Update");
        saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void userNameOnAction(ActionEvent event) {
        password.requestFocus();
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {
        userName.requestFocus();
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
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<String, UserTM>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<String, UserTM>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<String, UserTM>("password"));
    }

    private void getAll() {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<UserDTO> all = UserModel.getAll();
            for (UserDTO userDTO : all){
                observableList.add(new UserTM(
                        userDTO.getUserId(),
                        userDTO.getUserName(),
                        userDTO.getPassword()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblUser.setItems(observableList);
    }

    void clear(){
        cmbUserId.setValue("");
        userName.clear();
        password.clear();
    }
}
