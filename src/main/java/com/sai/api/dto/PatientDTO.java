package com.sai.api.dto;

import com.sai.model.Appointment;

import com.sai.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private String name;

    private Gender gender;

    private List<AppointmentDTO> appointments;
}
