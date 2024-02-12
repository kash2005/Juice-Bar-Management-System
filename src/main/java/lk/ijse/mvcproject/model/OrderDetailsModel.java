package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.CartDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModel {

    public static boolean saveOrderDetails(String orderId, List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO cartDTO : cartDTOList){
            if(!saveOrderDetails(orderId,cartDTO)){
                return false;
            }
        }
        return true;
    }
    public static boolean saveOrderDetails(String orderId,CartDTO cartDTO) throws SQLException {
        String sql = "insert into orderDetails(orderId,itemId,getQty,amount) values(?,?,?,?);";
        return CrudUtil.execute(sql,orderId,cartDTO.getItemId(),cartDTO.getQty(),(cartDTO.getPrice()* cartDTO.getQty()));
    }
}
