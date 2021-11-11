package com.sai.mapper;

import com.sai.api.dto.DoctorDTO;
import com.sai.api.dto.PatientDTO;
import com.sai.api.requests.DoctorRequest;
import com.sai.api.requests.PatientRequest;
import com.sai.api.requests.UserRequest;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient map(PatientRequest patientRequest);//Appointment map(AppointmentRequest appointmentRequest);

    PatientDTO map(Patient patient); //AppointmentDTO map(Appointment appointment);

    void merge(PatientRequest patientRequest, @MappingTarget Patient patient);
    //void merge(AppointentRequest appointmentRequest, @MappingTarget Appointment appointment);

    List<PatientDTO> map(List<Patient> patients);//List<AppointmentDTO> map(List<Appointment> appointments);

    Patient map(UserRequest userRequest);

}