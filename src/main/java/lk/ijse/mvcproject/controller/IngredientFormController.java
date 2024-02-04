package lk.ijse.mvcproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.dto.IngredientDTO;
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
        String id = ingredientId.getText();
        try {
            boolean isDeleted = IngredientModel.deleteIngredient(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Ingredient is deleted ").show();
                clearTextFields();
                generateIngredientId();
            }else {
                new Alert(Alert.AlertType.ERROR,"Ingredient is not deleted ").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String id = ingredientId.getText();
        String description = ingredientDescription.getText();
        String weight = ingredientWeight.getText();
        Double price = Double.valueOf(ingredientPrice.getText());
        IngredientDTO ingredientDTO = new IngredientDTO(id,description,price,weight);
        if (saveBtn.getText().equals("Save")){
            try {
                boolean isSaved = IngredientModel.saveIngredient(ingredientDTO);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Ingredient is saved !").show();
                    clearTextFields();
                    generateIngredientId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Ingredient is not saved").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
            try {
                boolean isUpdate = IngredientModel.updateIngredient(ingredientDTO);
                if (isUpdate){
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
                    new Alert(Alert.AlertType.CONFIRMATION,"Ingredient is updated !").show();
                    clearTextFields();
                    generateIngredientId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Ingredient is not updated !").show();
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
            IngredientDTO ingredientDTO = IngredientModel.searchIngredientId(id);
            if (ingredientDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
                String ingredientId1 = ingredientDTO.getIngredientId();
                String description = ingredientDTO.getDescription();
                double price = ingredientDTO.getPrice();
                String weight = ingredientDTO.getWeight();
                ingredientId.setText(ingredientId1);
                ingredientDescription.setText(description);
                ingredientWeight.setText(weight);
                ingredientPrice.setText(String.valueOf(price));
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    private void clearTextFields(){
        ingredientId.clear();
        ingredientDescription.clear();
        ingredientWeight.clear();
        ingredientPrice.clear();
    }
}


