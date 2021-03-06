package com.sai.api.controllers;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.AppointmentReactDTO;
import com.sai.api.requests.AppointmentRequest;
import com.sai.exception.AppointmentNotFoundException;
import com.sai.exception.DoctorNotfoundException;
import com.sai.exception.PatientNotfoundException;
import com.sai.mapper.AppointmentMapper;
import com.sai.mapper.AppointmentMapper;
import com.sai.model.Appointment;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.model.User;
import com.sai.repository.UserDao;
import com.sai.service.AppointmentService;
import com.sai.service.AppointmentService;
import com.sai.service.DoctorService;
import com.sai.service.PatientService;
import com.sai.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private  HttpServletRequest req;

    @Autowired
    private HttpServletResponse res;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PatientService patientService;

    private final AppointmentMapper appointmentMapper;


    @RequestMapping(method = RequestMethod.POST)
    public AppointmentDTO create(@RequestBody AppointmentRequest appointmentRequest) throws DoctorNotfoundException {
        Appointment appointment = appointmentMapper.map(appointmentRequest);
        Doctor doctor = doctorService.findDoctor(appointment.getDoctorId());
        appointment.setMeetLink(doctor.getMeetLink());
        appointment = appointmentService.create(appointment);
        return appointmentMapper.map(appointment);
    }

    @RequestMapping(value = "/username", method= RequestMethod.GET)
    public Long getUserName(){
        String username = appointmentService.getUserName(req, res);
        User user = userDao.findByUsername(username);
        return user.getId();
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
    public List<AppointmentReactDTO> findAllAppointments() throws AppointmentNotFoundException,DoctorNotfoundException, PatientNotfoundException {
        log.debug("GET /Appointment/");
        String username = appointmentService.getUserName(req, res);
        User user = userDao.findByUsername(username);
        List<AppointmentReactDTO> appointmentReactDTOS = new ArrayList<>();
        List<Appointment> appointments = appointmentService.findAppointments(user.getId());
        for (Appointment appointment : appointments){
            Doctor doctor = doctorService.findDoctor(appointment.getDoctorId());
            Patient patient = patientService.findPatient(appointment.getPatientId());
            String doctorName = doctor.getName();
            String patientName = patient.getName();
            SimpleDateFormat dt = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");
            String startTime = dt.format(appointment.getStartTime());
            String endTime = dt.format(appointment.getEndTime());
            AppointmentReactDTO appointmentReactDTO = new AppointmentReactDTO(appointment.getId(),doctor.getId(),patient.getId(),patient.getName(),doctorName,startTime,endTime,appointment.getSymptomsDescription(),appointment.getPrescription(),appointment.getMeetLink());
            appointmentReactDTOS.add(appointmentReactDTO);
        }
        return  appointmentReactDTOS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteAppointment(@PathVariable("id") Long id) throws AppointmentNotFoundException {
        log.debug("DELETE /Appointment/" + id);
        appointmentService.delete(id);
        return "Appointment with id " + id + " deleted";
    }
}
