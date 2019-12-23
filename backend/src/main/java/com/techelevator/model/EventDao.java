package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface EventDao {
	
	public List<Event> getAllEvents();
	
	public void saveEvent(int organization_id, String event_name, String event_date, String time, String location, String creator, String description);

	public List<Event> getEventsForUser(User user);
	
	public List<Event> getEventsForOrganizationByOrgId(long orgId);

}
