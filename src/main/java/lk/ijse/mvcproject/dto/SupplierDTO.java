package lk.ijse.mvcproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supplierId;
    private String name;
    private String contact;
    private String company;
}
