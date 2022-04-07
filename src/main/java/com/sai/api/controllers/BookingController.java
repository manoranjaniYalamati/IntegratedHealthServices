package com.sai.api.controllers;

import com.sai.api.dto.AppointmentDTO;
import com.sai.api.dto.TimeSlotsDTO;
import com.sai.exception.*;
import com.sai.mapper.AppointmentMapper;
import com.sai.mapper.TimeSlotsMapper;
import com.sai.model.TimeSlots;
import com.sai.service.AppointmentService;
import com.sai.service.AppointmentSlotsService;
import com.sai.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//get doctor by speciality
//get slots of that doctor id
//book appointent of that slotid

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/book")
public class BookingController {

    @Autowired
    private AppointmentSlotsService appointmentSlotsService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private BookingService bookingService;

    private final AppointmentMapper appointmentMapper;

    private final TimeSlotsMapper timeSlotsMapper;

    @GetMapping("/slots/{doctorId}")
    public List<TimeSlotsDTO> findTimeSlotsOfDoctorId(@PathVariable("doctorId") Long doctorId) throws AppointmentSlotsNotFoundException, AppointmentSlotNotAvailableException{
          return  timeSlotsMapper.map(bookingService.findTimeSlotsOfDoctorId(doctorId));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public AppointmentDTO create(@PathVariable("id") Long slotId, @RequestParam("symptomsDescription") String symptomsDescription,@RequestParam("patientId") Long patientId, @RequestParam("doctorId") Long doctorId, @RequestParam("isConsultationOnline") Boolean isConsultationOnline) throws AppointmentSlotsNotFoundException, AppointmentSlotNotAvailableException, PatientNotfoundException, DoctorNotfoundException, TimeSlotNotFoundException {
        return appointmentMapper.map(bookingService.bookAppointment(slotId, symptomsDescription, patientId, doctorId, isConsultationOnline));
    }

    @RequestMapping(value = "/{id}/{doctorId}",method = RequestMethod.POST)
    public AppointmentDTO Book(@PathVariable("id") Long slotId,@PathVariable("doctorId") Long doctorId,  @RequestParam("symptomsDescription") String symptomsDescription, @RequestParam("isConsultationOnline") Boolean isConsultationOnline) throws AppointmentSlotsNotFoundException, AppointmentSlotNotAvailableException, PatientNotfoundException, DoctorNotfoundException, TimeSlotNotFoundException {
        return appointmentMapper.map(bookingService.bookAppointment(slotId, symptomsDescription, doctorId, isConsultationOnline));
    }

    @RequestMapping(value = "/gmail", method = RequestMethod.POST)
    public String sendEmail(){

        return bookingService.sendEmail();

    }
}
