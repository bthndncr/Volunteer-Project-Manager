package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcEventDao implements EventDao {
	
	JdbcTemplate jdbcTemplate;
	
	public JdbcEventDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> allEvents = new ArrayList<Event>();
		String sqlAllEvents = "SELECT events.*, organizations.org_name as org_name FROM events join organizations on events.organization_id = organizations.org_id ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllEvents);
		while (results.next()) {
			Event newEvent = mapRowToEvent(results);
			allEvents.add(newEvent);
		}
		
		return allEvents;
	}
		
	@Override
	public void saveEvent(int organizationId, String eventName, String eventDate, String startTime, String location, String creator, String description) {
		
		String sql = "insert into events (organization_id, event_name, event_date, start_time, location, creator, description) values(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, organizationId, eventName, java.sql.Date.valueOf(eventDate), startTime, location, creator, description);
		
	}

	@Override
	public List<Event> getEventsForUser(User user) {
		List<Event> resultList = new ArrayList<>();
		String sqlGetEvents = "select events.*, organizations.org_name from events join organizations on events.organization_id = organizations.org_id join participants on participants.event_id = events.event_id join profiles on profiles.profile_id = participants.profile_id where profiles.user_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetEvents, user.getId());
		while (result.next()) {
			resultList.add(mapRowToEvent(result));
		}
		return resultList;
	}
	
	@Override
	public List<Event> getEventsForOrganizationByOrgId(long orgId) {
		List<Event> eventsList = new ArrayList<>();
		String sql = "select events.*, organizations.org_name from events join organizations on events.organization_id = organizations.org_id where organization_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, orgId);
		while(result.next()) {
			eventsList.add(mapRowToEvent(result));
			
		}
		return eventsList;
	}


	private Event mapRowToEvent(SqlRowSet results) {
		Event newEvent = new Event();
		newEvent.setEventId(results.getInt("event_id"));
		newEvent.setEventName(results.getString("event_name"));
		newEvent.setEventDate((results.getString("event_date")));
		newEvent.setOrgId(results.getInt("organization_id"));
		newEvent.setOrgName(results.getString("org_name"));
		newEvent.setCreatorId(results.getString("creator"));
		newEvent.setDescription(results.getString("description"));
		newEvent.setStartTime(results.getString("start_time"));
		newEvent.setLocation(results.getString("location"));
		return newEvent;
	}

	

}
