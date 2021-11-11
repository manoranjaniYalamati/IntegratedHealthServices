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
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private  Gender gender;

    private int age;

    private String contactEmail;//not null

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Appointment> appointments;

//    @OneToMany(mappedBy = "patient")
//    private Set<Appointment> appointments;
}
