package lk.ijse.mvcproject.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginPageFormController {
    @FXML
    private JFXButton loginBtn;

    @FXML
    private AnchorPane root;

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/mvcproject/view/dashboardForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        stage.setTitle("Juice Bar Management System - Dashboard Page");
        stage.centerOnScreen();
        stage.show();


    }
}
