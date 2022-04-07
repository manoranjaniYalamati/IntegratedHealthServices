package com.sai.mapper;

import com.sai.api.dto.AppointmentSlotsDTO;
import com.sai.api.dto.TimeSlotsDTO;
import com.sai.api.requests.AppointmentSlotsRequest;
import com.sai.model.AppointmentSlots;
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
public class AppointmentSlotsMapperImpl implements AppointmentSlotsMapper {

    @Override
    public AppointmentSlots map(AppointmentSlotsRequest appointmentSlotsRequest) {
        if ( appointmentSlotsRequest == null ) {
            return null;
        }

        AppointmentSlots appointmentSlots = new AppointmentSlots();

        appointmentSlots.setDoctorId( appointmentSlotsRequest.getDoctorId() );
        appointmentSlots.setStartTime( appointmentSlotsRequest.getStartTime() );
        appointmentSlots.setEndTime( appointmentSlotsRequest.getEndTime() );

        return appointmentSlots;
    }

    @Override
    public AppointmentSlotsDTO map(AppointmentSlots appointmentSlots) {
        if ( appointmentSlots == null ) {
            return null;
        }

        AppointmentSlotsDTO appointmentSlotsDTO = new AppointmentSlotsDTO();

        appointmentSlotsDTO.setId( appointmentSlots.getId() );
        appointmentSlotsDTO.setDoctorId( appointmentSlots.getDoctorId() );
        appointmentSlotsDTO.setStartTime( appointmentSlots.getStartTime() );
        appointmentSlotsDTO.setEndTime( appointmentSlots.getEndTime() );
        appointmentSlotsDTO.setTimeSlots( timeSlotsListToTimeSlotsDTOList( appointmentSlots.getTimeSlots() ) );

        return appointmentSlotsDTO;
    }

    @Override
    public void merge(AppointmentSlotsRequest appointmentSlotsRequest, AppointmentSlots appointmentSlots) {
        if ( appointmentSlotsRequest == null ) {
            return;
        }

        appointmentSlots.setDoctorId( appointmentSlotsRequest.getDoctorId() );
        appointmentSlots.setStartTime( appointmentSlotsRequest.getStartTime() );
        appointmentSlots.setEndTime( appointmentSlotsRequest.getEndTime() );
    }

    @Override
    public List<AppointmentSlotsDTO> map(List<AppointmentSlots> appointmentSlots) {
        if ( appointmentSlots == null ) {
            return null;
        }

        List<AppointmentSlotsDTO> list = new ArrayList<AppointmentSlotsDTO>( appointmentSlots.size() );
        for ( AppointmentSlots appointmentSlots1 : appointmentSlots ) {
            list.add( map( appointmentSlots1 ) );
        }

        return list;
    }

    protected TimeSlotsDTO timeSlotsToTimeSlotsDTO(TimeSlots timeSlots) {
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

    protected List<TimeSlotsDTO> timeSlotsListToTimeSlotsDTOList(List<TimeSlots> list) {
        if ( list == null ) {
            return null;
        }

        List<TimeSlotsDTO> list1 = new ArrayList<TimeSlotsDTO>( list.size() );
        for ( TimeSlots timeSlots : list ) {
            list1.add( timeSlotsToTimeSlotsDTO( timeSlots ) );
        }

        return list1;
    }
}
