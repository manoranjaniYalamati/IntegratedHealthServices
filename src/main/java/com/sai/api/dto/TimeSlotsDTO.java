package com.sai.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotsDTO {

    private Long id;

    private Date startTime;

    private Date endTime;

    private Long slotId;
}
