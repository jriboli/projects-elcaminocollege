package clinicalstudyconnections.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	
	private String doctorFirstName;
	private String doctorLastName;
	
	//ManyToOne
	@ManyToOne
	@JoinColumn(name = "site_id", nullable = true)
	private Site site;
	
	//ManyToOne
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private Owner owner;
}
