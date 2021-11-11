package com.sai.mapper;

import com.sai.api.dto.TimeSlotsDTO;
import com.sai.api.requests.TimeSlotsRequest;
import com.sai.model.TimeSlots;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSlotsMapper {

    TimeSlots map(TimeSlotsRequest timeSlotsRequest);

    TimeSlotsDTO map(TimeSlots timeSlots);

    void merge(TimeSlotsRequest timeSlotsRequest, @MappingTarget TimeSlots timeSlots);

    List<TimeSlotsDTO> map(List<TimeSlots> timeSlots);
}
