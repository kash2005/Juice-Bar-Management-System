package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.AttendanceDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

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

    public static ArrayList<AttendanceDTO> getAll() throws SQLException {
        String sql = "select * from attendance;";
        ArrayList<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            String attendanceId = resultSet.getString("attendanceId");
            LocalTime departTime = LocalTime.parse(resultSet.getString("departTime"));
            LocalTime entryTime = resultSet.getTime("entryTime").toLocalTime();
            String eId = resultSet.getString("eId");
            AttendanceDTO attendanceDTO = new AttendanceDTO(attendanceId, departTime, entryTime, eId);
            attendanceDTOS.add(attendanceDTO);
        }
        return attendanceDTOS;
    }

    public static AttendanceDTO searchId(String id) throws SQLException {
        String sql = "select * from attendance where attendanceId = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        AttendanceDTO attendanceDTO = null;
        if (resultSet.next()){
            String attendanceId = resultSet.getString("attendanceId");
            LocalTime departTime = LocalTime.parse(resultSet.getString("departTime"));
            LocalTime entryTime = LocalTime.parse(resultSet.getString("entryTime"));
            String eId = resultSet.getString("eId");
            attendanceDTO = new AttendanceDTO(attendanceId,departTime,entryTime,eId);
        }
        return attendanceDTO;
    }
}
