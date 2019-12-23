package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.techelevator.NoProfileException;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Organization;
import com.techelevator.model.OrganizationDAO;
import com.techelevator.model.OrganizationsProfilesDAO;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfileDao;
import com.techelevator.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin
public class OrganizationController {
	
	@Autowired 
	AuthProvider auth;
	
	@Autowired
	OrganizationDAO organizationDao;
	
	@Autowired
	OrganizationsProfilesDAO organizationsProfilesDao;
	
	@Autowired
	ProfileDao profileDao;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String createOrganization(@RequestBody Organization organization){
		
		Organization currentOrg = organizationDao.createOrg(organization.getOrgName(), organization.getPhotoUrl(), 
				organization.getCity(), organization.getState(), organization.getEmail(), organization.getDescription());
		
		User currentUser = auth.getCurrentUser();
		
		Profile currentProfile = profileDao.getProfileForUserId(currentUser.getId());
		
		organizationsProfilesDao.createOrganizationsProfiles(currentOrg.getOrgId(), currentProfile.getProfileId());
		return "{\"success\": true";
	}
	
	@GetMapping
	public Organization getOrganizationByProfile() throws NoProfileException, UnauthorizedException {
		long userId;
		Profile theProfile = new Profile();
        if (auth.isLoggedIn()) {
            userId = auth.getCurrentUser().getId();
            if (profileDao.hasProfile(userId)) {
                theProfile = profileDao.getProfileForUserId(userId);
                return organizationDao.getOrgByProfileId(theProfile.getProfileId());
            } else {
                throw new NoProfileException();
            }
        } else {
            throw new UnauthorizedException();
        }
	}

	@RequestMapping(path="/all", method = RequestMethod.GET)
	public List<Organization> getAllOrganizations() {
		return organizationDao.getAllOrgs();
	}

}
