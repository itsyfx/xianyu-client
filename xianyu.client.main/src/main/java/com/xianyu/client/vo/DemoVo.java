package com.xianyu.client.vo;

import javafx.beans.property.SimpleStringProperty;

public class DemoVo {

    private SimpleStringProperty createDate = new SimpleStringProperty();

    private SimpleStringProperty vessel = new SimpleStringProperty();

    private SimpleStringProperty refNum = new SimpleStringProperty();

    private SimpleStringProperty subject = new SimpleStringProperty();

    private SimpleStringProperty RFQ = new SimpleStringProperty();

    private SimpleStringProperty sent = new SimpleStringProperty();

    private SimpleStringProperty answered = new SimpleStringProperty();

    private SimpleStringProperty urgency = new SimpleStringProperty();

    private SimpleStringProperty status = new SimpleStringProperty();

    private SimpleStringProperty label = new SimpleStringProperty();

    private SimpleStringProperty type = new SimpleStringProperty();

    private SimpleStringProperty brand = new SimpleStringProperty();

    private SimpleStringProperty remarks = new SimpleStringProperty();

    public String getCreateDate() {
        return createDate.get();
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }

    public String getVessel() {
        return vessel.get();
    }

    public SimpleStringProperty vesselProperty() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel.set(vessel);
    }

    public String getRefNum() {
        return refNum.get();
    }

    public SimpleStringProperty refNumProperty() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum.set(refNum);
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getRFQ() {
        return RFQ.get();
    }

    public SimpleStringProperty RFQProperty() {
        return RFQ;
    }

    public void setRFQ(String RFQ) {
        this.RFQ.set(RFQ);
    }

    public String getSent() {
        return sent.get();
    }

    public SimpleStringProperty sentProperty() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent.set(sent);
    }

    public String getAnswered() {
        return answered.get();
    }

    public SimpleStringProperty answeredProperty() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered.set(answered);
    }

    public String getUrgency() {
        return urgency.get();
    }

    public SimpleStringProperty urgencyProperty() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency.set(urgency);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getRemarks() {
        return remarks.get();
    }

    public SimpleStringProperty remarksProperty() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks.set(remarks);
    }
}

