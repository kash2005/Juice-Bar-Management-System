package lk.ijse.mvcproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lk.ijse.mvcproject.model.DeliveryModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeliveryFormController implements Initializable {
    @FXML
    private TextField deliveryId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateDeliveryId();
    }

    private void generateDeliveryId() {
        try {
            String id = DeliveryModel.generateDeliveryId();
            deliveryId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
