INSERT INTO museum (museum_name, museum_address, museum_city, museum_country, museum_zip, museum_phone, museum_website)
VALUES
('Museum of Antiquity', '123 Main Avenue', 'London', 'England', '66214', '951-884-3674', 'antiquitymuseum.org'),
('Art Institute', '766 Van Gogh Way', 'Paris', 'France', '77142', '823-699-4944', 'instituteofart.gov'),
('Ye Olden Times', '4321 BackInTime Road', 'New York City', 'United States', '55142', '661-786-4431', 'daysofold.com'),
('Museum of Natural History', '721 12th Street W', 'Minneapolis', 'United States', '55116', '612-994-3208', 'museumofnaturalhistory.org'),
('Collection of Curiosities', '420 Leaf Lane', 'Berlin', 'Germany', '77641', '600-977-4237', 'wtfisthat.com');


INSERT INTO exhibition (name_of_exhibition, owner_of_exhibition, type_of_exhibition)
VALUES
('Curse of the Pharoah', 'Society of Egyptologists', 'Culture'),
('The DaVinci Code', 'Rich A.F. Wealthy', 'Art'),
('Pioneer Power', 'New England Historical Society', 'Technology'),
('Ice Berg Right Ahead', 'The Star Liner Society for Historic Preservation', 'Event'),
('Heads Up', 'Sir Weird Guy III', 'Anatomy');


INSERT INTO installation (museum_id, name_of_installation, type_of_installation)
VALUES
('1', 'The Tokugawa Shogunate', 'History'),
('2', 'Mesopotamian Pottery', 'Artifacts'),
('3', 'The Triangle Shirtwaist Factory Fire', 'Event'),
('4', 'The Jurassic Era', 'Paleontology'),
('5', 'Homo Erectus', 'Anthropology');