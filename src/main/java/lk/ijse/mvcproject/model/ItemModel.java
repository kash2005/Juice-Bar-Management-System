package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.ItemDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemModel {
    public static String generateItemId() throws SQLException {
        String sql = "select max(itemId) as lastItemId from item;";
        try {
            ResultSet resultSet = CrudUtil.execute(sql);
            if (resultSet.next()){
                String lastItemId = resultSet.getString("lastItemId");
                if (lastItemId == null){
                    return "I001";
                }else {
                     int nextId = Integer.parseInt(lastItemId.substring(1))+1;
                    return "I" + String.format("%03d",nextId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean saveItem(ItemDTO itemDTO) throws SQLException {
        String sql = "insert into item(itemId,description,qty,price) values(?,?,?,?);";
        return CrudUtil.execute(sql,itemDTO.getItemId(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice());
    }
}
