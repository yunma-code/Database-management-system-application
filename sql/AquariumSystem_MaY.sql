
CREATE DATABASE IF NOT EXISTS `AquariumSystem_MaY`;
USE AquariumSystem_MaY;

-- Staff: represents employee responsibilities of animal caring and managing events
CREATE TABLE IF NOT EXISTS Staff (
	staff_id INT PRIMARY KEY, 
    staff_name VARCHAR(100) NOT NULL,
    staff_role VARCHAR(50) NOT NULL, 
    contact_info VARCHAR(100) UNIQUE NOT NULL
);
-- Tank: represents tanks where the animals live in
CREATE TABLE IF NOT EXISTS Tank (
	tank_id INT PRIMARY KEY,
    location VARCHAR(100) NOT NULL,
    water_type VARCHAR(50) NOT NULL,
    temperature FLOAT NOT NULL CHECK (temperature between -10 and 40),
    capacity INT NOT NULL CHECK (capacity > 0)
);
-- Animal: represents animals in the aquarium
CREATE TABLE IF NOT EXISTS Animal (
	animal_id INT NOT NULL,
	scientific_name VARCHAR(100) NOT NULL,
    common_name VARCHAR(100),
    tank_id INT, 
    age INT DEFAULT 0 CHECK (age >= 0),
    diet VARCHAR(100),
    PRIMARY KEY (animal_id, scientific_name),
    FOREIGN KEY (tank_id) REFERENCES Tank(tank_id)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);
-- Care Task: represents tasks for caring the animals or tanks
CREATE TABLE IF NOT EXISTS CareTask(
	task_id INT PRIMARY KEY,
    task_description TEXT NOT NULL,
    frequency VARCHAR(50) NOT NULL,
    special_requirement TEXT
);
-- Schedule: Tracks care tasks and their timing for staff and animals
CREATE TABLE IF NOT EXISTS `Schedule`(
	schedule_id INT PRIMARY KEY,
    task_id INT NOT NULL,
    staff_id INT,
    animal_id INT NOT NULL,
    scientific_name VARCHAR(100) NOT NULL,
    scheduled_date DATE NOT NULL,
    scheduled_time TIME NOT NULL,
    FOREIGN KEY (task_id) REFERENCES CareTask(task_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (animal_id, scientific_name) REFERENCES Animal(animal_id, scientific_name)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);
-- Aquarium Event: represents events such as feeding time, training session or performances.
CREATE TABLE IF NOT EXISTS AquariumEvent(
	event_id INT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    event_location VARCHAR(100) NOT NULL,
    event_description TEXT,
    staff_id INT,
    animal_id INT,
    scientific_name VARCHAR(100),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id)
		ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (animal_id, scientific_name) REFERENCES Animal(animal_id, scientific_name)
		ON DELETE CASCADE
        ON UPDATE CASCADE
	
);

-- staff data
INSERT IGNORE INTO Staff (staff_id, staff_name, staff_role, contact_info) VALUES
(1, 'John Doe', 'CareTaker', 'john_doe@example.com'),
(2, 'Amy Smith', 'Trainer', 'amy_smith@example.com'),
(3, 'April Johnson', 'Event Coordinator', 'april_johnson@example.com'),
(4, 'Mark Lee', 'Veterinarian', 'mark_lee@example.com'),
(5, 'Sophie Brown', 'Aquarist', 'sophie_brown@example.com'),
(6, 'James Green', 'CareTaker', 'james_green@example.com'),
(7, 'Emma Davis', 'Trainer', 'emma_davis@example.com'),
(8, 'Ethan Taylor', 'Maintenance Manager', 'ethan_taylor@example.com'),
(9, 'Olivia Wilson', 'Nutritionist', 'olivia_wilson@example.com'),
(10, 'Liam Moore', 'Animal Behaviorist', 'liam_moore@example.com');

-- tank data
INSERT IGNORE INTO Tank (tank_id, location, water_type, temperature, capacity) VALUES
(1, 'North Wing', 'Freshwater', 24.5, 200),
(2, 'South Wing', 'Saltwater', 22.0, 1000),
(3, 'East Wing', 'Saltwater', 25.0, 150),
(4, 'West Wing', 'Saltwater', 0.5, 800),
(5, 'Research Lab', 'Freshwater', 24.0, 300),
(6, 'Kids Zone', 'Saltwater', 27.0, 200),
(7, 'Outdoor Exhibit', 'Freshwater', 28.5, 1000),
(8, 'Show Tank', 'Saltwater', 25.5, 1200);

