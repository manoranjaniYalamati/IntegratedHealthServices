package com.sai.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppointmentSlots {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @Column(name = "doctor_id")
    private Long doctorId;

    @OneToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Doctor doctor;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "doctorId", referencedColumnName = "id")
//    private Doctor doctor;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;

//    @OneToMany
//    private List<TimeSlots> timeSlots;

    @OneToMany(mappedBy = "appointmentSlots", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TimeSlots> timeSlots;
}
