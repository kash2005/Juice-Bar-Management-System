package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {
    public static String generateDeliveryId() throws SQLException {
        String sql = "select max(deliveryId) as lastId from delivery;";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "D001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "D" + String.format("%03d",nextId);
            }
        }
        return null;
    }
}
