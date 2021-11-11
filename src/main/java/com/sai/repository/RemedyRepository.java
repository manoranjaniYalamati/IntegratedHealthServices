package com.sai.repository;

import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.model.Remedy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface RemedyRepository extends JpaRepository<Remedy, Long> {

    public Remedy findRemedyByDiseaseName(String disease);
}
