package com.techelevator.model;

public class OrganizationsProfiles {
	
	private long orgId;
	private long profileId;
	
	
	public long getOrgId() {
		return orgId;
	}


	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}


	public long getProfileId() {
		return profileId;
	}


	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}


	public OrganizationsProfiles(long orgId, long profileId) {
		super();
		this.orgId = orgId;
		this.profileId = profileId;
	}
	
	public OrganizationsProfiles() {
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orgId ^ (orgId >>> 32));
		result = prime * result + (int) (profileId ^ (profileId >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationsProfiles other = (OrganizationsProfiles) obj;
		if (orgId != other.orgId)
			return false;
		if (profileId != other.profileId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrganizationsProfiles [orgId=" + orgId + ", profileId=" + profileId + "]";
	}
	
	
	
	
	
	

}
