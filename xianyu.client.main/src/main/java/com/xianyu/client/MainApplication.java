package com.xianyu.client;

import com.xianyu.client.common.constant.FxmlConstant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.LOGIN_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("登录");
        stage.getIcons().add(new Image(FxmlConstant.ICON_PNG));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}