package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.Site;
import lombok.Data;

@Data
public class OwnerData {
	private Long ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String companyName;
	private Set<Site> sites = new HashSet<>();
}
