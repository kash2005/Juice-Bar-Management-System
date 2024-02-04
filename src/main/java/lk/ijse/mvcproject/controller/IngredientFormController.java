package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.model.IngredientModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IngredientFormController implements Initializable {
    @FXML
    private TextField ingredientId;

    @FXML
    private TextField ingredientDescription;

    @FXML
    private TextField ingredientPrice;

    @FXML
    private TextField ingredientWeight;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<?> tblIngredient;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblContact;

    @FXML
    private TableColumn<?, ?> tblEmail;

    @FXML
    private TextField searchId;

    @FXML
    private JFXButton searchImg;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void descriptionTxtOnAction(ActionEvent event) {
        ingredientWeight.requestFocus();
    }

    @FXML
    void priceTxtOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchIdOnAction(ActionEvent event) {

    }

    @FXML
    void weightTxtOnAction(ActionEvent event) {
        ingredientPrice.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateIngredientId();

    }

    private void generateIngredientId() {
        try {
            String id = IngredientModel.generateIngredientId();
            ingredientId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


