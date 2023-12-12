package clinicalstudyconnections.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.service.ClinicalStudyConnectionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clinical-study-connection")
@Slf4j
public class PatientController {
	
	private ClinicalStudyConnectionService service;
	
	public PatientController(ClinicalStudyConnectionService service) {
		this.service = service;
	}
	
	/*
	 * ---- PATIENT --------------------------------------------------------------------
	 */
	
	@GetMapping("/patient")
	public List<PatientData> getAllPatients() {
		log.info("Grabbing all Patients");
		return service.getAllPatients();
	}
	
	@GetMapping("/patient/{patientId}")
	public PatientData getPatientById(@PathVariable Long patientId) {
		log.info("Grab Clinical Study with ID={}", patientId);
		return service.getPatientById(patientId);		
	}
	
	@PostMapping("/patient")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientData createPatient(@RequestBody PatientData patientData) {
		log.info("Create Patient {}", patientData);
		return service.savePatient(patientData);
	}
	
	@PutMapping("/patient/{patientId}")
	public PatientData updatePatient(@PathVariable Long patientId, @RequestBody PatientData patientData) {
		patientData.setPatientId(patientId);
		log.info("Update Clinical Study {}", patientData);
		return service.savePatient(patientData);
	}
	
	@DeleteMapping("/patient/{patientId}")
	public Map<String, String> deletePatient(@PathVariable Long patientId) {
		log.info("Delete Patient with ID={}", patientId);
		service.deletePatient(patientId);
		return Map.of("message", String.format("Deleting Patient with ID=%s was successful", patientId));
	}

}
