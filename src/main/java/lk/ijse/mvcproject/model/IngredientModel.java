package lk.ijse.mvcproject.model;

import lk.ijse.mvcproject.dto.IngredientDTO;
import lk.ijse.mvcproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientModel {

    public static String generateIngredientId() throws SQLException {
        String sql = "select max(ingredientId) as lastIngredientId from ingredient;";
        ResultSet resultSet = CrudUtil.execute(sql);
        try {
            if (resultSet.next()){
                String lastIngredientId = resultSet.getString("lastIngredientId");
                if (lastIngredientId == null) {
                    return "IN001";
                }else {
                    int nextId = Integer.parseInt(lastIngredientId.substring(2)) + 1;
                    return "IN" + String.format("%03d",nextId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean saveIngredient(IngredientDTO ingredientDTO) throws SQLException {
        String sql ="insert into ingredient(ingredientId,description,price,weight) values(?,?,?,?);";
        return CrudUtil.execute(sql,ingredientDTO.getIngredientId(),ingredientDTO.getDescription(),ingredientDTO.getPrice(),ingredientDTO.getWeight());
    }
}
