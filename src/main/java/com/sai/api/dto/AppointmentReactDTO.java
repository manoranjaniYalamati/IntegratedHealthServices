package com.sai.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
        import com.sai.model.Doctor;
        import com.sai.model.Patient;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.sql.Time;
        import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentReactDTO {//need to include user id and patient name and doctor name
    //need to add userid in doctor and patient too
    private Long id;

    private Long doctorId;

    private Long patientId;

    private String patientName;

    private String doctorName;

    private String startTime;

    private String endTime;

    private String SymptomsDescription;

    private String Prescription;

    private String meetLink;

}
