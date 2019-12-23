package com.techelevator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Event {
	
	private int eventId;
	private int orgId;
	private String eventName;
	private String orgName;
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private String eventDate;
	private String startTime;
	private String location;
	private String creatorId;
	private String description;
	
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEventDate() {
		
		return eventDate;
	}
	
	public void setEventDate(String eventDate) {
		
		this.eventDate = eventDate;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Event() {};

}
