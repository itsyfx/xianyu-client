/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianyu.client.controller;

import com.xianyu.client.common.utils.DateUtils;
import com.xianyu.client.vo.DemoVo;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Ramesh Godara
 */
public class Page01Controller implements Initializable {

    @FXML
    private TableView<DemoVo> tableView;

    @FXML
    private TextArea remark;

    @FXML
    private TextField subject;

    @FXML
    private ComboBox<String> urgency;

    @FXML
    private ComboBox<String> status;

    @FXML
    private Text date;

    @FXML
    private Text label;

    @FXML
    private Text type;

    @FXML
    private Text brand;

    @FXML
    private Text vessel;

    @FXML
    private Text refNum;

    @FXML
    private ScrollPane scrollPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableView();
    }


    private void initTableView(){
        tableView.setItems(null);

        List<TableColumn<DemoVo, String>> tableColumns = initColumn();
        tableView.getColumns().addAll(tableColumns);

        List<DemoVo> demoVos = initTableviewData("");
        tableView.setItems(FXCollections.observableList(demoVos));

        bindTableViewListener();

        initLeft(demoVos);

    }

    private void initLeft(List<DemoVo> demoVos) {
        if(demoVos == null || demoVos.isEmpty()){
            return;
        }
        VBox content = new VBox();
        Map<String,Integer> map = new HashMap<>();
        for(DemoVo vo : demoVos){
            map.putIfAbsent(vo.getVessel(), 0);
            map.put(vo.getVessel(),map.get(vo.getVessel())+1);
        }
        List<HBox> hBoxes = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            HBox button = createButton(entry.getKey(), entry.getValue());
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
            hBoxes.add(button);
        }
        content.getChildren().addAll(FXCollections.observableArrayList(hBoxes));
        scrollPane.setContent(content);
    }

    private HBox createButton(String name,Integer count){
        HBox parent = new HBox();
        Button button = new Button(name);

        button.setPrefWidth(100);
        button.setStyle("-fx-border-color: #F3F2F1;-fx-background-color: #F3F2F1;");
        HBox son = new HBox();
        son.setAlignment(Pos.CENTER_RIGHT);
        son.setMinWidth(100);
        Button bNum = new Button(count.toString());
        bNum.setStyle("-fx-background-color: lightgray;-fx-background-radius: 50%;");
        son.getChildren().add(bNum);
        parent.getChildren().addAll(button,son);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                List<DemoVo> demoVos = initTableviewData(name);
                tableView.setItems(FXCollections.observableList(demoVos));
                bNum.setStyle("-fx-background-color: red;-fx-background-radius: 50%;");
            }
        });
        return parent;
    }

    private void bindTableViewListener() {

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DemoVo>() {
            @Override
            public void changed(ObservableValue<? extends DemoVo> observable, DemoVo oldValue, DemoVo newValue) {
                date.setText("                "+newValue.getCreateDate());
                type.setText("                "+newValue.getType());
                brand.setText("              "+newValue.getBrand());
                label.setText("                "+newValue.getLabel());
                vessel.setText("              "+newValue.getVessel());
                refNum.setText("              "+newValue.getRefNum());
                remark.setText(newValue.getRemarks());
                subject.setText(newValue.getSubject());
                urgency.setItems(FXCollections.observableArrayList("Urgent","","Next quanlity"));
                urgency.setValue(newValue.getUrgency());
                status.setItems(FXCollections.observableArrayList("Offered","Ordered","PR received"));
                status.setValue(newValue.getStatus());
            }
        });

    }


    private List<TableColumn<DemoVo,String>> initColumn(){
        List<TableColumn<DemoVo,String>> cols = new ArrayList<>();
        // 创建列
        TableColumn<DemoVo, String> c1 = new TableColumn<>("Creation date");
        c1.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        cols.add(c1);

        TableColumn<DemoVo, String> c2 = new TableColumn<>("Vessel");
        c2.setCellValueFactory(new PropertyValueFactory<>("vessel"));
        cols.add(c2);

        TableColumn<DemoVo, String> c3 = new TableColumn<>("Reference number");
        c3.setCellValueFactory(new PropertyValueFactory<>("refNum"));
        cols.add(c3);

        TableColumn<DemoVo, String> c4 = new TableColumn<>("Subject");
        c4.setCellValueFactory(new PropertyValueFactory<>("subject"));
        cols.add(c4);

        TableColumn<DemoVo, String> c5 = new TableColumn<>("RFQ state");
        c5.setCellValueFactory(new PropertyValueFactory<>("RFQ"));
        c5.setCellFactory(new Callback<TableColumn<DemoVo, String>, TableCell<DemoVo, String>>() {
            @Override
            public TableCell<DemoVo, String> call(TableColumn<DemoVo, String> param) {
                return new TableCell<DemoVo, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText("");
                            setStyle("");
                        } else {
                            // 根据年龄设置不同的背景颜色
                            if ("0".equals(item)) {
                                setTextFill(Color.RED);
                            } else if ("1".equals(item)) {
                                setTextFill(Color.GREEN);
                            } else {
                                setTextFill(Color.BLUE);
                            }
                            setText(item);
                        }
                    }
                };
            }
        });
        cols.add(c5);

        TableColumn<DemoVo, String> c6 = new TableColumn<>("Sent");
        c6.setCellValueFactory(new PropertyValueFactory<>("sent"));
        cols.add(c6);

        TableColumn<DemoVo, String> c7 = new TableColumn<>("Answered");
        c7.setCellValueFactory(new PropertyValueFactory<>("answered"));
        cols.add(c7);

        TableColumn<DemoVo, String> c8 = new TableColumn<>("Urgency");
        c8.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        cols.add(c8);

        TableColumn<DemoVo, String> c9 = new TableColumn<>("Status");
        c9.setCellValueFactory(new PropertyValueFactory<>("status"));
        cols.add(c9);

        TableColumn<DemoVo, String> c10 = new TableColumn<>("Label");
        c10.setCellValueFactory(new PropertyValueFactory<>("label"));
        cols.add(c10);

        TableColumn<DemoVo, String> c11 = new TableColumn<>("Type");
        c11.setCellValueFactory(new PropertyValueFactory<>("type"));
        cols.add(c11);

        TableColumn<DemoVo, String> c12 = new TableColumn<>("Brand");
        c12.setCellValueFactory(new PropertyValueFactory<>("brand"));
        cols.add(c12);

        TableColumn<DemoVo, String> c13 = new TableColumn<>("Remarks");
        c13.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        cols.add(c13);

        return cols;

    }

    List<DemoVo> initTableviewData(String vesselName){
        String content = readResourceFile("fake/view.txt");
        System.out.println(content);
        List<DemoVo> result = new ArrayList<>();
        for(String row : content.split("\n")) {
            String[] columns = row.split("#");
            if(!"".equals(vesselName)){
                if(!columns[1].equals(vesselName)){
                    continue;
                }
            }
            DemoVo demoVo = new DemoVo();
            demoVo.setAnswered(columns[6]);
            demoVo.setBrand(columns[11]);
            LocalDateTime date = DateUtils.getDate(columns[0]);
            demoVo.setCreateDate(DateUtils.getDateStr(
                    date.withHour(new Random().nextInt(23))
                          .withMinute(new Random().nextInt(59))
                            .withSecond(new Random().nextInt(59))));
            demoVo.setLabel(columns[9]);
            demoVo.setRemarks(columns[12]);
            demoVo.setRFQ(columns[4]);
            demoVo.setSent(columns[5]);
            demoVo.setType(columns[10]);
            demoVo.setStatus(columns[8]);
            demoVo.setSubject(columns[3]);
            demoVo.setUrgency(columns[7]);
            demoVo.setVessel(columns[1]);
            demoVo.setRefNum(columns[2]);
            result.add(demoVo);
        }
        return result;
    }

    private static String readResourceFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (InputStream inputStream = Page01Controller.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return contentBuilder.toString();
    }

}