-- animal data
INSERT IGNORE INTO Animal (animal_id, scientific_name, common_name, tank_id, age, diet) VALUES
(1, 'Bathynomus giganteus', 'Giant Isopod', 4, 5, 'Carnivore'),
(2, 'Amphiprioninae', 'Clownfish', 8, 2, 'Omnivore'),
(3, 'Carcharodon carcharias', 'Great White Shark', 2, 8, 'Carnivore'),
(4, 'Balaenoptera musculus', 'Blue Whale', 2, 15, 'Carnivore'),
(5, 'Delphinidae', 'Dolphin', 3, 6, 'Carnivore'),
(6, 'Chelonia mydas', 'Green Sea Turtle', 5, 20, 'Herbivore'),
(7, 'Aptenodytes forsteri', 'Emperor Penguin', 7, 10, 'Carnivore'),
(8, 'Manta birostris', 'Manta Ray', 2, 12, 'Planktivore'),
(9, 'Carcharhinus leucas', 'Bull Shark', 2, 7, 'Carnivore'),
(10, 'Hippocampus comes', 'Tiger Tail Seahorse', 4, 3, 'Carnivore'),
(11, 'Aurelia aurita', 'Jellyfish', 6, 4, 'Carnivore'),
(12, 'Enhydra lutris', 'Sea Otter', 7, 5, 'Carnivore'),
(13, 'Eunectes murinus', 'Green Anaconda', 1, 12, 'Carnivore'),
(14, 'Sphyrna mokarran', 'Hammerhead Shark', 2, 10, 'Carnivore'),
(15, 'Eretmochelys imbricata', 'Hawksbill Sea Turtle', 3, 8, 'Omnivore');

-- care task data
INSERT IGNORE INTO CareTask (task_id, task_description, frequency, special_requirement) VALUES
(1, 'Clean the tank', 'Weekly', 'Use eco-friendly materials'),
(2, 'Feed the animal', 'Daily', 'Follow dietary guidlines'),
(3, 'Check water quality', 'Weekly', 'Test pH, ammonia, and nitrate levels'),
(4, 'Monitor animal health', 'Daily', 'Observe behavior and note any abnormalities'),
(5, 'Change water', 'Monthly', 'Ensure proper filtration and temperature adjustment');

-- schedule data
INSERT IGNORE INTO `Schedule` (schedule_id, task_id, staff_id, animal_id, scientific_name, scheduled_date, scheduled_time) VALUES
(1, 2, 1, 1, 'Bathynomus giganteus', '2024-12-01', '09:00:00'),
(2, 1, 1, 2, 'Amphiprioninae', '2024-12-02', '08:30:00'),
(3, 3, 2, 3, 'Carcharodon carcharias', '2024-12-03', '10:00:00'),
(4, 4, 3, 4, 'Balaenoptera musculus', '2024-12-04', '14:00:00'),
(5, 5, 1, 5, 'Delphinidae', '2024-12-05', '11:30:00');

-- event data
INSERT IGNORE INTO AquariumEvent (event_id, event_name, event_date, event_time, event_location, event_description, staff_id, animal_id, scientific_name) VALUES
(1, 'Dolphin feeding show', '2024-12-02', '15:00:00', 'South Wing', 'Public feeding event for dolphins', 2, 1, 'Bathynomus giganteus'), 
(2, 'Sea Otter training session', '2024-12-01', '10:00:00', 'East Wing', 'Public training session for sea otters', 10, 12, 'Enhydra lutris'),
(3, 'Jellyfish touching session', '2024-12-04', '14:00:00', 'Kids Zone', 'Jellyfish touching session for kids', 7, 11, 'Aurelia aurita');

-- Create Staff operation
DELIMITER //
DROP PROCEDURE IF EXISTS CreateStaff;
CREATE PROCEDURE CreateStaff(
    IN staff_id INT,
    IN staff_name VARCHAR(100),
    IN staff_role VARCHAR(50),
    IN contact_info VARCHAR(100)
)
BEGIN
	INSERT INTO Staff (staff_id, staff_name, staff_role, contact_info)
	VALUES (staff_id, staff_name, staff_role, contact_info);
END //
DELIMITER ;
-- Create Tank operation 
DELIMITER //
DROP PROCEDURE IF EXISTS CreateTank;
CREATE PROCEDURE CreateTank(
    IN tank_id INT,
    IN location VARCHAR(100),
    IN water_type VARCHAR(50),
    IN temperature FLOAT,
    IN capacity INT
)
BEGIN
    INSERT INTO Tank (tank_id, location, water_type, temperature, capacity)
    VALUES (tank_id, location, water_type, temperature, capacity);
END //
DELIMITER ;
-- Create Animal operation
DELIMITER //
DROP PROCEDURE IF EXISTS CreateAnimal;
CREATE PROCEDURE CreateAnimal(
	IN animal_id INT,
    IN scientific_name VARCHAR(100),
    IN common_name VARCHAR(100),
    IN tank_id INT,
    IN age INT,
    IN diet VARCHAR(100)
)
BEGIN
    INSERT INTO Animal (animal_id, scientific_name, common_name, tank_id, age, diet)
    VALUES (animal_id, scientific_name, common_name, tank_id, age, diet);
