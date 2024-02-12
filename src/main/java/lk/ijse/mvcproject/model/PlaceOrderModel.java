package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.CartDTO;
import lk.ijse.mvcproject.dto.DeliveryDTO;
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

    public static boolean savePlaceOrderWithDelivery(OrderDTO orderDTO, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList, DeliveryDTO deliveryDTO) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSaveOrder = OrderModel.saveOrder(orderDTO);
            if (isSaveOrder){
                boolean isUpdateQty = ItemModel.updateQty(cartDTOList);
                if (isUpdateQty){
                    boolean isSaveOrderDetails = OrderDetailsModel.saveOrderDetails(orderDTO.getOrderId(),cartDTOList);
                    if (isSaveOrderDetails){
                        boolean isSaveDelivery = DeliveryModel.saveDelivery(deliveryDTO);
                        if (isSaveDelivery){
                            connection.commit();
                            return true;
                        }
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
