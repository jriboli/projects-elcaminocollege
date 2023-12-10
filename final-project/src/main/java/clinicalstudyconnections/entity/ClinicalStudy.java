package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.enums.StudyStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ClinicalStudy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clinicalStudyId;
	
	private String studyName;
	private String studyDescription;
	private StudyStatus studyStatus;
	
	//OneToOne Specialty
	@OneToOne
	@JoinColumn(name = "specialty_id")
	private Specialty specialty;
	
	//ManyToMany Patient
	@ManyToMany(mappedBy = "clinicalStudies", cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<>();
	
	//ManyToMany Sites
	@ManyToMany(mappedBy = "clinicalStudies")
	private Set<Site> sites = new HashSet<>();
}
