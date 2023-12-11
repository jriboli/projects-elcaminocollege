package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.entity.Specialty;
import clinicalstudyconnections.enums.StudyStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ClinicalStudyData {
	private Long clinicalStudyId;
	private String studyName;
	private String studyDescription;
	private StudyStatus studyStatus;
	@JsonIgnore
	private Specialty specialty;
	private SpecialtyResponse specialtyResponse;
	@JsonIgnore
	private Set<Patient> patients = new HashSet<>();
	
	public ClinicalStudyData(ClinicalStudy clinicalStudy) {
		clinicalStudyId = clinicalStudy.getClinicalStudyId();
		studyName = clinicalStudy.getStudyName();
		studyDescription = clinicalStudy.getStudyDescription();
		studyStatus = clinicalStudy.getStudyStatus();
		
		// Specialty
		specialty = clinicalStudy.getSpecialty();
		
		// Patients
		patients = clinicalStudy.getPatients();
	}
	
	@Data
	@NoArgsConstructor
	static class SpecialtyResponse {
		private Long specialtyId;
		private String specialtyName;
		
		public SpecialtyResponse(Specialty specialty) {
			specialtyId = specialty.getSpecialtyId();
			specialtyName = specialty.getSpecialtyName();
		}
	}
}
