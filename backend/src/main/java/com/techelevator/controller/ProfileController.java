package com.techelevator.controller;

import com.techelevator.NoProfileException;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfileDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    AuthProvider auth;
    @Autowired
    ProfileDao profileDao;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Profile createProfile(@RequestBody Profile profile) {
        long userId = auth.getCurrentUser().getId();
        Profile newProfile = profileDao.saveProfile(profile.getFirstName(), profile.getLastName(), profile.getEmail(), profile.getPhotoUrl(), profile.getCity(), profile.getState(), profile.getBio(), userId);
        return newProfile;
    }

    @GetMapping
    public Profile getProfileForCurrentUser() throws NoProfileException, UnauthorizedException {
        long userId;
        if (auth.isLoggedIn()) {
            userId = auth.getCurrentUser().getId();
            if (profileDao.hasProfile(userId)) {
                return profileDao.getProfileForUserId(userId);
            } else {
                throw new NoProfileException();
            }
        } else {
            throw new UnauthorizedException();
        }
    }

}
