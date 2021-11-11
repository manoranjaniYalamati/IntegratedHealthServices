package com.sai.api.controllers;

import com.sai.api.dto.PatientDTO;
import com.sai.api.dto.PatientDTO;
import com.sai.api.requests.PatientRequest;
import com.sai.api.requests.PatientRequest;
import com.sai.exception.PatientNotfoundException;
import com.sai.mapper.PatientMapper;
import com.sai.model.Patient;
import com.sai.model.Patient;
import com.sai.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private final PatientMapper patientMapper;

    @RequestMapping(method = RequestMethod.POST)
    public PatientDTO create(@RequestBody PatientRequest patientRequest) {
        Patient patient = patientMapper.map(patientRequest);
        return patientMapper.map(patientService.create(patient));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PatientDTO update(@PathVariable("id") Long id,
                            @RequestBody PatientRequest patientRequest) throws PatientNotfoundException {//what about rem fields if request accepted what abput mandatory fields if dcotor is taken ?
        log.debug("PUT /Patient/"+id+"  with request:"+patientRequest);
        Patient patient =this.patientService.update(id, patientRequest);
        return patientMapper.map(patient);
    }

    @GetMapping("/{id}")
    public PatientDTO findPatient(@PathVariable("id") Long id) throws PatientNotfoundException{
        log.debug("GET /Patient/"+id);
        return patientMapper.map(patientService.findPatient(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PatientDTO> findAllPatients(){
        log.debug("GET /Patient/");
        return patientMapper.map(patientService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePatient(@PathVariable("id") Long id) throws PatientNotfoundException{
        log.debug("DELETE /Patient/"+id);
        patientService.delete(id);
        return "Patient with id " + id + " deleted";
    }

}
