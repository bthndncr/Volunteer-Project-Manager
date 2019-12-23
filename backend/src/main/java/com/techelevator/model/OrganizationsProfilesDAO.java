package com.techelevator.model;

import java.util.List;

public interface OrganizationsProfilesDAO {
	
	public OrganizationsProfiles createOrganizationsProfiles (long orgId, long profileId);
	
	public List<OrganizationsProfiles> getOrganizationsProfilesByOrgId (long orgId);
	
	public List<OrganizationsProfiles> getOrganizationsProfilesByProfileId (long profileId);

}
