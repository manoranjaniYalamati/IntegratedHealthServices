package com.sai.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class AppointmentRequest {

    private Long doctorId;

   private Long patientId;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    private String symptomsDescription;//small ss
}
