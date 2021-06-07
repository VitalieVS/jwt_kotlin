INSERT INTO countries(id, name, people_count) VALUES (100, 'Moldova', 1000000);
INSERT INTO regions(id, name, country_id) VALUES (101, 'Mun Chisinau', 100);
INSERT INTO cities(id, name, city_id) VALUES (102, 'Botanica', 101);

INSERT INTO countries(id, name, people_count) VALUES (103, 'Romania', 1000000);
INSERT INTO regions(id, name, country_id) VALUES (104, 'Iasi', 103);
INSERT INTO regions(id, name, country_id) VALUES (105, 'Bucuresti', 103);

INSERT INTO cities(id, name, city_id) VALUES (106, 'GroveStreet', 104);
INSERT INTO cities(id, name, city_id) VALUES (107, 'NiggaRayon', 104);

INSERT INTO cities(id, name, city_id) VALUES (108, 'BucurestiStreet', 105);
INSERT INTO cities(id, name, city_id) VALUES (109, 'Vodocacika', 105);