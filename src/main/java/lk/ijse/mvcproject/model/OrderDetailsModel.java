package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailsModel {

    public static boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        System.out.println("amma");
        String sql = "insert into orderDetails(orderId,itemId,getQty,amount) values(?,?,?,?);";
        boolean b = CrudUtil.execute(sql, orderDetailsDTO.getOrderId(), orderDetailsDTO.getItemId(), orderDetailsDTO.getGetQty(), orderDetailsDTO.getAmount());
        System.out.println(b+"  orderdetail  model");
        return true;
    }
}
