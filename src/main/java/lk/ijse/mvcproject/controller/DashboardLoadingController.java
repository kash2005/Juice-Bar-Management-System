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

public class DashboardLoadingController implements Initializable {

    @FXML
    private AnchorPane menuAnchorpane;

    @FXML
    private AnchorPane juiceMenuAnchorPane;

    @FXML
    private AnchorPane snackMenuAnchorPane;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private ImageView customerGreyImg;

    @FXML
    private ImageView customerOrangeImg;

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

    @FXML
    void menuImgOnAction(ActionEvent event) throws IOException {
        menuAnchorpane.setVisible(true);
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) throws IOException {
        setForms(customerBtn,"/lk/ijse/mvcproject/view/customerForm.fxml",customerOrangeImg,customerGreyImg);
    }

    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        setForms(orderBtn,"/lk/ijse/mvcproject/view/orderForm.fxml",orderOrangeImg,orderGreyImg);
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

    void setForms(JFXButton btn,String path,ImageView image,ImageView greyImageView) throws IOException {
        JFXButton[] buttons = {customerBtn,itemBtn,ingredientBtn,orderBtn,supplierBtn};
        String[] forms = {"/lk/ijse/mvcproject/view/customerForm.fxml","/lk/ijse/mvcproject/view/itemForm.fxml",
                "/lk/ijse/mvcproject/view/ingredientForm.fxml","/lk/ijse/mvcproject/view/orderForm.fxml",
                "/lk/ijse/mvcproject/view/supplierForm.fxml"};
        ImageView[] imageViews = {customerOrangeImg,itemOrangeImg,ingredientOrangeImg,
                orderOrangeImg,supplierOrangeImg};
        ImageView[] greyImage = {customerGreyImg,itemGreyImg,ingredientGreyImg,
                orderGreyImg,supplierGreyImg};
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
    void snacksBtnOnAction(ActionEvent event) {
        snackMenuAnchorPane.setVisible(true);
    }

    @FXML
    void juiceBtnOnAction(ActionEvent event) {
        juiceMenuAnchorPane.setVisible(true);
    }

    @FXML
    void closeJuiceOnAction(ActionEvent event) {
        juiceMenuAnchorPane.setVisible(false);
    }

    @FXML
    void closeSnackImgOnAction(ActionEvent event) {
        snackMenuAnchorPane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuAnchorpane.setVisible(false);
        juiceMenuAnchorPane.setVisible(false);
        snackMenuAnchorPane.setVisible(false);
    }

    @FXML
    void userImgOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/mvcproject/view/userEditForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.setTitle("Juice Bar Management System - User Edit Page");
    }
}
