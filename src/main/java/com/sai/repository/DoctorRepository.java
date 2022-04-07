package com.sai.repository;


import com.sai.model.Doctor;
import com.sai.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    //public Doctor findDoctorById(long id);

    @Query("SELECT u FROM Doctor u WHERE u.speciality = ?1")
    public List<Doctor> findDoctorsBySpeciality(String speciality);

    @Query("SELECT u FROM Doctor u WHERE u.experience = ?1")
    List<Doctor> findDoctorsByExperience(int experience);

    @Query("SELECT u FROM Doctor u WHERE u.userId = ?1")
    Doctor findDoctorByUserId(Long userId);
}
