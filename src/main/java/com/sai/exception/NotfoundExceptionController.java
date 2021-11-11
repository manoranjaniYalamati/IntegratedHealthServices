package com.sai.exception;

import java.util.HashMap;
import java.util.Map;

import com.sai.model.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotfoundExceptionController {

	public NotfoundExceptionController() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(value = PatientNotfoundException.class)
   public ResponseEntity patientException(PatientNotfoundException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "No patient found with given id");
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
   }
	
	
	@ExceptionHandler(value= DoctorNotfoundException.class)
	   public ResponseEntity doctorException(DoctorNotfoundException exception) {
			Map<String, String> message= new HashMap<String, String>();
			message.put("message", "No doctor found ");
			message.put("status","error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	   }

	@ExceptionHandler(value= AppointmentNotFoundException.class)
	public ResponseEntity appontmentException(AppointmentNotFoundException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "No Appointment found ");
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	@ExceptionHandler(value= AppointmentSlotsNotFoundException.class)
	public ResponseEntity appontmentSlotsException(AppointmentSlotsNotFoundException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "No Appointment slot ");
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	@ExceptionHandler(value= AppointmentSlotNotAvailableException.class)//should create new notavaialble controllera?
	public ResponseEntity appontmentSlotNotAvailableException(AppointmentSlotNotAvailableException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "AppointmentSlots not avaiable for this doctor.");
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	@ExceptionHandler(value= RemedyNotFoundException.class)
	public ResponseEntity remedyNotFoundException(RemedyNotFoundException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "Sorry!! Remedy not found");// if not avaialble we can save that in db
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	@ExceptionHandler(value= TimeSlotNotFoundException.class)
	public ResponseEntity timeSlotNotFoundException(TimeSlotNotFoundException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "Sorry!! Remedy not found");// if not avaialble we can save that in db
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

}
