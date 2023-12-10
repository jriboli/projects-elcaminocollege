package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
	//This does not solve the Infinite Recursion error - WHY ???
	//So it does help some... Need to understand more - INVESTIGATE
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "owner")
	private Set<Site> sites = new HashSet<>();
	
	//OneToMany Doctors
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "owner")
	private Set<Doctor> doctors = new HashSet<>();
	
	//FOR TESTING ONLY
	public Owner(Long ownerId, String firstName, String lastName, String company, Set<Site> sites) {
		this.ownerId = ownerId;
		this.ownerFirstName = firstName;
		this.ownerLastName = lastName;
		this.companyName = company;
		this.sites = sites;
	}
	
	public Owner() {
		
	}
}
