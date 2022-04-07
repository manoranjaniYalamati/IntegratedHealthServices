package com.sai.service;

import com.sai.api.dto.DoctorDetailsDTO;
import com.sai.api.requests.DoctorRequest;
import com.sai.exception.AppointmentNotFoundException;
import com.sai.exception.DoctorNotfoundException;
import com.sai.mapper.AppointmentMapper;
import com.sai.mapper.DoctorMapper;
import com.sai.model.Appointment;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.repository.AppointmentRepository;
import com.sai.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private  AppointmentMapper appointmentMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;


    public Doctor create(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor update(Long id, DoctorRequest doctorRequest) throws DoctorNotfoundException{
        Doctor doctor = findDoctor(id);//why this.find
        doctorMapper.merge(doctorRequest, doctor);
        return doctorRepository.save(doctor);
    }

    public Doctor findDoctor(Long id) throws DoctorNotfoundException {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotfoundException("Doctor not found with id : " + id));
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public void delete(Long id) throws DoctorNotfoundException{
        Doctor doctor = findDoctor(id);
        if(doctor != null){//fetch empty util files from company db
            doctorRepository.delete(doctor);
        }
        else{
            throw new DoctorNotfoundException("Doctor Not Found with id " + id);
        }
    }

    public List<DoctorDetailsDTO> findDoctorBySpeciality(String speciality) throws DoctorNotfoundException{
        List<Doctor> doctors = doctorRepository.findDoctorsBySpeciality(speciality);// have to make it as List<Doctor>
        if(doctors != null){
            List<DoctorDetailsDTO> doctorDetailsDTOS = new ArrayList<>();
            for(Doctor doctor : doctors) {
                DoctorDetailsDTO doctorDetailsDTO = new DoctorDetailsDTO();
                doctorDetailsDTO.setId(doctor.getId());
                doctorDetailsDTO.setSpeciality(doctor.getSpeciality());
                doctorDetailsDTO.setExperience(doctor.getExperience());
                doctorDetailsDTO.setName(doctor.getName());
                doctorDetailsDTO.setGender(doctor.getGender());
                doctorDetailsDTOS.add(doctorDetailsDTO);
            }
            return doctorDetailsDTOS;
        }
        else{
            throw new DoctorNotfoundException("Doctor Not Found with speciality " + speciality);//cant we get that error code to be in postman
        }
    }

    public Appointment savePrescriptionOfAppointment(Long appointmentId, String prescription) throws AppointmentNotFoundException{
        Appointment appointment = appointmentService.findAppointment(appointmentId);
        appointment.setPrescription(prescription);
        appointmentService.delete(appointmentId);
        return appointmentRepository.save(appointment);
    }

    public List<DoctorDetailsDTO> findDoctorByExperience(int experience) throws DoctorNotfoundException {
        List<Doctor> doctors = doctorRepository.findDoctorsByExperience(experience);// have to make it as List<Doctor>
        if(doctors != null){
            List<DoctorDetailsDTO> doctorDetailsDTOS = new ArrayList<>();
            for(Doctor doctor : doctors) {
                DoctorDetailsDTO doctorDetailsDTO = new DoctorDetailsDTO();
                doctorDetailsDTO.setId(doctor.getId());
                doctorDetailsDTO.setSpeciality(doctor.getSpeciality());
                doctorDetailsDTO.setExperience(doctor.getExperience());
                doctorDetailsDTO.setName(doctor.getName());
                doctorDetailsDTO.setGender(doctor.getGender());
                doctorDetailsDTOS.add(doctorDetailsDTO);
            }
            return doctorDetailsDTOS;
        }
        else{
            throw new DoctorNotfoundException("Doctor Not Found with experience " + experience);//cant we get that error code to be in postman
        }
    }

    public Long getDoctorIdByUserId(Long userId) {
        Doctor doctor = doctorRepository.findDoctorByUserId(userId);
        return doctor.getId();
    }
}
