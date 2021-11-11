package com.sai.api.requests;

import com.sai.model.Gender;
import lombok.Data;

@Data
public class PatientRequest {

    private String name;

    private Gender gender;

    private String contactEmail;
}
