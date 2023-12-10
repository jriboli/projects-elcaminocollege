package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

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
}
