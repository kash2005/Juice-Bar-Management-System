package lk.ijse.mvcproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientDTO {
    private String ingredientId;
    private String description;
    private double price;
    private String weight;
}
