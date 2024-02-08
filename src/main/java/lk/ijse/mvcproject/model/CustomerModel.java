package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.CustomerDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static String generateCustomerId() throws SQLException {
        String sql = "select max(customerId) as lastCustomerId from customer";
        try {
            ResultSet resultSet = CrudUtil.execute(sql);
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

    public static boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        String sql = "insert into customer(customerId,name,address,email,contact) values (?,?,?,?,?)";
        return CrudUtil.execute(sql,customerDTO.getCustomerId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getContact());
    }

    public static boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        String sql = "update customer set name = ?,address = ?,email = ?,contact = ? where customerId = ?;";
        return CrudUtil.execute(sql,customerDTO.getName(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getContact(),customerDTO.getCustomerId());
    }

    public static CustomerDTO searchCustomer(String id) throws SQLException {
        String sql = "select * from customer where customerId = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);
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

    public static boolean deleteCustomer(String id) throws SQLException {
        String sql = "delete from customer where customerId = ?;";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<CustomerDTO> getAll() throws SQLException {
        String sql = "select * from customer;";
        ArrayList<CustomerDTO> customerDTOArrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString("customerId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("contact")
            );
            customerDTOArrayList.add(customerDTO);
        }
        return customerDTOArrayList;
    }

    public static ArrayList<String> setCustomerId() throws SQLException {
        String sql = "select customerId from customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        ArrayList<String> arrayList = new ArrayList<>();
        while (resultSet.next()){
            String customerId = resultSet.getString("customerId");
            arrayList.add(customerId);
        }
        return arrayList;
    }

//    public static String setCustomerName(String value) {
//        String sql = "select * from customer";
//        r
//    }
}