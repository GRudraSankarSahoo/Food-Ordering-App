package com.example.foodapp;

public class OdersModel {

    int orderImage;
    String orderName,orderPrice,orderNumber;

    public OdersModel(int orderImage, String orderName, String orderPrice, String orderNumber) {
        this.orderImage = orderImage;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
    }

    public OdersModel(){
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int oderImage) {
        this.orderImage = oderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
