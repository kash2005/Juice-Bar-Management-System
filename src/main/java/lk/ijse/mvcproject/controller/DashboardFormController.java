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

public class DashboardFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView dashboardOrangeImg;

    @FXML
    private JFXButton dashboardBtn;

    @FXML
    private ImageView dashboardGreyImg;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private ImageView customerGreyImg;

    @FXML
    private ImageView customerOrangeImg;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private ImageView employeeGreyImg;

    @FXML
    private ImageView employeeOrangeImg;

    @FXML
    private JFXButton itemBtn;

    @FXML
    private ImageView itemGreyImg;

    @FXML
    private ImageView itemOrangeImg;

    @FXML
    private JFXButton ingredientBtn;

    @FXML
    private ImageView ingredientGreyImg;

    @FXML
    private JFXButton orderBtn;

    @FXML
    private ImageView orderGreyImg;

    @FXML
    private JFXButton supplierBtn;

    @FXML
    private ImageView supplierGreyImg;

    @FXML
    private ImageView ingredientOrangeImg;

    @FXML
    private ImageView orderOrangeImg;

    @FXML
    private ImageView supplierOrangeImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setForms(dashboardBtn,"/lk/ijse/mvcproject/view/DashboardLoadingForm.fxml",dashboardOrangeImg,dashboardGreyImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) throws IOException {
        setForms(customerBtn,"/lk/ijse/mvcproject/view/customerForm.fxml",customerOrangeImg,customerGreyImg);
    }

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        setForms(dashboardBtn,"/lk/ijse/mvcproject/view/DashboardLoadingForm.fxml",dashboardOrangeImg,dashboardGreyImg);
    }

    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        setForms(orderBtn,"/lk/ijse/mvcproject/view/orderForm.fxml",orderOrangeImg,orderGreyImg);
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) throws IOException {
        setForms(employeeBtn,"/lk/ijse/mvcproject/view/employeeForm.fxml",employeeOrangeImg,employeeGreyImg);
    }

    @FXML
    void ingredientBtnOnAction(ActionEvent event) throws IOException {
        setForms(ingredientBtn,"/lk/ijse/mvcproject/view/ingredientForm.fxml",ingredientOrangeImg,ingredientGreyImg);
    }

    @FXML
    void itemBtnOnAction(ActionEvent event) throws IOException {
        setForms(itemBtn,"/lk/ijse/mvcproject/view/itemForm.fxml",itemOrangeImg,itemGreyImg);
    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        setForms(supplierBtn,"/lk/ijse/mvcproject/view/supplierForm.fxml",supplierOrangeImg,supplierGreyImg);
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

    void setForms(JFXButton btn,String path,ImageView image,ImageView greyImageView) throws IOException {
        JFXButton[] buttons = {dashboardBtn,customerBtn,employeeBtn,itemBtn,ingredientBtn,orderBtn,supplierBtn};
        String[] forms = {"/lk/ijse/mvcproject/view/DashboardLoadingForm.fxml","/lk/ijse/mvcproject/view/customerForm.fxml",
                "/lk/ijse/mvcproject/view/employeeForm.fxml","/lk/ijse/mvcproject/view/itemForm.fxml",
                "/lk/ijse/mvcproject/view/ingredientForm.fxml","/lk/ijse/mvcproject/view/orderForm.fxml",
                "/lk/ijse/mvcproject/view/supplierForm.fxml"};
        ImageView[] imageViews = {dashboardOrangeImg,customerOrangeImg,employeeOrangeImg,itemOrangeImg,ingredientOrangeImg,
        orderOrangeImg,supplierOrangeImg};
        ImageView[] greyImage = {dashboardGreyImg,customerGreyImg,employeeGreyImg,itemGreyImg,ingredientGreyImg,
        orderGreyImg,supplierGreyImg};
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
}
