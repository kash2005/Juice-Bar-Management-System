package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
