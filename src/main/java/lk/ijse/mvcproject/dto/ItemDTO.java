package lk.ijse.mvcproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {
    private String itemId;
    private String description;
    private int qty;
    private double price;
}
