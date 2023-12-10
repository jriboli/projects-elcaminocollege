package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.entity.Specialty;
import lombok.Data;

@Data
public class SiteData {
	private Long siteId;
	private String siteName;
	private String siteAddress;
	private String siteCity;
	private String siteState;
	private String siteZip;
	private String sitePhone;
	private Owner owner;
	private Set<Doctor> doctors = new HashSet<>();	
	private Set<Specialty> specialties = new HashSet<>();
}
