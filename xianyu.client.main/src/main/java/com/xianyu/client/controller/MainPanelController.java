package com.xianyu.client.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.xianyu.client.common.utils.DateUtils;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.springframework.util.CollectionUtils;

import com.xianyu.client.common.constant.FxmlConstant;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainPanelController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private List<Button> menus;

    @FXML
    private AreaChart<?, ?> chartPurchase;

    @FXML
    private AreaChart<?, ?> chartSale;

    @FXML
    private LineChart<?, ?> chartReceipt;

    @FXML
    private Button procurement;

    @FXML
    private Button maintenance;

    @FXML
    private Button QHSE;

    @FXML
    private Button operations;

    @FXML
    private Button settings;

    @FXML
    private HBox v1;

    @FXML
    private HBox v2;

    @FXML
    private HBox v3;

    @FXML
    private HBox v4;

    @FXML
    private HBox v5;

    @FXML
    private VBox v6;

    @FXML
    private VBox v7;

    @FXML
    private VBox v8;

    @FXML
    private VBox v9;

    @FXML
    private Text time;

    private Timer timer = new Timer();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadFXML("Page01View");
        initButtonAction(procurement);
        initButtonAction(maintenance);
        initButtonAction(QHSE);
        initButtonAction(operations);
        initButtonAction(settings);
        initButtonAction(v1);
        initButtonAction(v2);
        initButtonAction(v3);
        initButtonAction(v4);
        initButtonAction(v5);
        initButtonAction(v6);
        initButtonAction(v7);
        initButtonAction(v8);
        initButtonAction(v9);

        initButtonTime();

        v9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // 创建一个新的Stage作为弹窗
                Stage popupStage = new Stage();
                popupStage.setTitle("My profile");

                // 创建弹窗的内容
                VBox popupVBox = new VBox();
                popupVBox.setPrefSize(500, 200);
                popupVBox.setStyle("-fx-background-color: lightgray;");
                Text text = new Text("this is a popup show!!");
                text.setFont(Font.font("Arial", 24));
                HBox content = new HBox();
                content.setPrefSize(500,100);
                content.setAlignment(Pos.BOTTOM_CENTER);
                popupVBox.setAlignment(Pos.CENTER);
                popupVBox.getChildren().add(text);
                // 创建场景并设置到弹窗
                Scene popupScene = new Scene(popupVBox);
                popupStage.setScene(popupScene);

                // 显示弹窗
                popupStage.show();
            }
        });
    }

    private void initButtonTime() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time.setText(DateUtils.getDateStr(LocalDateTime.now()));
            }
        },100,1000);
    }

    private void initButtonAction(Node button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setAutoReverse(true);

        // 设置鼠标进入事件
        button.setOnMouseEntered(event -> {
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.playFromStart();
        });

        // 设置鼠标离开事件
        button.setOnMouseExited(event -> {
            scaleTransition.stop();
            scaleTransition.setRate(-1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.playFromStart();
            scaleTransition.setOnFinished(finishEvent -> {
                scaleTransition.setRate(1);
            });
        });

    }

    private void changeButtonBackground(ActionEvent e) {
        
        ObservableList<Node> nodes = ((HBox)(((VBox)(borderPane.getTop())).getChildren().get(0))).getChildren();
        Button clickedButton = (Button) e.getSource();
        VBox vBox = null;
        int index = -1;
        for(int i = 0;i < nodes.size();i++){

            Node node = nodes.get(i);
            if(node instanceof Button){
                Button button = (Button)node;
                if (clickedButton == button) {
                    Region line = new Region();
                    line.setMinHeight(1);
                    line.setMaxHeight(1);
                    line.setStyle("-fx-background-color: black;");
                    vBox = new VBox(button, line);
                    index = i;
                    break;
                } 
            }else if(node instanceof VBox){
                VBox exist = (VBox)node;
                ObservableList<Node> children = exist.getChildren();
                if(CollectionUtils.isEmpty(children)){
                    continue;
                }
                Button button = (Button)children.get(0);
                if (clickedButton == button && children.size() == 1) {
                    Region line = new Region();
                    line.setMinHeight(1);
                    line.setMaxHeight(1);
                    line.setStyle("-fx-background-color: black;");
                    exist.getChildren().add(line);
                    //break;
                }else{
                    if(children.size() >= 2){
                        children.remove(1);
                    }
                }
            }else{
                System.out.println("unexpect node type!");
            }
            
        }

        if(index > -1){
            nodes.add(index, vBox);
        }
      
    }

    @FXML
    private void clear() {
        borderPane.setCenter(null);
    }

    @FXML
    private void loadFXML(String fileName) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("../fxml/" + fileName + ".fxml"));
            VBox center = (VBox)borderPane.getCenter();
            center.getChildren().clear();
            center.getChildren().add(parent);
        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close() throws IOException {

        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource(FxmlConstant.LOGIN_FXML));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("User Login");
        stage.getIcons().add(new Image(FxmlConstant.ICON_PNG));
        stage.show();
    }

    @FXML
    private void loadProcurementView(ActionEvent e) {
        loadFXML("Page01View");
        changeButtonBackground(e);
    }

    @FXML
    private void loadMaintenancetView(ActionEvent e) {
        loadFXML("Page02View");
        changeButtonBackground(e);
    }

    @FXML
    private void loadQHSEView(ActionEvent e) {
        loadFXML("Page03View");
        changeButtonBackground(e);
    }

    @FXML
    private void loadOperationsView(ActionEvent e) {
        loadFXML("Page04View");
        changeButtonBackground(e);
    }

    @FXML
    private void loadSettingsView(ActionEvent e) {
        loadFXML("Page05View");
        changeButtonBackground(e);
    }

    @FXML
    private void loadHomeView(ActionEvent e) {
        loadFXML("HomeView");
        changeButtonBackground(e);
    }

    @FXML
    private void menuIn(ActionEvent e){

    }

    @FXML
    private void menuOut(ActionEvent e){

    }
}
