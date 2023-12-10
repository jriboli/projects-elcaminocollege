package clinicalstudyconnections.model;

import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Site;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorData {
	private Long doctorId;	
	private String doctorFirstName;
	private String doctorLastName;
	private Site site;
	
	public DoctorData(Doctor doctor) {
		doctorId = doctor.getDoctorId();
		doctorFirstName = doctor.getDoctorFirstName();
		doctorLastName = doctor.getDoctorLastName();
		
		// Dont need Site - Right ???
	}
}
