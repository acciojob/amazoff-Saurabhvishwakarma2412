package com.driver;

import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> hsorder=new HashMap();
    HashMap<String,DeliveryPartner> hspartner=new HashMap<>();
    HashMap<Order,DeliveryPartner> Pairmap=new HashMap<>();
    public void addOrderRepository(Order order){
        hsorder.put(order.getId(),order);
    }
    public void addPartnerRepository(String partnerid){
        hspartner.put(partnerid,new DeliveryPartner(partnerid));
    }
    public void repositoryPair(String orderId,String partnerId){
        Order order=hsorder.get(orderId);
        DeliveryPartner partner=hspartner.get(partnerId);
        int ordercount=partner.getNumberOfOrders();
        partner.setNumberOfOrders(ordercount+1);
        Pairmap.put(order,partner);
    }
    public Order getOrderByIdRepository(String orderId){
        return hsorder.get(orderId);
    }
    public DeliveryPartner getPartnerByIdRepository(String partnerId){
        return hspartner.get(partnerId);
    }
    public List<String>  getOrdersByPartnerIdRepo(String partnerId){
        List<String> ans=new ArrayList<>();
        for(Order order:Pairmap.keySet()){
            DeliveryPartner partner=Pairmap.get(order);
            if(partner.getId().equals(partnerId)){
                ans.add(order.getId());
            }
        }
        return ans;
    }
    public List<String> getAllOrdersRepo(){
        List<String> ans=new ArrayList<>();
        for(String orderid:hsorder.keySet()){
            ans.add(orderid);
        }
        return ans;
    }
    public Integer getCountOfUnassignedOrdersRepo(){
        Integer ans=hsorder.size()-Pairmap.size();
        return ans;
    }

    public Integer numofindeliverdorder(int Time,String partnerId){
        int count=0;
        for(Order order:Pairmap.keySet()){
            DeliveryPartner partner=Pairmap.get(order);

            if(partner.getId().equals(partnerId) && order.getDeliveryTime()>Time){
                count++;
            }

        }
        Integer ans=count;
        return ans;
    }
    public int getmaxordertimeofpartner(String partnerId){
        int max=0;
        for(Order order :Pairmap.keySet()){
            DeliveryPartner partner=Pairmap.get(order);

            if(partner.getId().equals(partnerId)){
                max=Math.max(max,order.getDeliveryTime());
            }
        }
        return max;
    }
    public void deleteproductpair(String partnerId){

        for(Order order :Pairmap.keySet()) {
            DeliveryPartner partner = Pairmap.get(order);
            if(partner.getId().equals(partnerId)){
                int num=partner.getNumberOfOrders();
                partner.setNumberOfOrders(num-1);
                Pairmap.remove(order);
            }
        }
        hspartner.remove(partnerId);
    }
    public void deleteproductpairbyidrepo(String orderId){
        Order order=hsorder.get(orderId);

        DeliveryPartner partner=Pairmap.get(order);
        int numorder=partner.getNumberOfOrders();
        partner.setNumberOfOrders(numorder-1);
        Pairmap.remove(order);
        hsorder.remove(orderId);
    }


}
