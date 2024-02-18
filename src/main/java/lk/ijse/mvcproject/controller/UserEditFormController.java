package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.UserDTO;
import lk.ijse.mvcproject.model.UserModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserEditFormController implements Initializable {

    @FXML
    private TextField userId;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    public static UserDTO userDTO;

    @FXML
    private JFXButton updateBtn;

    @FXML
    void passwordOnAction(ActionEvent event) {
        updateBtn.fire();
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        String id = userId.getText();
        String name = userName.getText();
        String password1 = password.getText();
        UserDTO userDTO1 = new UserDTO(id, name, password1);
        try {
            boolean update = UserModel.update(userDTO1);
            if (update){
                new Alert(Alert.AlertType.CONFIRMATION,"User is Updated !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"User is not Updated !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void userIdOnAction(ActionEvent event) {
        userName.requestFocus();
    }

    @FXML
    void userNameOnAction(ActionEvent event) {
        password.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userId.setText(userDTO.getUserId());
        userName.setText(userDTO.getUserName());
        password.setText(userDTO.getPassword());
    }
}
