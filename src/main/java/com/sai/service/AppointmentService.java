package com.sai.service;

import com.sai.api.requests.AppointmentRequest;
import com.sai.config.TokenProvider;
import com.sai.exception.AppointmentNotFoundException;
import com.sai.mapper.AppointmentMapper;
import com.sai.model.Appointment;
import com.sai.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Value("${jwt.header.string}")
    public String HEADER_STRING;

    @Value("${jwt.token.prefix}")
    public String TOKEN_PREFIX;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private TokenProvider jwtTokenUtil;

    public Appointment create(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Appointment update(Long id, AppointmentRequest appointmentRequest) throws AppointmentNotFoundException {
        Appointment appointment = findAppointment(id);//why this.find
        appointmentMapper.merge(appointmentRequest, appointment);
        return appointmentRepository.save(appointment);
    }

    public String getUserName(HttpServletRequest req, HttpServletResponse res){
        String header = req.getHeader(HEADER_STRING);
        String authToken = header.replace(TOKEN_PREFIX,"");
        return jwtTokenUtil.getUsernameFromToken(authToken);
    }

    public Appointment findAppointment(Long id) throws AppointmentNotFoundException {
        return appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id : " + id));
    }

    public  List<Appointment> findAppointments(Long userId) throws AppointmentNotFoundException{
        return appointmentRepository.findAppointmentByUserId(userId); }

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

    public List<Long> findAppointmentsIdsByDoctorId(Long id){
        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorId(id);
        List<Long> appointmentIds = new ArrayList<>();
        for(Appointment appointment : appointments){
            appointmentIds.add(appointment.getId());
        }
        return  appointmentIds;
    }
}
