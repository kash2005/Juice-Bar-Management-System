package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.dto.tm.AddToCartTM;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderModel {

    public static String generateOrderId() throws SQLException {
        String sql = "select max(orderId) as lastOrder from orders";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String lastId = resultSet.getString("lastOrder");
            if (lastId == null){
                return "OR001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(2))+1;
                return "OR"+String.format("%03d",nextId);
            }
        }
        return null;
    }

    public static boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        String sql = "insert into orders(orderId,date,customerId) values (?,?,?);";
        return CrudUtil.execute(sql, orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId());
    }

    public static OrderDTO searchOrderId(String orderId) throws SQLException {
        String sql = "select * from orders where orderId = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, orderId);
        OrderDTO orderDTO = null;
        while (resultSet.next()){
            String orderId1 = resultSet.getString("orderId");
            LocalDate date = LocalDate.parse(resultSet.getString("date"));
            String customerId = resultSet.getString("customerId");
            orderDTO = new OrderDTO(orderId1,date,customerId);
        }
        return orderDTO;
    }
}
