package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long siteId;
	
	private String siteName;
	private String siteAddress;
	private String siteCity;
	private String siteState;
	private String siteZip;
	private String sitePhone;
	
	//ManyToOne Owner
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	//OneToMany Doctor
	@OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
	private Set<Doctor> doctors = new HashSet<>();
	
	//ManyToMany Specialty
	@ManyToMany
	@JoinTable(
			name = "site_specialty",
			joinColumns = @JoinColumn(name = "site_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id"))	
	private Set<Specialty> specialties = new HashSet<>();
	
	//ManyToMany Studies
	@ManyToMany
	@JoinTable(
			name = "site_study",
			joinColumns = @JoinColumn(name = "site_id"),
			inverseJoinColumns = @JoinColumn(name = "clinical_study_id"))
	private Set<ClinicalStudy> clinicalStudies = new HashSet<>();
	

}
