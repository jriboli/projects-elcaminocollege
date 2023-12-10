package clinicalstudyconnections.service;

import java.util.List;

import org.springframework.stereotype.Service;

import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.model.ClinicalStudyData;
import clinicalstudyconnections.model.DoctorData;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.model.SiteData;

@Service
public class ClinicalStudyConnectionService {

	/*
	 * ---- OWNER --------------------------------------------------
	 */
	public List<OwnerData> getAllOwners() {
		// TODO Auto-generated method stub
		return null;
	}

	public OwnerData getOwner(Long ownerId) {
		Owner owner = findOrCreateOwner(ownerId);
		
		return null;
	}

	public OwnerData saveOwner(OwnerData ownerData) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteOwner(Long ownerId) {
		// TODO Auto-generated method stub
		
	}

	private Owner findOrCreateOwner(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * ---- SITE --------------------------------------------------
	 */

	public List<SiteData> getAllSites(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public SiteData getSiteById(Long ownerId, Long siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	public SiteData saveSite(SiteData siteData) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSite(Long ownerId, Long siteId) {
		// TODO Auto-generated method stub
		
	}

	public void addDoctorToSite(Long ownerId, Long siteId, Long doctorId) {
		// TODO Auto-generated method stub
		
	}

	public void deleteDoctorFromSite(Long ownerId, Long siteId, Long doctorId) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * ---- DOCTOR --------------------------------------------------
	 */

	public List<DoctorData> getAllDoctors(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorData getDoctorById(Long ownerId, Long doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorData saveDoctor(DoctorData doctorData) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteDoctor(Long ownerId, Long doctorId) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * ---- STUDY --------------------------------------------------
	 */

	public List<ClinicalStudyData> getAllStudies() {
		// TODO Auto-generated method stub
		return null;
	}

	public ClinicalStudyData getStudyById(Long studyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ClinicalStudyData saveStudy(ClinicalStudyData clinicalStudyData) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteStudy(Long studyId) {
		// TODO Auto-generated method stub
		
	}

	public void addSiteToStudy(Long studyId, Long siteId) {
		// TODO Auto-generated method stub
		
	}

	public void addPatientToStudy(Long studyId, Long patientId) {
		// TODO Auto-generated method stub
		
	}

	public void deleteSiteFromStudy(Long studyId, Long siteId) {
		// TODO Auto-generated method stub
		
	}

	public void deletePatientFromStudy(Long studyId, Long patientId) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * ---- PATIENT --------------------------------------------------
	 */

	public List<PatientData> getAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	public PatientData getPatientById(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	public PatientData savePatient(PatientData patientData) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletePatient(Long patientId) {
		// TODO Auto-generated method stub
		
	}

}
