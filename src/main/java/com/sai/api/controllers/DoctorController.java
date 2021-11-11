package com.sai.api.controllers;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.DoctorDTO;
import com.sai.api.dto.DoctorDetailsDTO;
import com.sai.api.requests.DoctorRequest;
import com.sai.exception.AppointmentNotFoundException;
import com.sai.exception.DoctorNotfoundException;
import com.sai.mapper.AppointmentMapper;
import com.sai.mapper.DoctorMapper;
import com.sai.model.Doctor;
import com.sai.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    private final DoctorMapper doctorMapper;

    private final AppointmentMapper appointmentMapper;

    @PreAuthorize("hasRole('DOCTOR')")
    @RequestMapping(method = RequestMethod.POST)
    public DoctorDTO create(@RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = doctorMapper.map(doctorRequest);
        return doctorMapper.map(doctorService.create(doctor));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DoctorDTO update(@PathVariable("id") Long id,
                             @RequestBody DoctorRequest doctorRequest) throws DoctorNotfoundException {//what about rem fields if request accepted what abput mandatory fields if dcotor is taken ?
        log.debug("PUT /doctor/"+id+"  with request:"+doctorRequest);
        Doctor doctor =this.doctorService.update(id, doctorRequest);
        return doctorMapper.map(doctor);
    }

    @GetMapping("/{id}")
    public DoctorDTO findDoctor(@PathVariable("id") Long id) throws DoctorNotfoundException{
        log.debug("GET /doctor/"+id);
        Doctor doctor = doctorService.findDoctor(id);
        return doctorMapper.map(doctor);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DoctorDTO> findAllDoctors(){
        log.debug("GET /doctor/");
        return doctorMapper.map(doctorService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteDoctor(@PathVariable("id") Long id) throws DoctorNotfoundException{
        log.debug("DELETE /doctor/"+id);
        doctorService.delete(id);
        return "Doctor with id " + id + " deleted";
    }

    @GetMapping("/speciality")
    public List<DoctorDetailsDTO> findDoctorBySpeciality(@RequestParam("speciality") String speciality) throws DoctorNotfoundException{
        log.debug("GET /doctor/"+speciality);
        List<DoctorDetailsDTO> doctorDetailsDTO = doctorService.findDoctorBySpeciality(speciality);
        return doctorDetailsDTO;
    }

    @RequestMapping(value = "/appointment/{id}", method = RequestMethod.PUT)
    public AppointmentDTO savePrescriptionOfAppointment(@PathVariable("id") Long appointmentId, @RequestParam("prescription") String prescription) throws AppointmentNotFoundException{
        log.debug("POST /doctor/appointment with id " + appointmentId);
        return appointmentMapper.map(doctorService.savePrescriptionOfAppointment(appointmentId, prescription));
    }

}
