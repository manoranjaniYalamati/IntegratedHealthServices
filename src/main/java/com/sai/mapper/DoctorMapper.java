package com.sai.mapper;

import com.sai.api.dto.DoctorDTO;
import com.sai.api.dto.DoctorDetailsDTO;
import com.sai.api.requests.DoctorRequest;
import com.sai.api.requests.UserRequest;
import com.sai.model.Doctor;
import com.sai.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor map(DoctorRequest doctorRequest);//Patient map(PatientRequest patientRequest);

    DoctorDTO map(Doctor doctor);//PatientDTO map(Patient patient);

    void merge(DoctorRequest doctorRequest, @MappingTarget Doctor doctor);
//void merge(PatientRequest patientRequest, @MappingTarget Patient patient);
    List<DoctorDTO> map(List<Doctor> doctors);//List<PatientDTO> map(List<Patient> patients);

    Doctor map(UserRequest userRequest);

  //  Doctor map(User user);
}
