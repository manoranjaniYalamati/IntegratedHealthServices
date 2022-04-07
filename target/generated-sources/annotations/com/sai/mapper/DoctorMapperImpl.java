package com.sai.mapper;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.AppointmentSlotsDTO;
import com.sai.api.dto.DoctorDTO;
import com.sai.api.dto.TimeSlotsDTO;
import com.sai.api.requests.DoctorRequest;
import com.sai.api.requests.UserRequest;
import com.sai.model.Appointment;
import com.sai.model.AppointmentSlots;
import com.sai.model.Doctor;
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
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public Doctor map(DoctorRequest doctorRequest) {
        if ( doctorRequest == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setName( doctorRequest.getName() );
        doctor.setSpeciality( doctorRequest.getSpeciality() );
        if ( doctorRequest.getExperience() != null ) {
            doctor.setExperience( Integer.parseInt( doctorRequest.getExperience() ) );
        }
        doctor.setGender( doctorRequest.getGender() );
        doctor.setContactEmail( doctorRequest.getContactEmail() );
        doctor.setMeetLink( doctorRequest.getMeetLink() );

        return doctor;
    }

    @Override
    public DoctorDTO map(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setId( doctor.getId() );
        doctorDTO.setName( doctor.getName() );
        doctorDTO.setSpeciality( doctor.getSpeciality() );
        doctorDTO.setExperience( doctor.getExperience() );
        doctorDTO.setGender( doctor.getGender() );
        doctorDTO.setAppointments( appointmentListToAppointmentDTOList( doctor.getAppointments() ) );
        doctorDTO.setAppointmentSlots( appointmentSlotsToAppointmentSlotsDTO( doctor.getAppointmentSlots() ) );

        return doctorDTO;
    }

    @Override
    public void merge(DoctorRequest doctorRequest, Doctor doctor) {
        if ( doctorRequest == null ) {
            return;
        }

        doctor.setName( doctorRequest.getName() );
        doctor.setSpeciality( doctorRequest.getSpeciality() );
        if ( doctorRequest.getExperience() != null ) {
            doctor.setExperience( Integer.parseInt( doctorRequest.getExperience() ) );
        }
        doctor.setGender( doctorRequest.getGender() );
        doctor.setContactEmail( doctorRequest.getContactEmail() );
        doctor.setMeetLink( doctorRequest.getMeetLink() );
    }

    @Override
    public List<DoctorDTO> map(List<Doctor> doctors) {
        if ( doctors == null ) {
            return null;
        }

        List<DoctorDTO> list = new ArrayList<DoctorDTO>( doctors.size() );
        for ( Doctor doctor : doctors ) {
            list.add( map( doctor ) );
        }

        return list;
    }

    @Override
    public Doctor map(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setName( userRequest.getName() );
        doctor.setSpeciality( userRequest.getSpeciality() );
        if ( userRequest.getExperience() != null ) {
            doctor.setExperience( Integer.parseInt( userRequest.getExperience() ) );
        }
        doctor.setGender( userRequest.getGender() );
        doctor.setContactEmail( userRequest.getContactEmail() );
        doctor.setMeetLink( userRequest.getMeetLink() );

        return doctor;
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

    protected AppointmentSlotsDTO appointmentSlotsToAppointmentSlotsDTO(AppointmentSlots appointmentSlots) {
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
}
