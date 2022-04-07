package com.sai.service;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.AppointmentSlotsDTO;
import com.sai.exception.*;
import com.sai.mapper.AppointmentMapper;
import com.sai.mapper.AppointmentSlotsMapper;
import com.sai.model.*;
import com.sai.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.mail.javamail.JavaMailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {


    @Autowired
    private AppointmentSlotsService appointmentSlotsService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TimeSlotsService timeSlotsService;


    @Autowired
    private AppointmentSlotsMapper appointmentSlotsMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private HttpServletResponse res;

    public List<TimeSlots> findTimeSlotsOfDoctorId(Long doctorId) throws AppointmentSlotsNotFoundException, AppointmentSlotNotAvailableException {
        AppointmentSlots appointmentSlots = appointmentSlotsService.findAppointmentSlotsByDoctorId(doctorId);
        AppointmentSlotsDTO appointmentSlotsDTO = appointmentSlotsMapper.map(appointmentSlots);
        if (appointmentSlotsDTO.getTimeSlots() != null) {// why not appointment slots.check
            return appointmentSlots.getTimeSlots();
        } else {
            throw new AppointmentSlotNotAvailableException("AppointmentSlots not avaiable for this doctor.");
        }
    }

    public Appointment bookAppointment(Long slotId, String symptomsDescription, Long patientId, Long doctorId, Boolean isConsultationOnline) throws AppointmentSlotsNotFoundException, AppointmentSlotNotAvailableException, PatientNotfoundException, DoctorNotfoundException, TimeSlotNotFoundException {
        TimeSlots timeSlot = timeSlotsService.findTimeSlot(slotId);
        if (timeSlot != null) {

            Doctor doctor = doctorService.findDoctor(doctorId);

            Appointment appointment = new Appointment();
            appointment.setDoctorId(doctorId);
            appointment.setPatientId(patientId);
            appointment.setStartTime(timeSlot.getStartTime());
            appointment.setEndTime(timeSlot.getEndTime());
            if(isConsultationOnline){
            appointment.setMeetLink(doctor.getMeetLink());
            }
            appointment.setSymptomsDescription(symptomsDescription);
            appointmentService.create(appointment);
            timeSlotsService.delete(slotId);
            return appointment;

        } else {
            throw new TimeSlotNotFoundException("TimeSlot not found with id : " + slotId);
        }
    }

    public String sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("n160844@rguktn.ac.in", "manoranjanisrisai@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

        return "success";
    }




    public Appointment bookAppointment(Long slotId, String symptomsDescription, Long doctorId, Boolean isConsultationOnline) throws AppointmentSlotsNotFoundException, AppointmentSlotNotAvailableException, PatientNotfoundException, DoctorNotfoundException, TimeSlotNotFoundException {
        TimeSlots timeSlot = timeSlotsService.findTimeSlot(slotId);
        if (timeSlot != null) {

            Doctor doctor = doctorService.findDoctor(doctorId);

            Appointment appointment = new Appointment();
            appointment.setDoctorId(doctorId);
            appointment.setPatientId(patientService.getPatientIdByUserId(userService.getUserIdFromJWT(req, res)));
            appointment.setUserId(userService.getUserIdFromJWT(req, res));
            appointment.setStartTime(timeSlot.getStartTime());
            appointment.setEndTime(timeSlot.getEndTime());
            if(isConsultationOnline){
                appointment.setMeetLink(doctor.getMeetLink());
            }
            appointment.setSymptomsDescription(symptomsDescription);
            appointmentService.create(appointment);
            timeSlotsService.delete(slotId);
            return appointment;

        } else {
            throw new TimeSlotNotFoundException("TimeSlot not found with id : " + slotId);
        }
    }
}
