package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.dto.tm.AddToCartTM;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static boolean saveOrder(OrderDTO orderDTO, OrderDetailsDTO orderDetailsDTO, AddToCartTM addToCartTM) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into orders(orderId,date,customerId) values (?,?,?);";
            boolean result = CrudUtil.execute(sql, orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId());
            System.out.println(result+" order");
            if (result){
                boolean isUpdated = ItemModel.updateQty(addToCartTM);
                System.out.println(isUpdated+" item");
                if (isUpdated){
                    System.out.println("sahan");
                    boolean isOrderDetailsSaved = OrderDetailsModel.saveOrderDetails(orderDetailsDTO);
                    System.out.println(isOrderDetailsSaved+" orderdetail");
                    if (isOrderDetailsSaved){
                        connection.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
