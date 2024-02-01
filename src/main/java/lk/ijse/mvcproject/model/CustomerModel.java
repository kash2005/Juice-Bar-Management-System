package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public static String generateCustomerId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select max(customerId) as lastCustomerId from customer";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String lastCustomerId = resultSet.getString("lastCustomerId");
                if (lastCustomerId == null){
                    return "C001";
                }else {
                    int nextId = Integer.parseInt(lastCustomerId.substring(1))+1;
                    System.out.println(nextId);
                    return "C" + String.format("%03d",nextId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean saveCustomer(String id,String name,String address,String email,String contact) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "insert into customer(customerId,name,address,email,contact) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,address);
        preparedStatement.setString(4,email);
        preparedStatement.setString(5,contact);
        int rowAffected = preparedStatement.executeUpdate();
        boolean isSaved = rowAffected != 0;
        return isSaved;
    }
}