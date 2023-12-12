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
import clinicalstudyconnections.model.DoctorData;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.model.SiteData;
import clinicalstudyconnections.service.ClinicalStudyConnectionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clinical-study-connection")
@Slf4j
public class OwnerController {
	
	private ClinicalStudyConnectionService service;
	
	public OwnerController(ClinicalStudyConnectionService service) {
		this.service = service;
	}
	
	/*
	 * ---- OWNER --------------------------------------------------------------------
	 */

	@GetMapping("/owner")
	public List<OwnerData> getAllOwners() {
		log.info("Grabbing all owners");
		return service.getAllOwners();		
	}
	
	@GetMapping("/owner/{ownerId}")
	public OwnerData getOwnerById(@PathVariable Long ownerId) {
		log.info("Find owner with ID={}", ownerId);
		return service.getOwner(ownerId);
	}
	
	@PostMapping("/owner")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OwnerData createOwner(@RequestBody OwnerData ownerData) {
		log.info("Create owner {}", ownerData);
		return service.saveOwner(ownerData);
	}

	@PutMapping("/owner/{ownerId}")
	public OwnerData updateOwner(@PathVariable Long ownerId, @RequestBody OwnerData ownerData) {
		ownerData.setOwnerId(ownerId);
		log.info("Update owner {}", ownerData);
		return service.saveOwner(ownerData);
	}
	
	// We have this just to show an Error - Not Allowed
	// Return HTTP Code 405 
	@DeleteMapping("/owner")
	// Not Needed - handled in GlobalError class
	//@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> deleteAllOwners() {
		log.info("Trying to delete all Owners");
		throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
	}
	
	@DeleteMapping("/owner/{ownerId}")
	public Map<String, String> deleteOwnerById(@PathVariable Long ownerId) {
		log.info("Delete owner with ID={}", ownerId);
		service.deleteOwner(ownerId);
		return Map.of("message", String.format("Deleting Owner with ID=%s was successful", ownerId));
	}
	
	/*
	 * ---- SITE --------------------------------------------------------------------
	 */
	
	@GetMapping("/owner/{ownerId}/site")
	public List<SiteData> getAllSitesForOwner(@PathVariable Long ownerId) {
		log.info("Grabbing all sites for Owner ID={}", ownerId);
		// NICE TO HAVE 
		// Return message no sites setup yet. Please add through API call
		return service.getAllSites(ownerId);
	}
	
	@GetMapping("/owner/{ownerId}/site/{siteId}")
	public SiteData getSiteById(@PathVariable Long ownerId, @PathVariable Long siteId) {
		log.info("Find Site with ID={} for Owner with ID={}", siteId, ownerId);
		return service.getSiteById(ownerId, siteId);
	}
	
	@PostMapping("/owner/{ownerId}/site")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SiteData createSite(@PathVariable Long ownerId, @RequestBody SiteData siteData) {
		log.info("Create Site {}", siteData);
		// NEED TO FIX - DONE
		// THIS SHOULD ASSOCIATED SITE WITH THE OWNER
		// Fix - Added code in Service to find OwnerById and add to Site Obj before saving. 
		return service.saveSite(ownerId, siteData);
	}
	
	@PutMapping("/owner/{ownerId}/site/{siteId}")
	public SiteData updateSite(@PathVariable Long ownerId, @PathVariable Long siteId, @RequestBody SiteData siteData) {
		siteData.setSiteId(siteId);
		log.info("Update Site {}", siteData); 
		// THIS FAILS BECUASE THE CREATE DIDNT ASSOCIATE AN OWNER ID - DONE
		// ERROR - "Cannot invoke \"clinicalstudyconnections.entity.Owner.getOwnerId()\" because the return value of \"clinicalstudyconnections.entity.Site.getOwner()\" is null"
		// Fix - Was fixed with above fix for createSite();
		return service.saveSite(ownerId, siteData);
	}
	
	@DeleteMapping("/owner/{ownerId}/site/{siteId}")
	public Map<String, String> deleteSite(@PathVariable Long ownerId, @PathVariable Long siteId) {
		log.info("Delete Site with ID={}", siteId);
		service.deleteSite(ownerId, siteId);
		// SAME AS ABOVE - DONE
		// Fix - Was fixed with above fix for createSite();
		return Map.of("message", String.format("Deleting Site with ID=%s was successful", siteId));
	}
	
	// Adding Doctor to Site
	@PostMapping("/owner/{ownerId}/site/{siteId}/doctor/{doctorId}")
	public Map<String, String> addDoctorToSite(@PathVariable Long ownerId, @PathVariable Long siteId, @PathVariable Long doctorId) {
		log.info("Adding Doctor with ID={} to Site with ID={}", doctorId, siteId);
		service.addDoctorToSite(ownerId, siteId, doctorId);
		
		return Map.of("message", "Added Doctor to Site successfully.");
	}
	
	@DeleteMapping("/owner/{ownerId}/site/{siteId}/doctor/{doctorId}")
	public Map<String, String> deleteDoctorFromSite(@PathVariable Long ownerId, @PathVariable Long siteId, @PathVariable Long doctorId) {
		log.info("Deleting Doctor with ID={} to Site with ID={}", doctorId, siteId);
		service.deleteDoctorFromSite(ownerId, siteId, doctorId);
		
		return Map.of("message", "Removed Doctor from Site successfully.");
	}
	
	/*
	 * ---- DOCTOR --------------------------------------------------------------------
	 */
	
	@GetMapping("/owner/{ownerId}/doctor")
	public List<DoctorData> getAllDoctors(@PathVariable Long ownerId) {
		log.info("Grabbing all Doctors for Owner with ID={}", ownerId);
		return service.getAllDoctors(ownerId);
	}
	
	@GetMapping("/owner/{ownerId}/doctor/{doctorId}")
	public DoctorData getDoctorById(@PathVariable Long ownerId, @PathVariable Long doctorId) {
		log.info("Grab Doctor with ID={}", doctorId);
		return service.getDoctorById(ownerId, doctorId);
	}
	
	@PostMapping("/owner/{ownerId}/doctor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DoctorData createDoctor(@PathVariable Long ownerId, @RequestBody DoctorData doctorData) {
		log.info("Create Doctor {}", doctorData);
		// NEED TO FIX - DONE
		// ERROR - "not-null property references a null or transient value : clinicalstudyconnections.entity.Doctor.owner"
		// Fix - Added code in Service to find OwnerById and add to Doctor Obj before saving. 
		return service.saveDoctor(ownerId, doctorData);
	}
	
	@PutMapping("/owner/{ownerId}/doctor/{doctorId}")
	public DoctorData updateDoctor(@PathVariable Long ownerId, @PathVariable Long doctorId, @RequestBody DoctorData doctorData) {
		doctorData.setDoctorId(doctorId);
		log.info("Update Doctor {}", doctorData);
		return service.saveDoctor(ownerId, doctorData);
	}
	
	@DeleteMapping("/owner/{ownerId}/doctor/{doctorId}")
	public Map<String, String> deleteDoctor(@PathVariable Long ownerId, @PathVariable Long doctorId) {
		log.info("Delete Doctor with ID={}", doctorId);
		service.deleteDoctor(ownerId, doctorId);
		// NEED TO FIX - DONE
		// SHOULD HAVE FAILED WITH INVALID ID, GOT MESSAGE - "Deleting Doctor with ID=2 was successful"
		// Fix - Not a real issue, User Error
		return Map.of("message", String.format("Deleting Doctor with ID=%s was successful", doctorId));
	}
}