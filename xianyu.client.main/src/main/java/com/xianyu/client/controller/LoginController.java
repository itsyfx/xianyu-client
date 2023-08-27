package com.xianyu.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xianyu.client.UserClient;
import com.xianyu.client.common.constant.FxmlConstant;
import com.xianyu.client.helper.AlertHelper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

@Component
public class LoginController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    Window window;

    @Autowired
    private UserClient userClient;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public LoginController() {

    }

    @FXML
    private void login() throws Exception {
        if (!this.isValidated()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Invalid username and password.");
            return;
        }
        userClient.login();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.HOME_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Admin Panel");
        stage.getIcons().add(new Image(FxmlConstant.ICON_PNG));
        stage.show();

    }

    private boolean isValidated() {
        //todo
        return Boolean.TRUE;
    }

    @FXML
    private void showRegisterStage() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.REGISTER_FXML));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("User Registration");
        stage.getIcons().add(new Image(FxmlConstant.ICON_PNG));
        stage.show();
    }
}
