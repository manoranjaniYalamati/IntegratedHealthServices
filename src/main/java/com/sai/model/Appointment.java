package com.sai.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @Column(name = "doctor_id")
    private Long doctorId;

   @Column(name = "patient_id")
    private Long patientId;

   @Column(name= "user_id")
   private Long userId;

//    @ManyToOne
//    @JoinColumn(name="doctor_id", nullable=false)
//    private Doctor doctor;
//
//
//    @ManyToOne
//    @JoinColumn(name="patient_id", nullable=false)
//    private Patient patient;

    @ManyToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Doctor doctor;

    @ManyToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Patient patient;

    @ManyToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    private String SymptomsDescription;

    private String Prescription;

    private String meetLink;



}
