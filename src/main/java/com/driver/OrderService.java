package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {


    @Autowired
    public OrderRepository repobj;


    public void addOrderService(Order order){
        repobj.addOrderRepository(order);
    }
    public void addPartnerService(String partnerid){
        repobj.addPartnerRepository(partnerid);
    }
    public void servicePair(String orderId,String partnerId){
        repobj.repositoryPair(orderId,partnerId);
    }
    public Order getOrderByIdSrevice(String orderId){
       return repobj.getOrderByIdRepository(orderId);
    }
    public DeliveryPartner getPartnerByIdService(String partnerId){
        return repobj.getPartnerByIdRepository(partnerId);
    }
    public Integer getOrderCountByPartnerIdService(String partnerId){
        DeliveryPartner partner=repobj.getPartnerByIdRepository(partnerId);
        Integer ans=partner.getNumberOfOrders();
        return ans;
    }
    public List<String> getOrdersByPartnerIdService(String partnerId){
        return repobj.getOrdersByPartnerIdRepo(partnerId);
    }
    public List<String> getAllOrdersService(){
        return repobj.getAllOrdersRepo();
    }
    public Integer getCountOfUnassignedOrdersService(){
        Integer ans=repobj.getCountOfUnassignedOrdersRepo();
        return ans;
    }
    public Integer numofundeliverdorder(String time,String partnerId){
        int left=Integer.parseInt(time.substring(0,2))*60;
        int right=Integer.parseInt(time.substring(3));
        int Time=left+right;
        Integer ans=repobj.numofindeliverdorder(Time,partnerId);
        return ans;
    }
    public  String getLastOrderofPartner(String partnerId){
        int time=repobj.getmaxordertimeofpartner(partnerId);
        int min=time%60;
        int hour=time/60;
        String left="";
        String right="";
        if(min<=9){
            right="0"+min;
        }else{
            right=""+min;
        }
        if(hour <=9){
            left="0"+hour;
        }else{
            left=""+hour;
        }
        String ans=left+":"+right;
        return ans;
    }
    public  void deletePartnerByIdservice(String partnerId){
        repobj.deleteproductpair(partnerId);
    }
    public void deleteOrderByIdservice(String orderId){
        repobj.deleteproductpairbyidrepo(orderId);
    }

}
