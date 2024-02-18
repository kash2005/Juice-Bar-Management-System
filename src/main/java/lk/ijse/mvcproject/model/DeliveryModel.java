package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.DeliveryDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryModel {
    public static String generateDeliveryId() throws SQLException {
        String sql = "select max(deliveryId) as lastId from delivery;";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "D001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "D" + String.format("%03d",nextId);
            }
        }
        return null;
    }

    public static boolean saveDelivery(DeliveryDTO deliveryDTO) throws SQLException {
        String sql = "insert into delivery(deliveryId,distance,price,orderId) values(?,?,?,?);";
        return CrudUtil.execute(sql,deliveryDTO.getDeliveryId(),deliveryDTO.getDistance(),deliveryDTO.getPrice(),deliveryDTO.getOrderId());
    }

    public static String getAllDelivery(String id) throws SQLException {
        String sql = "select * from delivery where orderId = ?;";
        DeliveryDTO deliveryDTO = null;
        String status = "";
        ResultSet resultSet = CrudUtil.execute(sql,id);
        if (resultSet.next()){
            String deliveryId = resultSet.getString("deliveryId");
            String distance = resultSet.getString("distance");
            double price = resultSet.getDouble("price");
            String orderId = resultSet.getString("orderId");
            deliveryDTO = new DeliveryDTO(deliveryId,distance,price,orderId);
            System.out.println(deliveryId+" "+distance+" "+price+" "+orderId);
        }
//        if (deliveryDTO==null){
//            status = "No";
//        }else {
//            status= "yes";
//        }
        return status;
    }
}