END //
DELIMITER ;
-- Create CareTask opeartion 
DELIMITER //
DROP PROCEDURE IF EXISTS CreateCareTask;
CREATE PROCEDURE CreateCareTask(
    IN task_id INT,
    IN task_description TEXT,
    IN frequency VARCHAR(50),
    IN special_requirement TEXT
)
BEGIN
    INSERT INTO CareTask (task_id, task_description, frequency, special_requirement)
    VALUES (task_id, task_description, frequency, special_requirement);
END //
DELIMITER ;
-- Create Schedule operation
DELIMITER //
DROP PROCEDURE IF EXISTS CreateSchedule;
CREATE PROCEDURE CreateSchedule(
	IN schedule_id INT,
    IN task_id INT,
    IN staff_id INT,
    IN animal_id INT,
    IN scientific_name VARCHAR(100),
    IN scheduled_date DATE,
    IN scheduled_time TIME
)
BEGIN
    INSERT INTO `Schedule` (schedule_id, task_id, staff_id, animal_id, scientific_name, scheduled_date, scheduled_time)
    VALUES (schedule_id, task_id, staff_id, animal_id, scientific_name, scheduled_date, scheduled_time);
END //
DELIMITER ;
-- Create Event opeartion 
DELIMITER //
DROP PROCEDURE IF EXISTS CreateAquariumEvent;
CREATE PROCEDURE CreateAquariumEvent(
    IN event_id INT,
    IN event_name VARCHAR(100),
    IN event_date DATE,
    IN event_time TIME,
    IN event_location VARCHAR(100),
    IN event_description TEXT,
    IN staff_id INT,
    IN animal_id INT,
    IN scientific_name VARCHAR(100)
)
BEGIN
    INSERT INTO AquariumEvent (event_id, event_name, event_date, event_time, event_location, event_description, staff_id, animal_id, scientific_name)
    VALUES (event_id, event_name, event_date, event_time, event_location, event_description, staff_id, animal_id, scientific_name);
END //
DELIMITER ;

-- Delete operations
-- staff
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteStaff;
CREATE PROCEDURE DeleteStaff(
    IN in_staff_id INT
)
BEGIN
    DELETE FROM Staff WHERE staff_id = in_staff_id;
END //
DELIMITER ;
-- tank
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteTank;
CREATE PROCEDURE DeleteTank(
    IN in_tank_id INT
)
BEGIN
    DELETE FROM Tank WHERE tank_id = in_tank_id;
END //
DELIMITER ;
-- Animal
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteAnimal;
CREATE PROCEDURE DeleteAnimal(
    IN in_animal_id INT
)
BEGIN
    DELETE FROM Animal WHERE animal_id = in_animal_id;
END //
DELIMITER ;
-- CareTask
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteCareTask;
CREATE PROCEDURE DeleteCareTask(
    IN in_task_id INT
)
BEGIN
    DELETE FROM CareTask WHERE task_id = in_task_id;
END //
DELIMITER ;
-- schedule
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteSchedule;
CREATE PROCEDURE DeleteSchedule(
    IN in_schedule_id INT
)
BEGIN
    DELETE FROM Schedule WHERE schedule_id = in_schedule_id;
END //
DELIMITER ;
-- event
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteAquariumEvent;
CREATE PROCEDURE DeleteAquariumEvent(
    IN in_event_id INT
)
BEGIN
    DELETE FROM AquariumEvent WHERE event_id = in_event_id;
END //
DELIMITER ;

-- Read operations
-- staff
DELIMITER //
DROP PROCEDURE IF EXISTS GetStaff;
CREATE PROCEDURE GetStaff()
BEGIN
    SELECT * FROM Staff;
END //
DELIMITER ;
-- tank
DELIMITER //
DROP PROCEDURE IF EXISTS GetTank;
CREATE PROCEDURE GetTank()
BEGIN
    SELECT * FROM Tank;
END //
DELIMITER ;
-- Animal
DELIMITER //
DROP PROCEDURE IF EXISTS GetAnimal;
CREATE PROCEDURE GetAnimal()
BEGIN
    SELECT * FROM Animal;
END //
DELIMITER ;
-- care task
DELIMITER //
DROP PROCEDURE IF EXISTS GetCareTask;
CREATE PROCEDURE GetCareTask()
BEGIN
    SELECT * FROM CareTask;
END //
DELIMITER ;
-- schedule
DELIMITER //
DROP PROCEDURE IF EXISTS GetSchedule;
CREATE PROCEDURE GetSchedule()
BEGIN
    SELECT * FROM `Schedule`;
