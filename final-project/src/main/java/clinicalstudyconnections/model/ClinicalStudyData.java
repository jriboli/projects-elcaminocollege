package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.entity.Specialty;
import clinicalstudyconnections.enums.StudyStatus;
import lombok.Data;

@Data
public class ClinicalStudyData {
	private Long clinicalStudyId;
	private String studyName;
	private String studyDescription;
	private StudyStatus studyStatus;
	private Specialty specialty;
	private Set<Patient> patients = new HashSet<>();
	
	public ClinicalStudyData(ClinicalStudy clinicalStudy) {
		clinicalStudyId = clinicalStudy.getClinicalStudyId();
		studyName = clinicalStudy.getStudyName();
		studyDescription = clinicalStudy.getStudyDescription();
		studyStatus = clinicalStudy.getStudyStatus();
		
		// Specialty
		specialty = clinicalStudy.getSpecialty();
		
		// Patients
		//patients = clinicalStudy.getPatients();
	}
}
