package com.xianyu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import com.xianyu.client.common.constant.FxmlConstant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@EnableFeignClients(basePackages = {"com.xianyu.client"})
@EnableDiscoveryClient
@SpringBootApplication
public class MainApplication extends Application {

    //spring boot context
    public static ConfigurableApplicationContext applicationContext;
    private static String[] args;

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
        MainApplication.args = args;
        launch(args);
    }

    @Override
    public void init() throws Exception{
        applicationContext = SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void stop() throws Exception {
        applicationContext.stop();
    }

}