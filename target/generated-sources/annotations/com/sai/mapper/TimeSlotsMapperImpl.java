package com.sai.mapper;

import com.sai.api.dto.TimeSlotsDTO;
import com.sai.api.requests.TimeSlotsRequest;
import com.sai.model.TimeSlots;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-07T09:45:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class TimeSlotsMapperImpl implements TimeSlotsMapper {

    @Override
    public TimeSlots map(TimeSlotsRequest timeSlotsRequest) {
        if ( timeSlotsRequest == null ) {
            return null;
        }

        TimeSlots timeSlots = new TimeSlots();

        timeSlots.setStartTime( timeSlotsRequest.getStartTime() );
        timeSlots.setEndTime( timeSlotsRequest.getEndTime() );
        timeSlots.setSlotId( timeSlotsRequest.getSlotId() );

        return timeSlots;
    }

    @Override
    public TimeSlotsDTO map(TimeSlots timeSlots) {
        if ( timeSlots == null ) {
            return null;
        }

        TimeSlotsDTO timeSlotsDTO = new TimeSlotsDTO();

        timeSlotsDTO.setId( timeSlots.getId() );
        timeSlotsDTO.setStartTime( timeSlots.getStartTime() );
        timeSlotsDTO.setEndTime( timeSlots.getEndTime() );
        timeSlotsDTO.setSlotId( timeSlots.getSlotId() );
        timeSlotsDTO.setDoctorSlots( timeSlots.getDoctorSlots() );

        return timeSlotsDTO;
    }

    @Override
    public void merge(TimeSlotsRequest timeSlotsRequest, TimeSlots timeSlots) {
        if ( timeSlotsRequest == null ) {
            return;
        }

        timeSlots.setStartTime( timeSlotsRequest.getStartTime() );
        timeSlots.setEndTime( timeSlotsRequest.getEndTime() );
        timeSlots.setSlotId( timeSlotsRequest.getSlotId() );
    }

    @Override
    public List<TimeSlotsDTO> map(List<TimeSlots> timeSlots) {
        if ( timeSlots == null ) {
            return null;
        }

        List<TimeSlotsDTO> list = new ArrayList<TimeSlotsDTO>( timeSlots.size() );
        for ( TimeSlots timeSlots1 : timeSlots ) {
            list.add( map( timeSlots1 ) );
        }

        return list;
    }
}
