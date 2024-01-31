package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;

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
                    return "C" + String.format("%3d",nextId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
