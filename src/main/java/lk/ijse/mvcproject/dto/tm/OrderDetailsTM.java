package lk.ijse.mvcproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsTM {
    private String itemCode;
    private String itemDescription;
    private Double unitPrice;
    private String qtyOnHand;
    private String getQty;
}
