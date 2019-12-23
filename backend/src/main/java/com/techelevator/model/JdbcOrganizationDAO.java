package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrganizationDAO implements OrganizationDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcOrganizationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Organization createOrg(String orgName, String photoUrl, String city, String state, String email,
			String description) {
		String sqlCreateOrg = "INSERT INTO organizations (org_id, org_name, photo_url, city, state, email, description) VALUES (?,?,?,?,?,?,?)";
		String sqlGetNextOrg = "SELECT NEXTVAL('organizations_org_id_seq')";
		SqlRowSet nextPkValue = jdbcTemplate.queryForRowSet(sqlGetNextOrg);
		nextPkValue.next();
		Long nextPk = nextPkValue.getLong(1);
		jdbcTemplate.update(sqlCreateOrg, nextPk, orgName, photoUrl, city, state, email, description);
		Organization result = new Organization(nextPk, orgName, photoUrl, city, state, email, description);
		return result;
	}

	@Override
	public Organization getOrg(long orgId) {
		String sqlGetOrg = "SELECT * FROM organizations WHERE org_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetOrg, orgId);
		Organization anOrg = mapRowToOrg(result);
		// TODO Auto-generated method stub
		return anOrg;
	}
	
	@Override
	public Organization getOrgByProfileId(long profileId) {
		Organization theOrg = new Organization();
		String sql = "select organizations.* from organizations join organizations_profiles on organizations_profiles.org_id = organizations.org_id where profile_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, profileId);
		while(result.next()) {
			theOrg = mapRowToOrg(result);
		}
		return theOrg;
	}

	@Override
	public List<Organization> getAllOrgs() {
		List<Organization> resultList = new ArrayList<>();
		String sqlGetAllOrgs = "select * from organizations";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllOrgs);
		while (result.next()) {
			resultList.add(mapRowToOrg(result));
		}
		return resultList;
	}

	public Organization mapRowToOrg(SqlRowSet result) {
		Organization anOrg = new Organization();
		
		anOrg.setOrgId(result.getLong("org_id"));
		anOrg.setOrgName(result.getString("org_name"));
		anOrg.setPhotoUrl(result.getString("photo_url"));
		anOrg.setCity(result.getString("city"));
		anOrg.setState(result.getString("state"));
		anOrg.setEmail(result.getString("email"));
		anOrg.setDescription(result.getString("description"));

		return anOrg;
	}

	

}
