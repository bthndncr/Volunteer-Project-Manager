package com.techelevator.model;

import java.util.List;

public interface OrganizationDAO {
	
	public Organization createOrg(String orgName, String photoUrl, String city, String state, String email,
			String description);
	
	public Organization getOrg(long orgId);
	
	public Organization getOrgByProfileId(long profileId);

	public List<Organization> getAllOrgs();

}
