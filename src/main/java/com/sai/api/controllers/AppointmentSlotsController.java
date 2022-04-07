package com.sai.api.controllers;

import com.sai.api.dto.AppointmentSlotsDTO;
import com.sai.api.requests.AppointmentSlotsRequest;
import com.sai.exception.AppointmentSlotsNotFoundException;
import com.sai.mapper.AppointmentSlotsMapper;
import com.sai.model.AppointmentSlots;
import com.sai.model.TimeSlots;
import com.sai.service.AppointmentSlotsService;
import com.sai.service.TimeSlotsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/slot")
public class AppointmentSlotsController {

    @Autowired
    private AppointmentSlotsService appointmentSlotsService;

    @Autowired
    private TimeSlotsService timeSlotsService;

    private final AppointmentSlotsMapper appointmentSlotsMapper;


    @RequestMapping(method = RequestMethod.POST)
    public AppointmentSlotsDTO create(@RequestBody AppointmentSlotsRequest appointmentSlotsRequest) throws AppointmentSlotsNotFoundException{
        log.debug("CREATE /slot  with request:"+appointmentSlotsRequest);
        AppointmentSlots appointmentSlots = appointmentSlotsMapper.map(appointmentSlotsRequest);
        appointmentSlots = appointmentSlotsService.create(appointmentSlots);
        List<TimeSlots> timeSlots = timeSlotsService.createTimeSlots(appointmentSlots);
        appointmentSlots.setTimeSlots(timeSlots);
        return appointmentSlotsMapper.map(appointmentSlots);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AppointmentSlotsDTO update(@PathVariable("id") Long id,
                                 @RequestBody AppointmentSlotsRequest appointmentSlotsRequest) throws AppointmentSlotsNotFoundException {//what about rem fields if request accepted what abput mandatory fields if dcotor is taken ?
        log.debug("PUT /slot/"+id+"  with request:"+appointmentSlotsRequest);
        AppointmentSlots appointmentSlots =this.appointmentSlotsService.update(id, appointmentSlotsRequest);
        List<TimeSlots> timeSlots = timeSlotsService.createTimeSlots(appointmentSlots);
        appointmentSlots.setTimeSlots(timeSlots);
        return appointmentSlotsMapper.map(appointmentSlots);
    }

    @GetMapping("/{id}")
    public AppointmentSlotsDTO findAppointmentSlots(@PathVariable("id") Long id) throws AppointmentSlotsNotFoundException{
        log.debug("GET /slot/"+id);
        return appointmentSlotsMapper.map(appointmentSlotsService.findAppointmentSlots(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AppointmentSlotsDTO> findAllAppointmentSlotss(){
        log.debug("GET /slot/");
        return appointmentSlotsMapper.map(appointmentSlotsService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteAppointmentSlots(@PathVariable("id") Long id) throws AppointmentSlotsNotFoundException{
        log.debug("DELETE /slot/"+id);
        appointmentSlotsService.delete(id);
        return "AppointmentSlots with id " + id + " deleted";
    }

    @GetMapping("/doctor/{id}")
    public AppointmentSlotsDTO findAppointmentSlotsByDoctorId(@PathVariable("id") Long id) throws AppointmentSlotsNotFoundException{
        log.debug("GET /slot/"+id);
        return appointmentSlotsMapper.map(appointmentSlotsService.findAppointmentSlotsByDoctorId(id));
    }

}
