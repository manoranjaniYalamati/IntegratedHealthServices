package com.sai.api.dto;


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
public class AppointmentDTO {//need to include user id and patient name and doctor name
    //need to add userid in doctor and patient too

    private Long doctorId;

    private Long patientId;

    private Date startTime;

    private Date endTime;

    private String SymptomsDescription;

    private String Prescription;

    private String meetLink;

}
