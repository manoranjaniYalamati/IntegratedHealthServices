package com.sai.api.dto;

import com.sai.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailsDTO {
    private Long id;

    private String name;

    private String speciality;

    private int experience;

    private Gender gender;

}
