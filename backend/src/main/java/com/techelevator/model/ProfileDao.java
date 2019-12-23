package com.techelevator.model;

public interface ProfileDao {
    public Profile getProfileForUserId(long userId);
    public Profile saveProfile(String firstName, String lastName, String email, String photoUrl, String city, String state, String bio, long userId);
    public boolean hasProfile(long userId);
}
