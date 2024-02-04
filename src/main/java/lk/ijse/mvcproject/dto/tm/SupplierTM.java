package lk.ijse.mvcproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierTM {
    private String supplierId;
    private String name;
    private String contact;
    private String company;
}
