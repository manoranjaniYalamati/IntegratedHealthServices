package com.sai.repository;

import com.sai.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT u FROM Patient u WHERE u.userId = ?1")
    Patient findByUserId(Long userId);
}