END //
DELIMITER ;
-- event
DELIMITER //
DROP PROCEDURE IF EXISTS GetAquariumEvent;
CREATE PROCEDURE GetAquariumEvent()
BEGIN
    SELECT * FROM AquariumEvent;
END //
DELIMITER ;

-- Update operations
-- staff
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateStaff;
CREATE PROCEDURE UpdateStaff(
    IN in_staff_id INT,
    IN in_staff_name VARCHAR(100),
    IN in_staff_role VARCHAR(50),
    IN in_contact_info VARCHAR(100)
)
BEGIN
    UPDATE Staff
    SET staff_name = in_staff_name, staff_role = in_staff_role, contact_info = in_contact_info
    WHERE staff_id = in_staff_id;
END //
DELIMITER ;
-- tank
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateTank;
CREATE PROCEDURE UpdateTank(
    IN in_tank_id INT,
    IN in_location VARCHAR(100),
    IN in_water_type VARCHAR(50),
    IN in_temperature FLOAT,
    IN in_capacity INT
)
BEGIN
    UPDATE Tank
    SET location = in_location, water_type = in_water_type, temperature = in_temperature, capacity = in_capacity
    WHERE in_tank_id = tank_id;
END //
DELIMITER ;
-- Animal
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateAnimal;
CREATE PROCEDURE UpdateAnimal(
    IN in_animal_id INT,
    IN in_scientific_name VARCHAR(100),
    IN in_common_name VARCHAR(100),
    IN in_tank_id INT,
    IN in_age INT,
    IN in_diet VARCHAR(100)
)
BEGIN
    UPDATE Animal
    SET scientific_name = in_scientific_name, common_name = in_common_name, tank_id = in_tank_id, age = in_age, diet = in_diet
    WHERE animal_id = in_animal_id AND scientific_name = in_scientific_name;
END //
DELIMITER ;
-- care task
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateCareTask;
CREATE PROCEDURE UpdateCareTask(
    IN in_task_id INT,
    IN in_task_description TEXT,
    IN in_frequency VARCHAR(50),
    IN in_special_requirement TEXT
)
BEGIN
    UPDATE CareTask
    SET task_description = in_task_description, frequency = in_frequency, special_requirement = in_special_requirement
    WHERE task_id = in_task_id;
END //
DELIMITER ;
-- schedule
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateSchedule;
CREATE PROCEDURE UpdateSchedule(
    IN in_schedule_id INT,
    IN in_task_id INT,
    IN in_staff_id INT,
    IN in_animal_id INT,
    IN in_scientific_name VARCHAR(100),
    IN in_scheduled_date DATE,
    IN in_scheduled_time TIME
)
BEGIN
    UPDATE Schedule
    SET task_id = in_task_id, staff_id = in_staff_id, animal_id = in_animal_id, scientific_name = in_scientific_name, scheduled_date = in_scheduled_date, scheduled_time = in_scheduled_time
    WHERE schedule_id = in_schedule_id;
END //
DELIMITER ;
-- event
DELIMITER //
DROP PROCEDURE IF EXISTS UpdateAquariumEvent;
CREATE PROCEDURE UpdateAquariumEvent(
    IN in_event_id INT,
    IN in_event_name VARCHAR(100),
    IN in_event_date DATE,
    IN in_event_time TIME,
    IN in_event_location VARCHAR(100),
    IN in_event_description TEXT,
    IN in_staff_id INT,
    IN in_animal_id INT,
    IN in_scientific_name VARCHAR(100)
)
BEGIN
    UPDATE AquariumEvent
    SET event_name = in_event_name, event_date = in_event_date, event_time = in_event_time, event_location = in_event_location, 
        event_description = in_event_description, staff_id = in_staff_id, animal_id = in_animal_id, scientific_name = in_scientific_name
    WHERE event_id = in_event_id;
END //
DELIMITER ;


-- other operations:
-- trigger: update animal age automatically on a yearly basis
DELIMITER //
DROP TRIGGER IF EXISTS UpdateAnimalAge;
CREATE TRIGGER UpdateAnimalAge
AFTER INSERT ON Animal
FOR EACH ROW
BEGIN
	IF NEW.age = 0 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Age cannot be zero';
	END IF;
END //
DELIMITER ;
-- stored procedure: retrieve event scheduled by staff
DELIMITER //
DROP PROCEDURE IF EXISTS GetEventByStaff;
CREATE PROCEDURE GetEventByStaff(IN staffId INT)
BEGIN
	SELECT event_id, event_name, event_date, event_time, event_location, event_description
    FROM AquariumEvent
    WHERE staff_id = staffId;
END //
DELIMITER ;

