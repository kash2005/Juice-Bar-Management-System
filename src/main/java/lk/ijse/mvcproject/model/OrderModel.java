package lk.ijse.mvcproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.util.CrudUtil;

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
}
