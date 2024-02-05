package lk.ijse.mvcproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private String orderId;
    private String itemId;
    private int getQty;
    private Double amount;
}
