package com.sai.mapper;


import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.AppointmentSlotsDTO;
import com.sai.api.requests.AppointmentRequest;
import com.sai.api.requests.AppointmentSlotsRequest;
import com.sai.model.Appointment;
import com.sai.model.AppointmentSlots;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentSlotsMapper {

    AppointmentSlots map(AppointmentSlotsRequest appointmentSlotsRequest);

    AppointmentSlotsDTO map(AppointmentSlots appointmentSlots);

    void merge(AppointmentSlotsRequest appointmentSlotsRequest, @MappingTarget AppointmentSlots appointmentSlots);

    List<AppointmentSlotsDTO> map(List<AppointmentSlots> appointmentSlots);

   // void merge(AppointmentSlots appointmentSlots1, @MappingTarget AppointmentSlots appointmentSlots);

}
