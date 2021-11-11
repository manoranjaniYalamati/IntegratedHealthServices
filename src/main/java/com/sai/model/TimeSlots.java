package com.sai.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class TimeSlots {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    @Column(name = "slot_id")
    private Long slotId;

    @ManyToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "slot_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AppointmentSlots appointmentSlots;

}
