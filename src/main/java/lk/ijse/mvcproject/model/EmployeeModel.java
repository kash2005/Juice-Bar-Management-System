package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.EmployeeDTO;
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

    public static String searchId(String eId) throws SQLException {
        String sql = "select jobRoll from employee where eId = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, eId);
        String jobRoll =null;
        if (resultSet.next()){
            jobRoll = resultSet.getString("jobRoll");
        }
        return jobRoll;
    }

    public static String generateId() throws SQLException {
        String sql = "select max(eId) as lastId from employee;";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "E001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "E" + String.format("%03d",nextId);
            }
        }
        return null;
    }

    public static boolean save(EmployeeDTO employeeDTO) throws SQLException {
        String sql = "insert into employee(eId,name,address,email,contact,jobRoll,onePerHour) values(?,?,?,?,?,?,?);";;
        return CrudUtil.execute(sql,employeeDTO.getEId(),employeeDTO.getName(),employeeDTO.getAddress(),
                employeeDTO.getEmail(),employeeDTO.getContact(),employeeDTO.getJobRoll(),employeeDTO.getOnePerHour());
    }

    public static EmployeeDTO searchEmployeeId(String id) throws SQLException {
        String sql = "select * from employee where eId = ?;";
        EmployeeDTO employeeDTO = null;
        ResultSet resultSet = CrudUtil.execute(sql, id);
        if (resultSet.next()){
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String contact = resultSet.getString("contact");
            String jobRoll = resultSet.getString("jobRoll");
            String onePerHour = resultSet.getString("onePerHour");
            String eId = resultSet.getString("eId");
            employeeDTO = new EmployeeDTO(eId,name,address,email,contact,jobRoll,onePerHour);
        }
        return employeeDTO;
    }

    public static boolean update(EmployeeDTO employeeDTO) throws SQLException {
        String  sql = "update employee set name = ?,address = ?,email = ?,contact = ?,jobRoll = ?,onePerHour = ? where eId = ?;";
        return CrudUtil.execute(sql,employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getEmail(),
                employeeDTO.getContact(),employeeDTO.getJobRoll(),employeeDTO.getOnePerHour(),employeeDTO.getEId());
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "delete from employee where eId = ?;";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<EmployeeDTO> getAll() throws SQLException {
        String sql = "select * from employee;";
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        EmployeeDTO employeeDTO = null;
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            employeeDTO = new EmployeeDTO(
                    resultSet.getString("eId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("contact"),
                    resultSet.getString("jobRoll"),
                    resultSet.getString("onePerHour")
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
