package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.SupplierDTO;
import lk.ijse.mvcproject.dto.tm.SupplierTM;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static String generateId() throws SQLException {
        String sql = "select max(supplierId) as lastSupplierId from supplier;";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String lastSupplierId = resultSet.getString("lastSupplierId");
            if (lastSupplierId == null){
                return "S001";
            }else {
                int nextId = Integer.parseInt(lastSupplierId.substring(1)) + 1;
                return "S" + String.format("%03d",nextId);
            }
        }
        return null;
    }

    public static boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        String sql = "insert into supplier(supplierId,name,contact,company) values(?,?,?,?)";
        return CrudUtil.execute(sql,supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getCompany());
    }

    public static boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        String sql = "update supplier set name = ?, contact = ?, company = ? where supplierId = ?;";
        return CrudUtil.execute(sql,supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getCompany(),supplierDTO.getSupplierId());
    }

    public static boolean deleteSupplier(String id) throws SQLException {
        String sql = "delete from supplier where supplierId = ?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<SupplierDTO> getAll() throws SQLException {
        String sql = "select * from supplier";
        ResultSet resultSet = CrudUtil.execute(sql);
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        while (resultSet.next()){
            SupplierDTO supplierDTO = new SupplierDTO(
                    resultSet.getString("supplierId"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getString("company")
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    public static SupplierDTO searchSupplier(String id) throws SQLException {
        String sql = "select * from supplier where supplierId = ?;";
        ResultSet resultSet = CrudUtil.execute(sql,id);
        SupplierDTO supplierDTO = null;
        while (resultSet.next()){
            String supplierId = resultSet.getString("supplierId");
            String name = resultSet.getString("name");
            String contact = resultSet.getString("contact");
            String company = resultSet.getString("company");
            supplierDTO = new SupplierDTO(supplierId,name,contact,company);
        }
        return supplierDTO;
    }
}
