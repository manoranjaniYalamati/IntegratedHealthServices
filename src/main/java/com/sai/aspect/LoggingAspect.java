package com.sai.aspect;

import com.sai.api.dto.UserDto;
import com.sai.exception.DoctorNotfoundException;
import com.sai.exception.PatientNotfoundException;
import com.sai.model.Appointment;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.service.DoctorService;
import com.sai.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.sai.api.dto.AppointmentDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JavaMailSender javaMailSender;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(pointcut = "execution(public com.sai.model.Appointment com.sai.service.BookingService.bookAppointment(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) throws PatientNotfoundException, DoctorNotfoundException {
        Appointment appointment = (Appointment) result;
        Patient patient = patientService.findPatient(appointment.getPatientId());
        Doctor doctor = doctorService.findDoctor(appointment.getDoctorId());
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(patient.getContactEmail());
        SimpleDateFormat dt = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");

        if(appointment.getMeetLink() == null){
            String messageText = "Dear " + patient.getName() + ",\n Your appointment to consult doctor " + doctor.getName() + " has been scheduled on  " + dt.format(appointment.getStartTime()) + " to " + dt.format(appointment.getEndTime()) + ".\nYou can contact the doctor at hospital";
            msg.setText(messageText);
        }
        else{
            String messageText = "Dear " + patient.getName() + ",\n Your appointment to consult doctor " + doctor.getName() + " has been scheduled on  " + dt.format(appointment.getStartTime()) + " to " + dt.format(appointment.getEndTime()) + ".\nYour meet link for consultation is " + appointment.getMeetLink();
            msg.setText(messageText);
        }
        msg.setSubject("Integrated Health Services");
        javaMailSender.send(msg);
    }

    @AfterReturning(pointcut = "execution(public com.sai.api.dto.UserDto com.sai.services.UserServiceImpl.save(..))", returning = "result")
    public void sendEmailAfterReturning(JoinPoint joinPoint, Object result) {

        UserDto userDto = (UserDto) result;

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userDto.getContactEmail());
        String messageText = "Hello " + userDto.getName() + ",\n    Welcome and thank you for signing up for IHS.Your Registration was successfull.\nBest Regards,\nIHS";
        msg.setSubject("Integrated Health Services");
        msg.setText(messageText);
        javaMailSender.send(msg);
    }

}
