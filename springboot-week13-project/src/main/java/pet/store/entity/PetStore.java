package pet.store.entity;

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
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PetStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	// MappedBy is like naming it for access in the linked class
	// Note here "petStore" is used in the Employee class
	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
	// I wonder if the Class Type here matters for the linking ???
	private Set<Employee> employees = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	// Name is the TABLE name
	// JoinColumns is using our PetStore ID 
	// This is because one of the two Many to Many artifacts needs to setup this way
	@JoinTable(name = "pet_store_customers",
			joinColumns = @JoinColumn(name = "pet_store_id"),
			inverseJoinColumns = @JoinColumn(name = "customer_id"))
	// Again wonder if the Class Type here matters for the linking ??? 
	private Set<Customer> petStoreCustomers = new HashSet<>();
	
}
