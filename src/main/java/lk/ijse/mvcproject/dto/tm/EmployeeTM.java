package lk.ijse.mvcproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTM {
    private String eId;
    private String name;
    private String address;
    private String email;
    private String contact;
    private String jobRoll;
    private String onePerHour;
}
