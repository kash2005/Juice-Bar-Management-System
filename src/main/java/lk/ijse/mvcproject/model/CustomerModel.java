package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.CustomerDTO;
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

    public static boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "update customer set name = ?,address = ?,email = ?,contact = ? where customerId = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,customerDTO.getName());
        preparedStatement.setString(2,customerDTO.getAddress());
        preparedStatement.setString(3,customerDTO.getEmail());
        preparedStatement.setString(4,customerDTO.getContact());
        preparedStatement.setString(5,customerDTO.getCustomerId());
        int rowAffected = preparedStatement.executeUpdate();
        Boolean isUpdate = rowAffected != 0;

        return isUpdate;
    }

    public static CustomerDTO searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select * from customer where customerId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        CustomerDTO customerDTO = null;
        if (resultSet.next()){
            String customerId = resultSet.getString("customerId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String contact = resultSet.getString("contact");
            customerDTO = new CustomerDTO(customerId,name,address,email,contact);
        }
        return customerDTO;
    }
}