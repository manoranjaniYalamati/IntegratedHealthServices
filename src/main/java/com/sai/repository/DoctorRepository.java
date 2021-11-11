package com.sai.repository;


import com.sai.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    public Doctor findDoctorById(long id);

    @Query("SELECT u FROM Doctor u WHERE u.speciality = ?1")
    public List<Doctor> findDoctorsBySpeciality(String speciality);
}
