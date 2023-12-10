package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.entity.Site;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerData {
	private Long ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String companyName;
	private Set<SiteResponse> sites = new HashSet<>();
	
	public OwnerData(Owner owner) {
		this.ownerId = owner.getOwnerId();
		this.ownerFirstName = owner.getOwnerFirstName();
		this.ownerLastName = owner.getOwnerLastName();
		this.companyName = owner.getCompanyName();
		
		owner.getSites().forEach(site -> sites.add(new SiteResponse(site)));
	}
	
	// This should help with the Infinite Recursion error 
	@Data
	@NoArgsConstructor
	static class SiteResponse {
		private Long siteId;
		private String siteName;
		private String siteAddress;
		private String siteCity;
		private String siteState;
		private String siteZip;
		private String sitePhone;
		
		public SiteResponse(Site site) {
			siteId = site.getSiteId();
			siteName = site.getSiteName();
			siteAddress = site.getSiteAddress();
			siteCity = site.getSiteCity();
			siteState = site.getSiteState();
			siteZip = site.getSiteZip();
			sitePhone = site.getSitePhone();
		}
	}
}
