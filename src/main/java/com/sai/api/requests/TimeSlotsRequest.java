package com.sai.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TimeSlotsRequest {
    private Long slotId;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;

}
