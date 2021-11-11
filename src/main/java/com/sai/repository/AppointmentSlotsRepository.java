package com.sai.repository;

import com.sai.model.AppointmentSlots;
import com.sai.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlots, Long> {

    public AppointmentSlots findAppointmentSlotsByDoctorId(Long id);

}
