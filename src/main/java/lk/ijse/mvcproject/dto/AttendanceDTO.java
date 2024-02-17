package lk.ijse.mvcproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDTO {
    private String attendanceId;
    private LocalTime departTime;
    private LocalTime entryTime;
    private String eId;
}