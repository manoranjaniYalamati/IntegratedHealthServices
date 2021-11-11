package com.sai.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSlotsDTO {

    private Long Id;

    private Long doctorId;

    private Date startTime;

    private Date endTime;

    private List<TimeSlotsDTO> timeSlots;
}
