package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.SupplierDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

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

//    public static boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
//        String sql = "update supplier set name = ?, contact = ?, company = ? where supplierId = ?;";
//        return CrudUtil.execute(sql,supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getCompany(),supplierDTO.getSupplierId());
//    }
}
