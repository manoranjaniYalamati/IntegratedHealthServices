package com.sai.repository;

import com.sai.model.Appointment;
import com.sai.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query("SELECT u FROM Appointment u WHERE u.userId = ?1")
    public List<Appointment> findAppointmentByUserId(Long userId);

    @Query("SELECT u FROM Appointment u WHERE u.doctorId = ?1")
    List<Appointment> findAppointmentsByDoctorId(Long id);
}
