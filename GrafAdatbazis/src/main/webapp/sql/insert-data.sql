INSERT INTO users (username, enabled, password) VALUES ('admin', 1, 'admin');
INSERT INTO user_roles (username, user_role, user_ID) VALUES ('admin', 'ROLE_ADMIN', 1);

INSERT INTO graph (name, descriptor) VALUES ('K3', '*12*23*13');
INSERT INTO graph (name, descriptor) VALUES ('K5', '*1234*2345*1245*1235*1345');
INSERT INTO graph (name, descriptor) VALUES ('Bull', '*2*134*24*235*4');
INSERT INTO graph (name, descriptor) VALUES ('Moser', '*2345*136*126*157*147*237*456');
INSERT INTO graph (name, descriptor) VALUES ('K3-3', '*246*135*246*135*246*135');
INSERT INTO graph (name, descriptor) VALUES ('Butterfly', '*23*13*1245*35*34');
INSERT INTO graph (name, descriptor) VALUES ('K2-3', '*24*135*24*135*24');
INSERT INTO graph (name, descriptor) VALUES ('S6', '*234567*1*1*1*1*1*1');
INSERT INTO graph (name, descriptor) VALUES ('S7', '*2345678*1*1*1*1*1*1*1');
INSERT INTO graph (name, descriptor) VALUES ('S8', '*23456789*1*1*1*1*1*1*1*1');
INSERT INTO graph (name, descriptor) VALUES ('W5', '*2345*124*135*124*135');