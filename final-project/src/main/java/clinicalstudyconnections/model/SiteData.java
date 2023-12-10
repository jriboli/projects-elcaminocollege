package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.entity.Site;
import clinicalstudyconnections.entity.Specialty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
	
	public SiteData(Site site) {
		siteId = site.getSiteId();
		siteName = site.getSiteName();
		siteAddress = site.getSiteAddress();
		siteCity = site.getSiteCity();
		siteState = site.getSiteState();
		siteZip = site.getSiteZip();
		sitePhone = site.getSitePhone();
		
	}
	
	@Data
	@NoArgsConstructor
	static class OwnerResponse {
		private String ownerName;
		
		public OwnerResponse(Owner owner) {
			ownerName = owner.getOwnerFirstName() + " " + owner.getOwnerLastName();
		}
	}
	
	@Data
	@NoArgsConstructor
	static class DoctorResponse {
		private Long doctorId;	
		private String doctorFirstName;
		private String doctorLastName;
		//private Site site;
		
		public DoctorResponse(Doctor doctor) {
			doctorId = doctor.getDoctorId();
			doctorFirstName = doctor.getDoctorFirstName();
			doctorLastName = doctor.getDoctorLastName();
		}
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
