package com.sai.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private String name;

    private String speciality;

    private int experience;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private  Gender gender;

    private String contactEmail;

    private String meetLink;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Appointment> appointments;


    @OneToOne(mappedBy = "doctor")
    private AppointmentSlots appointmentSlots;

}
