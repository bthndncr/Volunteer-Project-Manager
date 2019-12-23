package com.techelevator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcProfileDao implements ProfileDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcProfileDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Profile getProfileForUserId(long userId) {
        String sqlQueryOnId = "SELECT * FROM profiles WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlQueryOnId, userId);
        result.next();
        Profile resultProfile = mapRowToProfile(result);
        return resultProfile;
    }

    private Profile mapRowToProfile(SqlRowSet result) {
        Profile newProfile = new Profile();
        newProfile.setProfileId(result.getLong("profile_id"));
        newProfile.setUserId(result.getLong("user_id"));
        newProfile.setFirstName(result.getString("first_name"));
        newProfile.setLastName(result.getString("last_name"));
        newProfile.setEmail(result.getString("email"));
        newProfile.setPhotoUrl(result.getString("profile_photo"));
        newProfile.setCity(result.getString("city"));
        newProfile.setState(result.getString("state"));
        newProfile.setBio(result.getString("bio"));
        return newProfile;
    }

    @Override
    public Profile saveProfile(String firstName, String lastName, String email, String photoUrl, String city, String state, String bio, long userId) {
        String sqlInsertProfile = "INSERT INTO profiles (profile_id, first_name, last_name, email, profile_photo, city, state, bio, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlGetNextProfPk = "SELECT NEXTVAL('profiles_profile_id_seq')";
        SqlRowSet nextPkResult = jdbcTemplate.queryForRowSet(sqlGetNextProfPk);
        nextPkResult.next();
        long newPk = nextPkResult.getLong(1);
        jdbcTemplate.update(sqlInsertProfile, newPk, firstName, lastName, email, photoUrl, city, state, bio, userId);
        return new Profile(newPk, firstName, lastName, email, photoUrl, city, state, bio, userId);

    }

    @Override
    public boolean hasProfile(long userId) {
        String sqlCheckProfile = "SELECT * FROM profiles WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCheckProfile, userId);
        return results.next();
    }

}
