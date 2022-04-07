package com.sai.service;

import com.sai.api.requests.PatientRequest;
import com.sai.exception.PatientNotfoundException;
import com.sai.mapper.PatientMapper;
import com.sai.model.Patient;
import com.sai.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    public Patient create(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient update(Long id, PatientRequest patientRequest) throws PatientNotfoundException {
        Patient patient = findPatient(id);//why this.find
        patientMapper.merge(patientRequest, patient);
        return patientRepository.save(patient);
    }

    public Patient findPatient(Long id) throws PatientNotfoundException {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotfoundException("Patient not found with id : " + id));
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public void delete(Long id) throws PatientNotfoundException{
        Patient patient = findPatient(id);
        if(patient != null){//fetch empty util files from company db
            patientRepository.delete(patient);
        }
        else{
            throw new PatientNotfoundException("Patient Not Found with id " + id);
        }
    }

    public Long getPatientIdByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId);
        return patient.getId();
    }
}
