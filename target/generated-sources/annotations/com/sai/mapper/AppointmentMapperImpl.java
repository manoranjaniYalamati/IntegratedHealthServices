package com.sai.mapper;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.requests.AppointmentRequest;
import com.sai.model.Appointment;
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
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment map(AppointmentRequest appointmentRequest) {
        if ( appointmentRequest == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setDoctorId( appointmentRequest.getDoctorId() );
        appointment.setPatientId( appointmentRequest.getPatientId() );
        appointment.setStartTime( appointmentRequest.getStartTime() );
        appointment.setEndTime( appointmentRequest.getEndTime() );
        appointment.setSymptomsDescription( appointmentRequest.getSymptomsDescription() );

        return appointment;
    }

    @Override
    public AppointmentDTO map(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setDoctorId( appointment.getDoctorId() );
        appointmentDTO.setPatientId( appointment.getPatientId() );
        appointmentDTO.setStartTime( appointment.getStartTime() );
        appointmentDTO.setEndTime( appointment.getEndTime() );
        appointmentDTO.setSymptomsDescription( appointment.getSymptomsDescription() );
        appointmentDTO.setPrescription( appointment.getPrescription() );
        appointmentDTO.setMeetLink( appointment.getMeetLink() );

        return appointmentDTO;
    }

    @Override
    public void merge(AppointmentRequest appointmentRequest, Appointment appointment) {
        if ( appointmentRequest == null ) {
            return;
        }

        appointment.setDoctorId( appointmentRequest.getDoctorId() );
        appointment.setPatientId( appointmentRequest.getPatientId() );
        appointment.setStartTime( appointmentRequest.getStartTime() );
        appointment.setEndTime( appointmentRequest.getEndTime() );
        appointment.setSymptomsDescription( appointmentRequest.getSymptomsDescription() );
    }

    @Override
    public List<AppointmentDTO> map(List<Appointment> appointments) {
        if ( appointments == null ) {
            return null;
        }

        List<AppointmentDTO> list = new ArrayList<AppointmentDTO>( appointments.size() );
        for ( Appointment appointment : appointments ) {
            list.add( map( appointment ) );
        }

        return list;
    }
}
