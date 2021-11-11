package com.sai.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class AppointmentSlotsRequest {

    private Long doctorId;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;
}
