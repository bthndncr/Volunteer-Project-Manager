package com.techelevator.model.participant;

import java.util.List;

import com.techelevator.model.Profile;

public interface ParticipantDao {
	
	public void signUpForEvent(int eventId, long profileId);
	
	public int getNumberOfParticipants(int eventId);
	
	public List<Profile> getListOfParticipants();

}
