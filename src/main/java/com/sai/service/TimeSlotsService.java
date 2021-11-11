package com.sai.service;

import com.sai.exception.AppointmentSlotsNotFoundException;
import com.sai.exception.TimeSlotNotFoundException;
import com.sai.model.AppointmentSlots;
import com.sai.model.TimeSlots;
import com.sai.repository.TimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimeSlotsService {

    @Autowired
    private TimeSlotsRepository timeSlotsRepository;


    public TimeSlots create(TimeSlots timeSlot){
            return timeSlotsRepository.save(timeSlot);
    }

    public TimeSlots findTimeSlot(Long id) throws TimeSlotNotFoundException {
        return timeSlotsRepository.findById(id).orElseThrow(() -> new TimeSlotNotFoundException("TimeSlot not found with id : " + id));
    }

    public void delete(Long id) throws TimeSlotNotFoundException{
        TimeSlots timeSlot = findTimeSlot(id);
        if(timeSlot != null){//fetch empty util files from company db
            timeSlotsRepository.delete(timeSlot);
        }
        else{
            throw new TimeSlotNotFoundException("TimeSlot Not Found with id " + id);
        }
    }

    public void deleteTimeSlotsBySlotId(Long id) {
        List<TimeSlots> timeSlots = timeSlotsRepository.findTimeSlotsBySlotId(id);
        if(timeSlots != null){
            timeSlotsRepository.deleteAll(timeSlots);
        }
    }

    public List<TimeSlots> createTimeSlots(AppointmentSlots appointmentSlots){
        Date startTime = appointmentSlots.getStartTime();
        Date endTime = appointmentSlots.getEndTime();
        long dif = startTime.getTime();
        //    List<TimeSlots> timeSlots = new List<TimeSlots>();
        List<TimeSlots> timeSlots =  new ArrayList<>();
        while (dif < endTime.getTime()) {
            Date slot = new Date(dif);
            //  System.out.println("Hour Slot --->" + slot);
           // dif += 600000;
            dif += 900000;
            if (dif < endTime.getTime()) {
                TimeSlots timeSlot = new TimeSlots();
                timeSlot.setStartTime(slot);
                timeSlot.setEndTime(new Date(dif));// may be we need to save timeslot too?
                timeSlot.setSlotId(appointmentSlots.getId());// remove or not
                create(timeSlot);
                timeSlots.add(timeSlot);
            }
        }
        return timeSlots;
        //appointmentSlots.setTimeSlots(timeSlots);// needed or not try adding too

    }
}
