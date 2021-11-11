package com.sai.mapper;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.PatientDTO;
import com.sai.api.requests.AppointmentRequest;
import com.sai.api.requests.PatientRequest;
import com.sai.model.Appointment;
import com.sai.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment map(AppointmentRequest appointmentRequest);

    AppointmentDTO map(Appointment appointment);

    void merge(AppointmentRequest appointmentRequest, @MappingTarget Appointment appointment);

    List<AppointmentDTO> map(List<Appointment> appointments);


}
