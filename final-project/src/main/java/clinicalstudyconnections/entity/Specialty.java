package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Specialty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specialtyId;
	
	private String specialtyName;
	
	//ManyToMany Site
	@ManyToMany(mappedBy = "specialties")
	private Set<Site> sites = new HashSet<>();
	
	//OneToOne Clinical Study
	// FIX THIS ------------------------------------------------
	@OneToOne(mappedBy = "specialty")
	private ClinicalStudy clinicalStudy;
}
