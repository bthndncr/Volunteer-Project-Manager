package com.techelevator.model.participant;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Profile;

@Component
public class JdbcParticipantDao implements ParticipantDao{
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParticipantDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void signUpForEvent(int eventId, long profileId) {
		String sqlSignUp = "INSERT INTO participants(event_id, profile_id) "
						 + "VALUES (?, ?);";
		jdbcTemplate.update(sqlSignUp, eventId, profileId);
		
	}

	@Override
	public int getNumberOfParticipants(int eventId) {
		String sqlGetNumber = "SELECT count(*) FROM participants "
							+ "WHERE event_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetNumber, eventId);
		int totalParticipants = -1;
		try  {
			results.next();
			totalParticipants= results.getInt(1);			
		}
		finally {
		}
		return totalParticipants;
	}

	@Override
	public List<Profile> getListOfParticipants() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
