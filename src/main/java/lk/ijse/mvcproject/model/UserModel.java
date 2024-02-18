package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.UserDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {

    public static UserDTO getUser(String userName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select * from user where userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();//table ek represent karanne result set ehekin
        if (resultSet.next()){//table eke row ek check karanw data tyenw nam ilaga ekt paninw
            UserDTO userDTO = new UserDTO(
                    resultSet.getString("userId"),
                    resultSet.getString("userName"),
                    resultSet.getString("password")
            );
            return userDTO;
        }
        return null;
    }


    public static ArrayList<UserDTO> getAll() throws SQLException {
        String sql = "select * from user;";
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            String userId = resultSet.getString("userId");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            UserDTO userDTO = new UserDTO(userId, userName, password);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public static UserDTO searchId(String id) throws SQLException {
        String sql = "select * from user where userId = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        UserDTO userDTO = null;
        if (resultSet.next()){
            String userId = resultSet.getString("userId");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            userDTO = new UserDTO(userId, userName, password);
        }
        return userDTO;
    }

    public static boolean save(UserDTO userDTO) throws SQLException {
        String sql = "insert into user(userId,userName,password) values(?,?,?);";
        return CrudUtil.execute(sql,userDTO.getUserId(),userDTO.getUserName(),userDTO.getPassword());
    }

    public static boolean update(UserDTO userDTO) throws SQLException {
        String sql = "update user set userName = ?,password = ? where userId = ?;";
        return CrudUtil.execute(sql,userDTO.getUserName(),userDTO.getPassword(),userDTO.getUserId());
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "delete from user where userId = ?;";
        return CrudUtil.execute(sql,id);
    }
}
