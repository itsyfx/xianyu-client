package com.xianyu.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void changeButtonBackground(ActionEvent e) {
        
        ObservableList<Node> nodes = ((HBox)(borderPane.getTop())).getChildren();
        Button clickedButton = (Button) e.getSource();
        VBox vBox = null;
        int index = -1;
        for(int i = 0;i < nodes.size();i++){
            Button button = (Button)nodes.get(i);
            if (clickedButton == button) {
                Region line = new Region();
                line.setMinHeight(1);
                line.setMaxHeight(1);
                line.setStyle("-fx-background-color: black;");
                vBox = new VBox(button, line);
                index = i;
                break;
            } 
        }

        if(index > -1){
            nodes.remove(index);
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
            borderPane.setCenter(parent);

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
}
