package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardLoaderController implements Initializable {
    @FXML
    private AnchorPane menuAnchorpane;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private ImageView employeeGreyImg;

    @FXML
    private ImageView employeeOrangeImg;

    @FXML
    private JFXButton attendanceBtn;

    @FXML
    private ImageView attendanceGreyImg;

    @FXML
    private ImageView attendanceOrangeImg;

    @FXML
    private JFXButton userBtn;

    @FXML
    private ImageView userGreyImg;

    @FXML
    private ImageView userOrangeImg;


    @FXML
    void userBtnOnAction(ActionEvent event) {
        try {
            setForms(userBtn,"/lk/ijse/mvcproject/view/userForm.fxml",userOrangeImg,userGreyImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void attendanceBtnOnAction(ActionEvent event) {
        try {
            setForms(attendanceBtn,"/lk/ijse/mvcproject/view/attendanceForm.fxml",attendanceOrangeImg,attendanceGreyImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) throws IOException {
        setForms(employeeBtn,"/lk/ijse/mvcproject/view/employeeForm.fxml",employeeOrangeImg,employeeGreyImg);
    }

    void setForms(JFXButton btn,String path,ImageView image,ImageView greyImageView) throws IOException {
        JFXButton[] buttons = {employeeBtn,attendanceBtn,userBtn};
        String[] forms = {"/lk/ijse/mvcproject/view/employeeForm.fxml", "/lk/ijse/mvcproject/view/attendanceForm.fxml",
                "/lk/ijse/mvcproject/view/userForm.fxml"};
        ImageView[] imageViews = {employeeOrangeImg,attendanceOrangeImg,userOrangeImg};
        ImageView[] greyImage = {employeeGreyImg,attendanceGreyImg,userGreyImg};
        for (int i = 0; i < forms.length; i++) {
            if (btn.equals(buttons[i]) && path.equals(forms[i]) && image.equals(imageViews[i]) && greyImageView.equals(greyImage[i])){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(forms[i]));
                Stage stage = new Stage();
                stage.setScene(new Scene(anchorPane));
                stage.show();
                menuAnchorpane.setVisible(false);
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
    void menuImgOnAction(ActionEvent event) throws IOException {
        menuAnchorpane.setVisible(true);
    }

    @FXML
    void userImgOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/mvcproject/view/userForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.setTitle("Juice Bar Management System - User Page");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuAnchorpane.setVisible(false);
    }
}
