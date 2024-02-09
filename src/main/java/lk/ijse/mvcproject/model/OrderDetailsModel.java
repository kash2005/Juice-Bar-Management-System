package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailsModel {

    public static boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into orderDetails(orderId,itemId,getQty,amount) values(?,?,?,?);";
            boolean result = CrudUtil.execute(sql, orderDetailsDTO.getOrderId(), orderDetailsDTO.getItemId(), orderDetailsDTO.getGetQty(), orderDetailsDTO.getAmount());
            if (result){
                connection.commit();
            }else{
                connection.rollback();
            }
            return result;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close(); // Close the connection
            }
        }
    }
}
