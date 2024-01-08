package lk.ijse.mvcproject.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingPageFormController implements Initializable {

    @FXML
    private ProgressBar progressgBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i,55);
                    Thread.sleep(55);
                }
                return null;
            }
        };

        progressgBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {

            Parent loginParent = null;
            try {
                loginParent = FXMLLoader.load(getClass().getResource("/lk/ijse/mvcproject/view/loginPageForm.fxml"));
                Scene loginScene = new Scene(loginParent);
                Stage loginStage = new Stage();
                loginStage.setResizable(false);
                Image image = new Image(getClass().getResourceAsStream("/lk/ijse/mvcproject/image/orange_logo.png"));
                loginStage.getIcons().add(image);
                loginStage.setTitle("Juice Bar Management System - Login Page");
                loginStage.setScene(loginScene);
                loginStage.show();
                ((Stage)progressgBar.getScene().getWindow()).close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        new Thread(task).start();
    }
}
