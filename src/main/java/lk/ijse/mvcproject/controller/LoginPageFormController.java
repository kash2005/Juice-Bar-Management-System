package lk.ijse.mvcproject.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.mvcproject.dto.UserDTO;
import lk.ijse.mvcproject.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageFormController implements Initializable {
    @FXML
    private TextField userNameId;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private PasswordField passwordId;

    @FXML
    private TextField textPasswordId;

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView showImg;

    @FXML
    private ImageView hideImg;

    @FXML
    void hidePasswordOnMouseClicked(MouseEvent event) {
        passwordId.setText(textPasswordId.getText());
        hideImg.setVisible(true);
        passwordId.setVisible(true);
        showImg.setVisible(false);
        textPasswordId.setVisible(false);
    }

    @FXML
    void showPasswordOnMouseClicked(MouseEvent event) {
        textPasswordId.setText(passwordId.getText());
        showImg.setVisible(true);
        textPasswordId.setVisible(true);
        hideImg.setVisible(false);
        passwordId.setVisible(false);
    }

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

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        loginBtn.fire();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        passwordId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameId.requestFocus();
        textPasswordId.setVisible(false);
        hideImg.setVisible(true);
        passwordId.setVisible(true);
    }
}
