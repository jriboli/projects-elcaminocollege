package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Site;
import clinicalstudyconnections.entity.Specialty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecialtyData {
	private Long specialtyId;
	private String specialtyName;
	private Set<Site> sites = new HashSet<>();
	private ClinicalStudy clinicalStudy;
	
	public SpecialtyData(Specialty specialty) {
		specialtyId = specialty.getSpecialtyId();
		specialtyName = specialty.getSpecialtyName();
		
		// Dont think I need Sites OR CliniclaStudies
	}
}
