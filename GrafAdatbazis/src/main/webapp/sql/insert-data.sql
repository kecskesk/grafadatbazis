INSERT INTO users (username, enabled, password) VALUES ('admin', 1, 'admin');
INSERT INTO user_roles (username, user_role) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO station (station_name) VALUES ('Station A');
INSERT INTO station (station_name) VALUES ('Station B');

INSERT INTO schedule (origin, destination, start_time, end_time) VALUES (1,2,'2017-01-10', '2017-02-02');