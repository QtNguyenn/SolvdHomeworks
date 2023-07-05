SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema RideServiceDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RideServiceDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RideServiceDB` DEFAULT CHARACTER SET utf8 ;
USE `RideServiceDB` ;

-- -----------------------------------------------------
-- Table `RideServiceDB`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Account` (
  `AccountID` INT(5) NOT NULL AUTO_INCREMENT,
  `AccountPW` VARCHAR(15) NOT NULL,
  `AccountUser` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`AccountID`),
  UNIQUE (`AccountUser`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Passenger` (
  `PassengerID` INT(5) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` BIGINT NOT NULL,
  `Account_AccountID` INT(5) NOT NULL,
  PRIMARY KEY (`PassengerID`),
  INDEX `fk_Passenger_Account1_idx` (`Account_AccountID` ASC) VISIBLE,
  CONSTRAINT `fk_Passenger_Account1`
    FOREIGN KEY (`Account_AccountID`)
    REFERENCES `RideServiceDB`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Driver` (
  `DriverID` INT(5) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` BIGINT NOT NULL,
  `Account_AccountID` INT(5) NOT NULL,
  PRIMARY KEY (`DriverID`),
  INDEX `fk_Driver_Account1_idx` (`Account_AccountID` ASC) VISIBLE,
  CONSTRAINT `fk_Driver_Account1`
    FOREIGN KEY (`Account_AccountID`)
    REFERENCES `RideServiceDB`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  UNIQUE (`Account_AccountID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Location` (
  `Loc_ID` INT(5) NOT NULL AUTO_INCREMENT,
  `Name_Address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Loc_ID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`RideService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`RideService` (
  `RideID` INT(5) NOT NULL AUTO_INCREMENT,
  `Cost` INT NOT NULL,
  `Distance` INT NOT NULL,
  `PickUp_time` INT NOT NULL,
  `DropOff_time` INT NOT NULL,
  `Passenger_PassengerID` INT(5) NOT NULL,
  `Driver_DriverID` INT(5) NOT NULL,
  `PickUpID` INT(5) NOT NULL,
  `DropOffID` INT(5) NOT NULL,
  PRIMARY KEY (`RideID`),
  INDEX `fk_RideService_Passenger1_idx` (`Passenger_PassengerID` ASC) VISIBLE,
  INDEX `fk_RideService_Driver1_idx` (`Driver_DriverID` ASC) VISIBLE,
  INDEX `PickUpID_idx` (`PickUpID` ASC) VISIBLE,
  INDEX `DropOffID_idx` (`DropOffID` ASC) VISIBLE,
  CONSTRAINT `fk_RideService_Passenger1`
    FOREIGN KEY (`Passenger_PassengerID`)
    REFERENCES `RideServiceDB`.`Passenger` (`PassengerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_RideService_Driver1`
    FOREIGN KEY (`Driver_DriverID`)
    REFERENCES `RideServiceDB`.`Driver` (`DriverID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `PickUpID`
    FOREIGN KEY (`PickUpID`)
    REFERENCES `RideServiceDB`.`Location` (`Loc_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `DropOffID`
    FOREIGN KEY (`DropOffID`)
    REFERENCES `RideServiceDB`.`Location` (`Loc_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Car` (
  `CarID` INT(5) NOT NULL AUTO_INCREMENT,
  `LicensePlate` VARCHAR(45) NOT NULL,
  `Make` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `Year` INT NOT NULL,   
  `Driver_DriverID` INT(5) NOT NULL,
  PRIMARY KEY (`CarID`),
  INDEX `fk_Car_Driver1_idx` (`Driver_DriverID` ASC) VISIBLE,
  CONSTRAINT `fk_Car_Driver1`
    FOREIGN KEY (`Driver_DriverID`)
    REFERENCES `RideServiceDB`.`Driver` (`DriverID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE (`LicensePlate`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`DriverRating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`DriverRating` (
  `RatingID` INT NOT NULL AUTO_INCREMENT,
  `Comment` MEDIUMTEXT NULL,
  `DriverRating` TINYINT(5) UNSIGNED NULL,
  `Driver_DriverID` INT(5) NOT NULL,
  `Passenger_PassengerID` INT(5) NOT NULL,
  PRIMARY KEY (`RatingID`),
  INDEX `fk_DriverRating_Driver1_idx` (`Driver_DriverID` ASC) VISIBLE,
  INDEX `fk_DriverRating_Passenger1_idx` (`Passenger_PassengerID` ASC) VISIBLE,
  CONSTRAINT `fk_DriverRating_Driver1`
    FOREIGN KEY (`Driver_DriverID`)
    REFERENCES `RideServiceDB`.`Driver` (`DriverID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DriverRating_Passenger1`
    FOREIGN KEY (`Passenger_PassengerID`)
    REFERENCES `RideServiceDB`.`Passenger` (`PassengerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Payment` (
  `PaymentID` INT(5) NOT NULL AUTO_INCREMENT,
  `Amount` INT NOT NULL,
  `DiscountAmount` INT NULL,
  `Date` VARCHAR(10) NOT NULL,
  `RideService_RideID` INT(5) NOT NULL,
  PRIMARY KEY (`PaymentID`),
  INDEX `fk_Payment_RideService1_idx` (`RideService_RideID` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_RideService1`
    FOREIGN KEY (`RideService_RideID`)
    REFERENCES `RideServiceDB`.`RideService` (`RideID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  UNIQUE (`RideService_RideID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Promotion` (
  `PromotionID` INT(5) NOT NULL AUTO_INCREMENT,
  `Code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`PromotionID`),
  UNIQUE (`Code`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Payment_has_Promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Payment_has_Promotion` (
  `Payment_PaymentID` INT(5) NOT NULL,
  `Promotion_PromotionID` INT(5) NOT NULL,
  PRIMARY KEY (`Payment_PaymentID`, `Promotion_PromotionID`),
  INDEX `fk_Payment_has_Promotion_Promotion1_idx` (`Promotion_PromotionID` ASC) VISIBLE,
  INDEX `fk_Payment_has_Promotion_Payment1_idx` (`Payment_PaymentID` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_has_Promotion_Payment1`
    FOREIGN KEY (`Payment_PaymentID`)
    REFERENCES `RideServiceDB`.`Payment` (`PaymentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_has_Promotion_Promotion1`
    FOREIGN KEY (`Promotion_PromotionID`)
    REFERENCES `RideServiceDB`.`Promotion` (`PromotionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`City`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`City` (
  `idCity` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Location_Loc_ID` INT(5) NOT NULL,
  PRIMARY KEY (`idCity`),
  INDEX `fk_City_Location1_idx` (`Location_Loc_ID` ASC) VISIBLE,
  CONSTRAINT `fk_City_Location1`
    FOREIGN KEY (`Location_Loc_ID`)
    REFERENCES `RideServiceDB`.`Location` (`Loc_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RideServiceDB`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RideServiceDB`.`Country` (
  `idCountry` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `City_idCity` INT NOT NULL,
  PRIMARY KEY (`idCountry`),
  INDEX `fk_Country_City1_idx` (`City_idCity` ASC) VISIBLE,
  CONSTRAINT `fk_Country_City1`
    FOREIGN KEY (`City_idCity`)
    REFERENCES `RideServiceDB`.`City` (`idCity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE (`City_idCity`)
)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;