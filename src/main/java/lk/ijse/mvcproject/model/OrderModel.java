package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.db.DbConnection;
import lk.ijse.mvcproject.dto.OrderDTO;
import lk.ijse.mvcproject.dto.OrderDetailsDTO;
import lk.ijse.mvcproject.dto.tm.AddToCartTM;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public static boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        String sql = "insert into orders(orderId,date,customerId) values (?,?,?);";
        return CrudUtil.execute(sql, orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId());
    }

    public static OrderDTO searchOrderId(String orderId) throws SQLException {
        String sql = "select * from orders where orderId = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, orderId);
        OrderDTO orderDTO = null;
        while (resultSet.next()){
            String orderId1 = resultSet.getString("orderId");
            LocalDate date = LocalDate.parse(resultSet.getString("date"));
            String customerId = resultSet.getString("customerId");
            orderDTO = new OrderDTO(orderId1,date,customerId);
        }
        return orderDTO;
    }

    public static Integer[] lineChart() throws SQLException {
        Integer[] data = new Integer[12];
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;

        String sql = "SELECT MONTH(date), COUNT(orderId) FROM orders GROUP BY MONTH(date) ";

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            switch (resultSet.getString(1)) {

                case "1":
                    jan = Integer.parseInt(resultSet.getString(2));
                    break;
                case "2":
                    feb = Integer.parseInt(resultSet.getString(2));
                    break;
                case "3":
                    mar = Integer.parseInt(resultSet.getString(2));
                    break;
                case "4":
                    apr = Integer.parseInt(resultSet.getString(2));
                    break;
                case "5":
                    may = Integer.parseInt(resultSet.getString(2));
                    break;
                case "6":
                    jun = Integer.parseInt(resultSet.getString(2));
                    break;
                case "7":
                    jul = Integer.parseInt(resultSet.getString(2));
                    break;
                case "8":
                    aug = Integer.parseInt(resultSet.getString(2));
                    break;
                case "9":
                    sep = Integer.parseInt(resultSet.getString(2));
                    break;
                case "10":
                    oct = Integer.parseInt(resultSet.getString(2));
                    break;
                case "11":
                    nov = Integer.parseInt(resultSet.getString(2));
                    break;
                case "12":
                    dec = Integer.parseInt(resultSet.getString(2));
                    break;

            }

            data[0] = jan;
            data[1] = feb;
            data[2] = mar;
            data[3] = apr;
            data[4] = may;
            data[5] = jun;
            data[6] = jul;
            data[7] = aug;
            data[8] = sep;
            data[9] = oct;
            data[10] = nov;
            data[11] = dec;
        }
        return data;

    }
}
