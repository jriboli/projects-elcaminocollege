package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.enums.PatientSex;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	private String patientFirstName;
	private String patientLastName;
	private int patientAge;
	private PatientSex patientSex;
	
	//ManyToMany Study
	@ManyToMany
	@JoinTable(
			name = "study_patient",
			joinColumns = @JoinColumn(name = "patient_id"),
			inverseJoinColumns = @JoinColumn(name = "study_id"))
	private Set<ClinicalStudy> clinicalStudies = new HashSet<>();
	
}
