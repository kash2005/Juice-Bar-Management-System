package lk.ijse.mvcproject.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.mvcproject.dto.UserDTO;
import lk.ijse.mvcproject.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {
    @FXML
    private TextField userNameId;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private PasswordField passwordId;

    @FXML
    private AnchorPane root;

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        String userName = userNameId.getText();
        String password = passwordId.getText();
        try {
            UserDTO user = UserModel.getUser(userName);
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/mvcproject/view/dashboardForm.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(anchorPane));
                Stage stage1 = (Stage) root.getScene().getWindow();
                stage1.close();
                stage.setTitle("Juice Bar Management System - Dashboard Page");
                stage.centerOnScreen();
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
