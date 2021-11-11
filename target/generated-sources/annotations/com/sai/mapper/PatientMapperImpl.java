package com.sai.mapper;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.PatientDTO;
import com.sai.api.requests.PatientRequest;
import com.sai.api.requests.UserRequest;
import com.sai.model.Appointment;
import com.sai.model.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-07T14:54:14+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public Patient map(PatientRequest patientRequest) {
        if ( patientRequest == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setName( patientRequest.getName() );
        patient.setGender( patientRequest.getGender() );
        patient.setContactEmail( patientRequest.getContactEmail() );

        return patient;
    }

    @Override
    public PatientDTO map(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setName( patient.getName() );
        patientDTO.setGender( patient.getGender() );
        patientDTO.setAppointments( appointmentListToAppointmentDTOList( patient.getAppointments() ) );

        return patientDTO;
    }

    @Override
    public void merge(PatientRequest patientRequest, Patient patient) {
        if ( patientRequest == null ) {
            return;
        }

        patient.setName( patientRequest.getName() );
        patient.setGender( patientRequest.getGender() );
        patient.setContactEmail( patientRequest.getContactEmail() );
    }

    @Override
    public List<PatientDTO> map(List<Patient> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientDTO> list = new ArrayList<PatientDTO>( patients.size() );
        for ( Patient patient : patients ) {
            list.add( map( patient ) );
        }

        return list;
    }

    @Override
    public Patient map(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setName( userRequest.getName() );
        patient.setGender( userRequest.getGender() );
        patient.setContactEmail( userRequest.getContactEmail() );

        return patient;
    }

    protected AppointmentDTO appointmentToAppointmentDTO(Appointment appointment) {
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

    protected List<AppointmentDTO> appointmentListToAppointmentDTOList(List<Appointment> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDTO> list1 = new ArrayList<AppointmentDTO>( list.size() );
        for ( Appointment appointment : list ) {
            list1.add( appointmentToAppointmentDTO( appointment ) );
        }

        return list1;
    }
}
