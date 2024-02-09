package lk.ijse.mvcproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into orders(orderId,date,customerId) values (?,?,?);";
            boolean result = CrudUtil.execute(sql, orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId());
            if (result){
                connection.commit();
            }else {
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
