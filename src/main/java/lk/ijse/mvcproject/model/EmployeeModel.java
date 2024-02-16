package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static ArrayList<String> getCmbEmployeeId() throws SQLException {
        String sql = "select eId from employee;";
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            String eId = resultSet.getString("eId");
            arrayList.add(eId);
        }
        return arrayList;
    }

//    public static String searchId(String eId) {
//        String sql = "select jobRoll from employee where eId = ?;";
//    }
}
