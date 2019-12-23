package com.techelevator.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.techelevator.NoProfileException;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.Event;
import com.techelevator.model.EventDao;
import com.techelevator.model.Organization;
import com.techelevator.model.OrganizationDAO;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfileDao;

@RestController
public class EventController {

    @Autowired
    AuthProvider auth;
    
    @Autowired
    EventDao eventDao;
    
    @Autowired
	OrganizationDAO organizationDao;
    
    @Autowired
	ProfileDao profileDao;

    //route to show all events

    @RequestMapping(path = "/browseEvents", method = RequestMethod.GET)
    public List<Event> viewAllEvents() {
        return eventDao.getAllEvents();
    }


    @RequestMapping(path = "/createEvent", method = RequestMethod.POST)
    public String saveNewEvent(@RequestBody Event event) {

        // creatorId will be an exiting userId, so only authed user can create events

        eventDao.saveEvent(event.getOrgId(), event.getEventName(), event.getEventDate(), event.getStartTime(), event.getLocation(), event.getCreatorId(), event.getDescription());
        return "Success";
    }
    
    @RequestMapping(path="/api/orgEvents", method=RequestMethod.GET)
    public List<Event> getEventsForOrganization() throws NoProfileException, UnauthorizedException{
    	long userId;
		Profile theProfile = new Profile();
        if (auth.isLoggedIn()) {
            userId = auth.getCurrentUser().getId();
            if (profileDao.hasProfile(userId)) {
                theProfile = profileDao.getProfileForUserId(userId);
            } else {
                throw new NoProfileException();
            }
        } else {
            throw new UnauthorizedException();
        }
        
    	Organization theOrg = organizationDao.getOrgByProfileId(theProfile.getProfileId());
    	return eventDao.getEventsForOrganizationByOrgId(theOrg.getOrgId());
    }

    @RequestMapping(path = "/api/events/currentUser", method = RequestMethod.GET)
    public List<Event> getEventsForCurrentUser() {
        return eventDao.getEventsForUser(auth.getCurrentUser());
    }

    @RequestMapping(path = "/api/events/currentUser/active", method = RequestMethod.GET)
    public List<Event> getActiveEventsForCurrentUser() {
        List<Event> eventList = eventDao.getEventsForUser(auth.getCurrentUser());
        eventList = eventList
                .stream()
                .filter(event -> {
                    LocalDate eventDate = LocalDate.parse(event.getEventDate(), DateTimeFormatter.ISO_DATE);
                    return (LocalDate.now().isBefore(eventDate) || LocalDate.now().isEqual(eventDate));
                })
				.collect(Collectors.toList());
        return eventList;
    }

    //route to sign up for an event (requires auth) ???

}
