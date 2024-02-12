package lk.ijse.mvcproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private String deliveryId;
    private String distance;
    private double price;
    private String orderId;
}
