package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ownerId;
	
	private String ownerFirstName;
	private String ownerLastName;
	private String companyName;
	
	//OneToMany Sites
	@OneToMany(mappedBy = "owner")
	private Set<Site> sites = new HashSet<>();
	
	//OneToMany Doctors
	@OneToMany(mappedBy = "owner")
	private Set<Doctor> doctors = new HashSet<>();
}
