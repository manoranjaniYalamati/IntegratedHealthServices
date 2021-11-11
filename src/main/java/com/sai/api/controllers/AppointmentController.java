package com.sai.api.controllers;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.requests.AppointmentRequest;
import com.sai.exception.AppointmentNotFoundException;
import com.sai.exception.DoctorNotfoundException;
import com.sai.mapper.AppointmentMapper;
import com.sai.mapper.AppointmentMapper;
import com.sai.model.Appointment;
import com.sai.model.Doctor;
import com.sai.service.AppointmentService;
import com.sai.service.AppointmentService;
import com.sai.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    private final AppointmentMapper appointmentMapper;


    @RequestMapping(method = RequestMethod.POST)
    public AppointmentDTO create(@RequestBody AppointmentRequest appointmentRequest) throws DoctorNotfoundException {
        Appointment appointment = appointmentMapper.map(appointmentRequest);
        Doctor doctor = doctorService.findDoctor(appointment.getDoctorId());
        appointment.setMeetLink(doctor.getMeetLink());
        appointment = appointmentService.create(appointment);
        return appointmentMapper.map(appointment);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AppointmentDTO update(@PathVariable("id") Long id,
                                 @RequestBody AppointmentRequest appointmentRequest) throws AppointmentNotFoundException {//what about rem fields if request accepted what abput mandatory fields if dcotor is taken ?
        log.debug("PUT /Appointment/" + id + "  with request:" + appointmentRequest);
        Appointment appointment = this.appointmentService.update(id, appointmentRequest);
        return appointmentMapper.map(appointment);
    }

    @GetMapping("/{id}")
    public AppointmentDTO findAppointment(@PathVariable("id") Long id) throws AppointmentNotFoundException {
        log.debug("GET /Appointment/" + id);
        return appointmentMapper.map(appointmentService.findAppointment(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AppointmentDTO> findAllAppointments() {
        log.debug("GET /Appointment/");
        return appointmentMapper.map(appointmentService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteAppointment(@PathVariable("id") Long id) throws AppointmentNotFoundException {
        log.debug("DELETE /Appointment/" + id);
        appointmentService.delete(id);
        return "Appointment with id " + id + " deleted";
    }
}
