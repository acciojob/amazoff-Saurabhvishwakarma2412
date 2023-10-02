package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        int left=Integer.parseInt(deliveryTime.substring(0,2))*60;
        int right=Integer.parseInt(deliveryTime.substring(3));
        this.deliveryTime=left+right;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
