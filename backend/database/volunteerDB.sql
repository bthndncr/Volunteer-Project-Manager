BEGIN TRANSACTION;

DROP TABLE IF EXISTS users              cascade;
DROP TABLE IF EXISTS events             cascade;
DROP TABLE IF EXISTS profiles           cascade;
DROP TABLE IF EXISTS causes             cascade;
DROP TABLE IF EXISTS users_causes       cascade;
DROP TABLE IF EXISTS profiles_causes    cascade;
DROP TABLE IF EXISTS organizations_profiles cascade ;
DROP TABLE IF EXISTS organizations      cascade;
DROP TABLE IF EXISTS organizations_users cascade;
DROP TABLE IF EXISTS participants       cascade;
DROP TABLE IF EXISTS events_organizations cascade;


CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

CREATE TABLE profiles (
  profile_id serial PRIMARY KEY,
  first_name varchar(20),
  last_name varchar(20),
  email varchar(40),
  profile_photo varchar,
  city varchar,
  state varchar,
  bio varchar,
  user_id int
);

CREATE TABLE causes (
  cause_id serial PRIMARY KEY,
  name varchar NOT NULL
);

CREATE TABLE profiles_causes (
  pcid serial PRIMARY KEY,
  cause_id int,
  profile_id int
);

CREATE TABLE organizations(
  org_id serial PRIMARY KEY,
  org_name varchar,
  photo_url varchar,
  city varchar,
  state varchar,
  email varchar,
  description varchar
);

CREATE TABLE organizations_profiles (
  org_id int,
  profile_id int,
  CONSTRAINT PK_orgs_users PRIMARY KEY(org_id, profile_id)
);

CREATE TABLE events (
  event_id serial PRIMARY KEY,
  organization_id int,
  event_name varchar,
  event_date date,
  start_time varchar,
  location varchar,
  creator varchar,
  description varchar
);

CREATE TABLE participants (
  event_id int,
  profile_id int,
  CONSTRAINT PK_participants PRIMARY KEY (event_id, profile_id)
);



------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
-----

ALTER TABLE events
ADD CONSTRAINT FK_events_orgs
FOREIGN KEY (organization_id) references organizations(org_id);


ALTER TABLE organizations_profiles
ADD CONSTRAINT FK_orgs_profiles_profiles
FOREIGN KEY (profile_id) references profiles(profile_id);

ALTER TABLE organizations_profiles
ADD CONSTRAINt FK_orgs_profiles_orgs
FOREIGN KEY (org_id) references organizations(org_id);

ALTER TABLE participants 
ADD CONSTRAINT FK_participants_events
FOREIGN KEY (event_id) REFERENCES events(event_id);

ALTER TABLE participants 
ADD CONSTRAINT FK_participants_profiles
FOREIGN KEY (profile_id) REFERENCES profiles(profile_id);

ALTER TABLE profiles_causes 
ADD CONSTRAINT FK_users_causes_causes
FOREIGN KEY (cause_id) REFERENCES causes(cause_id);

ALTER TABLE profiles_causes
ADD CONSTRAINT FK_users_causes_users
FOREIGN KEY (profile_id) REFERENCES profiles(profile_id);

ALTER TABLE profiles
ADD CONSTRAINT FK_profiles_users
FOREIGN KEY (user_id) REFERENCES users(id);


COMMIT TRANSACTION;