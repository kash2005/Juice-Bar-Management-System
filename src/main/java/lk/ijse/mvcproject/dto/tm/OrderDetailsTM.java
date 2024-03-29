package lk.ijse.mvcproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsTM {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private String itemId;
    private String description;
    private int getQty;
    private double amount;
    private String deliveryStatus;
}
