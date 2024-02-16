package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton dashboardBtn;

    @FXML
    private ImageView dashboardOrangeImg;

    @FXML
    private ImageView dashboardGreyImg;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private ImageView employeeGreyImg;

    @FXML
    private ImageView employeeOrangeImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setForms(dashboardBtn,"/lk/ijse/mvcproject/view/DashboardLoadingForm.fxml",dashboardOrangeImg,dashboardGreyImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        setForms(dashboardBtn,"/lk/ijse/mvcproject/view/DashboardLoadingForm.fxml",dashboardOrangeImg,dashboardGreyImg);
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) throws IOException {
        setForms(employeeBtn,"/lk/ijse/mvcproject/view/employeeForm.fxml",employeeOrangeImg,employeeGreyImg);
    }

    void setForms(JFXButton btn,String path,ImageView image,ImageView greyImageView) throws IOException {
        JFXButton[] buttons = {dashboardBtn,employeeBtn};
        String[] forms = {"/lk/ijse/mvcproject/view/DashboardLoadingForm.fxml","/lk/ijse/mvcproject/view/employeeForm.fxml"};
        ImageView[] imageViews = {dashboardOrangeImg,employeeOrangeImg};
        ImageView[] greyImage = {dashboardGreyImg,employeeGreyImg};
        for (int i = 0; i < forms.length; i++) {
            if (btn.equals(buttons[i]) && path.equals(forms[i]) && image.equals(imageViews[i]) && greyImageView.equals(greyImage[i])){
                Parent load = FXMLLoader.load(getClass().getResource(forms[i]));
                root.getChildren().clear();
                root.getChildren().add(load);
                buttons[i].setStyle("-fx-text-fill: orange");
                imageViews[i].setVisible(true);
                greyImage[i].setVisible(false);
            }else {
                buttons[i].setStyle("-fx-text-fill: #757575");
                imageViews[i].setVisible(false);
                greyImage[i].setVisible(true);
            }
        }
    }

    @FXML
    void signoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk/ijse/mvcproject/view/loginPageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Juice Bar Management System - Login Page");
    }

}
