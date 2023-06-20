use rideservice;

INSERT INTO Account (AccountID, AccountPW, AccountUser)
VALUES 
(1, 'password1', 'user1'),
(2, 'password2', 'user2'),
(3, 'password3', 'user3'),
(4, 'password4', 'user4'),
(5, 'password5', 'user5');
INSERT INTO driver (DriverID, Name, Email, Phone,Account_AccountID)
VALUES 
(1, 'John Smith', 'driver1@rideservice.com','1234567890',1),
(2, 'Sarah Davis', 'driver2@rideservice.com', 1234509876, 4);

INSERT INTO passenger (PassengerID, Name, Email, Phone,Account_AccountID)
VALUES 
(1, 'Peter Johnson', 'passenger1@rideservice.com','1987654320',2),
(2, 'Anne Harris', 'passenger2@rideservice.com','2432167890',3);

INSERT INTO car (CarID, LicensePlate, Make, Model,Year,Driver_DriverID)
VALUES 
(1, 'ABC123', 'Toyota', 'Camry','2020', 2),
(2, 'Def123', 'Toyota', 'Camry','2020', 2),
(3, 'ZZC123', 'Honda', 'Civic','2019', 1);

INSERT INTO rideservice (RideID, Cost, Distance,PickUp_time,DropOff_time,
						Passenger_PassengerID,Driver_DriverID,PickUpID,DropOffID)
VALUES 
(1, 15, 2, 1100,1500, 1,2,1,1),
(2, 10, 5, 700,1000, 2,1,1,1);

INSERT INTO driverrating (RatingID, Comment,DriverRating,Driver_DriverID,Passenger_PassengerID)
VALUES 
(1, 'Good', 5, 2,1),
(2, 'Drive too fast', 2, 1,2);

INSERT INTO payment (PaymentID, Amount,DiscountAmount,Date,RideService_RideID)
VALUES 
(1, 15, 5, '6/20/2023',1),
(2, 10, 2, '5/15/2020',2);

INSERT INTO Promotion (PromotionID, Code)
VALUES 
(1, '$5 OFF'),
(2, '$2 OFF');

INSERT INTO Payment_has_Promotion (Payment_PaymentID, Promotion_PromotionID)
VALUES 
(1, 1),
(2, 2);
INSERT INTO location (Loc_ID, Name_Address)
VALUES 
(1, '123 ABC Street'),
(2, '456 DEF Avenue');

INSERT INTO city (idCIty,Name,Location_Loc_ID)
VALUES 
(1, 'New York',1),
(2, 'Los Angles',2),
(3,'London',2);

INSERT INTO country (idCountry,Name,City_idCity)
VALUES 
(1, 'USA',1),
(2, 'England',3);

UPDATE `RideService`.`Passenger` SET `Name` = 'Ryan Peterson' WHERE `PassengerID` = 1;
UPDATE `RideService`.`Driver` SET `Email` = 'newemail@example.com' WHERE `DriverID` = 2;
UPDATE `RideService`.`Passenger` SET `Phone` = 1234567890 WHERE `PassengerID` = 2;
UPDATE `RideService`.`RideService` SET `Cost` = 25, `Distance` = 15 WHERE `RideID` = 2;
UPDATE `RideService`.`RideService` SET `PickUp_time` = 1632456000, `DropOff_time` = 1632459000 WHERE `RideID` = 2;
UPDATE `RideService`.`Car` SET `LicensePlate` = 'ABC1234' WHERE `CarID` = 1;
UPDATE `RideService`.`Car` SET `Make` = 'Toyota', `Model` = 'Rav4' WHERE `CarID` = 1;
UPDATE `RideService`.`Car` SET `Year` = 2022 WHERE `CarID` = 2;
UPDATE `RideService`.`DriverRating` SET `Comment` = 'Great driver!', `DriverRating` = 5 WHERE `RatingID` = 1;
UPDATE `RideService`.`Payment` SET `Amount` = 50, `Date` = '2023-06-19' WHERE `PaymentID` = 1;
UPDATE `RideService`.`Passenger` SET `Email` = 'Passenger2@rideservice' WHERE `PassengerID` = 2;

DELETE FROM `RideService`.`RideService` WHERE `RideID` = 2;
DELETE FROM `RideService`.`Payment` WHERE `RideService_RideID` = 2;
DELETE FROM `RideService`.`DriverRating` WHERE `Driver_DriverID` = (SELECT `Driver_DriverID` FROM `RideService`.`RideService` WHERE `RideID` = 2);
DELETE FROM `RideService`.`Payment_has_Promotion` WHERE `Payment_PaymentID` = (SELECT `PaymentID` FROM `RideService`.`Payment` WHERE `RideService_RideID` = 2);

DELETE FROM `RideService`.`Car` WHERE `CarID` = 2;
DELETE FROM `RideService`.`Driver` WHERE `DriverID` = (SELECT `Driver_DriverID` FROM `RideService`.`Car` WHERE `CarID` = 2);
DELETE FROM `RideService`.`DriverRating` WHERE `RatingID` = 2;

DELETE FROM `RideService`.`Payment` WHERE `PaymentID` = 1;
DELETE FROM `RideService`.`Payment_has_Promotion` WHERE `Payment_PaymentID` = 1;

DELETE FROM `RideService`.`Account` WHERE `AccountID` = 5;

ALTER TABLE `RideService`.`Passenger` ADD COLUMN `Age` INT;
ALTER TABLE `RideService`.`Passenger` MODIFY COLUMN `Phone` VARCHAR(15) NOT NULL;
ALTER TABLE `RideService`.`Driver` ADD COLUMN `Rating` FLOAT;
ALTER TABLE `RideService`.`RideService` CHANGE COLUMN `Cost` `Fare` INT NOT NULL;
ALTER TABLE `RideService`.`Passenger` ADD CONSTRAINT `uk_Passenger_Email` UNIQUE (`Email`);

-- LEFT JOIN: Retrieve all passengers and their corresponding rides 
SELECT *
FROM `RideService`.`Passenger`
LEFT JOIN `RideService`.`RideService` ON `Passenger`.`PassengerID` = `RideService`.`RideService`.`Passenger_PassengerID`;

-- RIGHT JOIN: Retrieve all drivers and their corresponding cars
SELECT *
FROM `RideService`.`Driver`
RIGHT JOIN `RideService`.`Car` ON `Driver`.`DriverID` = `Car`.`Driver_DriverID`;

-- INNER JOIN: Retrieve all rides and their corresponding passengers and drivers:
SELECT *
FROM `RideService`.`RideService`
INNER JOIN `RideService`.`Passenger` ON `RideService`.`RideService`.`Passenger_PassengerID` = `Passenger`.`PassengerID`
INNER JOIN `RideService`.`Driver` ON `RideService`.`RideService`.`Driver_DriverID` = `Driver`.`DriverID`;

-- Group driverID and calculate driver total distance
SELECT Driver.DriverID, SUM(RideService.distance) AS total_distance
FROM RideService
JOIN Driver ON RideService.Driver_DriverID = Driver.DriverID
GROUP BY Driver.DriverID;

-- Group By passengerID with count totalrides and Having lest than 10 rides
SELECT Passenger_PassengerID, COUNT(*) AS total_rides
FROM RideService
GROUP BY Passenger_PassengerID
HAVING total_rides < 10;
