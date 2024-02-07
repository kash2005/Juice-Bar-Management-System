package lk.ijse.mvcproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddToCartTM {
    private String itemCode;
    private String itemDescription;
    private Double unitPrice;
    private int qtyOnHand;
    private int getQty;
}
