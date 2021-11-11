package com.sai.service;

import com.sai.api.requests.AppointmentRequest;
import com.sai.exception.AppointmentNotFoundException;
import com.sai.mapper.AppointmentMapper;
import com.sai.model.Appointment;
import com.sai.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    public Appointment create(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Appointment update(Long id, AppointmentRequest appointmentRequest) throws AppointmentNotFoundException {
        Appointment appointment = findAppointment(id);//why this.find
        appointmentMapper.merge(appointmentRequest, appointment);
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppointment(Long id) throws AppointmentNotFoundException {
        return appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id : " + id));
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public void delete(Long id) throws AppointmentNotFoundException{
        Appointment appointment = findAppointment(id);
        if(appointment != null){//fetch empty util files from company db
            appointmentRepository.delete(appointment);
        }
        else{
            throw new AppointmentNotFoundException("Appointment Not Found with id " + id);
        }
    }
}
