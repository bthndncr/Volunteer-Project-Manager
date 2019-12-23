




INSERT INTO organizations (org_name, photo_url, city, state, email, description)
VALUES ('Habitat for Humanity', 'https://over50andoverseas.com/wp-content/uploads/2018/11/habitat-for-humanity-logo.jpg', 'Cleveland', 'OH', 'user@domain.com', 'Habitat for Humanity partners with people in your community, and all over the world, to help them build or improve a place they can call home. Habitat homeowners help build their own homes alongside volunteers and pay an affordable mortgage. With your support, Habitat homeowners achieve the strength, stability and independence they need to build a better life for themselves and for their families. Through our 2020 Strategic Plan, Habitat for Humanity will serve more people than ever before through decent and affordable housing.');

INSERT INTO organizations (org_name, photo_url, city, state, email, description)
VALUES ('Cleveland Metroparks', 'https://www.clevelandmetroparks.com/getmedia/398b4015-8615-4e4c-9e55-f055272e4fc3/CM-LOGO_VERTICAL_OPTION-A.ashx', 'Cleveland', 'OH', 'user@domain.com', 'Since 2013, Cleveland Metroparks has acquired nearly 1,000 acres of quality wetlands, streams and forests. With a border to Lake Erie, Cleveland Metroparks realizes the importance of urban watersheds. Stormwater, pollution, algal blooms, invasive species and loss of wetlands pose continuing challenges, and the Park District has received national attention for making watershed stewardship a priority through education, scientific monitoring and environmental restoration. As a result of these efforts, Cleveland Metroparks has lowered regional stormwater management costs by $5.12M annually.');

INSERT INTO organizations (org_name, photo_url, city, state, email, description)
VALUES ('The Cleveland Foundation', 'https://s3-prod.crainscleveland.com/s3fs-public/cleve%20foundation_i.jpg', 'Cleveland', 'OH', 'user@domain.com', 'For more than a century, the Cleveland Foundation has partnered with our donors to improve the lives of residents in Cuyahoga, Lake and Geauga counties. Make a difference by giving to your community foundation.');







INSERT INTO events (organization_id, event_name, event_date, start_time, location, creator, description)
VALUES ((select org_id from organizations where org_name = 'Habitat for Humanity'), 'Habitat for Humanity 5k', '2020-04-17', '7:00', 'Public Square, Cleveland, OH', 1, 'We are in need of volunteers to hand out water to racers.');

INSERT INTO events (organization_id, event_name, event_date, start_time, location, creator, description)
VALUES ((select org_id from organizations where org_name = 'Cleveland Metroparks'), 'Edgewater Beach Cleanup', '2020-03-20', '7:00', 'Edgewater Beach, Cleveland, OH ', 1, 'Looking for volunteers to pick up trash and prepare the beach for the 2020 season.');

INSERT INTO events (organization_id, event_name, event_date, start_time, location, creator, description)
VALUES ((select org_id from organizations where org_name = 'The Cleveland Foundation'), 'Common Ground 2019', '2020-06-30', '7:00', '6705 Detroit Avenue, Cleveland, OH 44102', 1, 'Share a meal and a conversation about the places we call home. Talking about what the environment was, is, and will be. Free to attend.');

