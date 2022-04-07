package com.sai.api.requests;

import com.sai.model.Gender;
import lombok.Data;

@Data
public class DoctorRequest {

    private String name;

    private Gender gender;

    private String contactEmail;

    private String speciality;

    private String meetLink;

    private String experience;
}
