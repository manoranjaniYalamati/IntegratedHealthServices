package com.sai.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

    @Column(name= "user_id")
    private Long userId;

    private int age;

    private String contactEmail;//not null

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Appointment> appointments;


    @OneToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

//    @OneToMany(mappedBy = "patient")
//    private Set<Appointment> appointments;
}
