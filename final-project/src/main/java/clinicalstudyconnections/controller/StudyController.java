package clinicalstudyconnections.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clinicalstudyconnections.model.ClinicalStudyData;
import clinicalstudyconnections.service.ClinicalStudyConnectionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clinical-study-connection")
@Slf4j
public class StudyController {
	private ClinicalStudyConnectionService service;
	
	public StudyController(ClinicalStudyConnectionService service) {
		this.service = service;
	}
	/*
	 * ---- CLINICAL STUDY --------------------------------------------------------------------
	 */
	
	// Adding a filter parameter when searching all Studies
	@GetMapping("/study")
	public List<ClinicalStudyData> getAllStudies(@RequestParam String specialty) {
		log.info("Grabbing all Studies");
		if(Objects.isNull(specialty)) {
			return service.getAllStudies();
		}
		else {
			return service.getStudiesBySpecialty(specialty);
		}
	}
	
	@GetMapping("/study/{studyId}")
	public ClinicalStudyData getStudyById(@PathVariable Long studyId) {
		log.info("Grab Clinical Study with ID={}", studyId);
		return service.getStudyById(studyId);
	}
	
	@PostMapping("/study")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClinicalStudyData createStudy(@RequestBody ClinicalStudyData clinicalStudyData) {
		log.info("Create Clinical Study {}", clinicalStudyData);
		// NEED TO FIX - DONE
		// ERROR - "Type definition error: [simple type, class clinicalstudyconnections.model.ClinicalStudyData]"
		// Fix - Needed to update the ClinicalStudyData class 
		return service.saveStudy(clinicalStudyData);
	}
	
	@PutMapping("/study/{studyId}")
	public ClinicalStudyData updateStudy(@PathVariable Long studyId, @RequestBody ClinicalStudyData clinicalStudyData) {
		clinicalStudyData.setClinicalStudyId(studyId);
		log.info("Update Clinical Study {}", clinicalStudyData);
		return service.saveStudy(clinicalStudyData);
	}
	
	@DeleteMapping("/study/{studyId}")
	public Map<String, String> deleteStudy(@PathVariable Long studyId) {
		log.info("Delete Clincial Study with ID={}", studyId);
		// We need to do more than just DELETE... 
		// Should we remove Patient and Sites first - ???
		service.deleteStudy(studyId);
		return Map.of("message", String.format("Deleting Clinical Study with ID=%s was successful", studyId));
	}
	
	// Adding Site to Study
	@PostMapping("/study/{studyId}/site/{siteId}")
	public Map<String, String> enrollSite(@PathVariable Long studyId, @PathVariable Long siteId) {
		log.info("Adding Site with ID={} to Study with ID={}", studyId, siteId);
		// NEED TO FIX - DONE
		// INFINITE LOOP
		// ERROR - "Handler dispatch failed: java.lang.StackOverflowError"
		// Fixed - forgot to document the how --- tisk tisk 
		service.enrollSite(studyId, siteId);
		
		return Map.of("message", "Site was successfully associated with Site");
	}
	
	@DeleteMapping("/study/{studyId}/site/{siteId}")
	public Map<String, String> removeSite(@PathVariable Long studyId, @PathVariable Long siteId) {
		log.info("Deleting Site with ID={} to Study with ID={}", studyId, siteId);
		service.removeSite(studyId, siteId);
		
		return Map.of("message", "Site was successfully removed from Site");
	}
	
	@PostMapping("/study/{studyId}/patient/{patientId}")
	public Map<String, String> enrollPatient(@PathVariable Long studyId, @PathVariable Long patientId) {
		log.info("Adding Patient with ID={} to Study with ID={}", studyId, patientId);
		service.enrollPatient(studyId, patientId);
		
		return Map.of("message", "Patient was successfully enrolled with Site");
	}
	
	@DeleteMapping("/study/{studyId}/patient/{patientId}")
	public Map<String, String> removePatient(@PathVariable Long studyId, @PathVariable Long patientId) {
		log.info("Deleting Patient with ID={} to Study with ID={}", studyId, patientId);
		service.removePatient(studyId, patientId);
		
		return Map.of("message", "Site was successfully removed from Site");
	}
}
