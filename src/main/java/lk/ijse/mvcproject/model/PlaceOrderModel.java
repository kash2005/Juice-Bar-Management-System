package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.CartDTO;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderModel {

    public static boolean savePlaceOrder(OrderDTO orderDTO, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSave = OrderModel.saveOrder(orderDTO);
            if (isOrderSave){
                boolean isUpdatedItem = ItemModel.updateQty(cartDTOList);
                if (isUpdatedItem){
                    boolean isSaveOrderDetails = OrderDetailsModel.saveOrderDetails(orderDTO.getOrderId(),cartDTOList);
                    if (isSaveOrderDetails){
                        connection.commit();
                        return true;
                    }
                }
            }else {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
