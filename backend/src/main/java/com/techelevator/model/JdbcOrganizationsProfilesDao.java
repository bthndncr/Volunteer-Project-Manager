package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcOrganizationsProfilesDao implements OrganizationsProfilesDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcOrganizationsProfilesDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public OrganizationsProfiles createOrganizationsProfiles(long orgId, long profileId) {
		String sqlCreateOrgProf = "INSERT INTO organizations_profiles (org_id, profile_id) VALUES (?,?)";
		jdbcTemplate.update(sqlCreateOrgProf, orgId, profileId);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganizationsProfiles> getOrganizationsProfilesByOrgId(long orgId) {
		
		List<OrganizationsProfiles> orgProfList = new ArrayList<OrganizationsProfiles>();
		
		String sqlSelectOrgProf = "SELECT * from organizations_profiles WHERE org_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrgProf, orgId);
		
		while(result.next()) {
			OrganizationsProfiles anOrgProf = mapRowToOrganizationsProfiles(result);
			orgProfList.add(anOrgProf);
		}
		// TODO Auto-generated method stub
		return orgProfList;
	}

	@Override
	public List<OrganizationsProfiles> getOrganizationsProfilesByProfileId(long profileId) {
		
		List<OrganizationsProfiles> orgProfList = new ArrayList<OrganizationsProfiles>();
		
		String sqlSelectOrgProf = "SELECT * from organizations_profiles WHERE profile_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrgProf, profileId);
		
		while(result.next()) {
			OrganizationsProfiles anOrgProf = mapRowToOrganizationsProfiles(result);
			orgProfList.add(anOrgProf);
		}
		// TODO Auto-generated method stub
		return orgProfList;
	}
	
	public OrganizationsProfiles mapRowToOrganizationsProfiles(SqlRowSet result) {
		OrganizationsProfiles anOrgProf = new OrganizationsProfiles();
		
		anOrgProf.setOrgId(result.getLong(1));
		anOrgProf.setProfileId(result.getLong(2));
		
		return anOrgProf;
	}

}
