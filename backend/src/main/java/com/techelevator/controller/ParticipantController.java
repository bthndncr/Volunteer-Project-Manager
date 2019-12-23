package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.model.Event;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfileDao;
import com.techelevator.model.User;
import com.techelevator.model.participant.ParticipantDao;

@RestController
public class ParticipantController {
	
	@Autowired
	ParticipantDao participantDao;
	
	@Autowired
	ProfileDao profileDao;
	
	@Autowired
	AuthProvider auth;
	
	@RequestMapping(path="/signUp", method=RequestMethod.POST)
	public String signUpForEvent(@RequestBody Event event) {
		User currentUser = auth.getCurrentUser();
		long userId = currentUser.getId();
		Profile currentProfile = profileDao.getProfileForUserId(userId);
		participantDao.signUpForEvent(event.getEventId(), currentProfile.getProfileId());
		return "Success";
	}

}
