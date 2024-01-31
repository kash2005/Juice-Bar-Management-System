package lk.ijse.mvcproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTM {
    private String customerId;
    private String name;
    private String address;
    private String email;
    private String contact;
}
