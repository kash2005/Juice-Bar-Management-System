package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceModel {
    public static String generateId() throws SQLException {
        String sql = "select max(attendanceId) as lastId from attendance;";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "A001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "A"+String.format("%03d",nextId);
            }
        }
        return null;
    }
}
