package com.sai.service;

import com.sai.api.requests.AppointmentSlotsRequest;

import com.sai.exception.AppointmentSlotsNotFoundException;
import com.sai.mapper.AppointmentSlotsMapper;

import com.sai.model.AppointmentSlots;

import com.sai.model.TimeSlots;
import com.sai.repository.AppointmentSlotsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentSlotsService {
    @Autowired
    private AppointmentSlotsRepository appointmentSlotsRepository;

    @Autowired
    private AppointmentSlotsMapper appointmentSlotsMapper;

    @Autowired
    private TimeSlotsService timeSlotsService;


    public AppointmentSlots create(AppointmentSlots appointmentSlots) {
        return appointmentSlotsRepository.save(appointmentSlots);
    }

    public AppointmentSlots update(Long id, AppointmentSlotsRequest appointmentSlotsRequest) throws AppointmentSlotsNotFoundException {
        AppointmentSlots appointmentSlots = findAppointmentSlots(id);//why this.find
        appointmentSlotsMapper.merge(appointmentSlotsRequest, appointmentSlots);
        timeSlotsService.deleteTimeSlotsBySlotId(id);
        return appointmentSlotsRepository.save(appointmentSlots);
    }

    public AppointmentSlots findAppointmentSlots(Long id) throws AppointmentSlotsNotFoundException {
        return appointmentSlotsRepository.findById(id).orElseThrow(() -> new AppointmentSlotsNotFoundException("AppointmentSlots not found with id : " + id));
    }

    public List<AppointmentSlots> findAll() {
        return appointmentSlotsRepository.findAll();
    }

    public void delete(Long id) throws AppointmentSlotsNotFoundException {
        timeSlotsService.deleteTimeSlotsBySlotId(id);
        AppointmentSlots appointmentSlots = findAppointmentSlots(id);
        if (appointmentSlots != null) {//fetch empty util files from company db
            appointmentSlotsRepository.delete(appointmentSlots);
        } else {
            throw new AppointmentSlotsNotFoundException("AppointmentSlots Not Found with id " + id);
        }
    }

    public AppointmentSlots findAppointmentSlotsByDoctorId(Long id) throws AppointmentSlotsNotFoundException {
        AppointmentSlots appointmentSlots = appointmentSlotsRepository.findAppointmentSlotsByDoctorId(id);
        if (appointmentSlots != null) {
            return appointmentSlots;
        } else {
            throw new AppointmentSlotsNotFoundException("AppointmentSlots Not Found with doctorId " + id);
        }
    }
}
