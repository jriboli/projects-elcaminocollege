package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.enums.PatientSex;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientData {
	private Long patientId;
	private String patientFirstName;
	private String patientLastName;
	private int patientAge;
	private PatientSex patientSex;
	private Set<ClinicalStudy> clincialStudies = new HashSet<>();
	
	public PatientData(Patient patient) {
		patientId = patient.getPatientId();
		patientFirstName = patient.getPatientFirstName();
		patientLastName = patient.getPatientLastName();
		patientAge = patient.getPatientAge();
		patientSex = patient.getPatientSex();
		
		// Clincial Studies
	}
}
