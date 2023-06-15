-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Account` (
  `AccountID` VARCHAR(10) NOT NULL,
  `AccountPW` VARCHAR(10) NOT NULL,
  `AccountUser` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`AccountID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Passenger` (
  `PassengerID` INT(5) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` INT NOT NULL,
  `Account_AccountID` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`PassengerID`),
  INDEX `fk_Passenger_Account1_idx` (`Account_AccountID` ASC) VISIBLE,
  CONSTRAINT `fk_Passenger_Account1`
    FOREIGN KEY (`Account_AccountID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Driver` (
  `DriverID` INT(5) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` INT NOT NULL,
  `Account_AccountID` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`DriverID`),
  INDEX `fk_Driver_Account1_idx` (`Account_AccountID` ASC) VISIBLE,
  CONSTRAINT `fk_Driver_Account1`
    FOREIGN KEY (`Account_AccountID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Location` (
  `Loc_ID` INT(5) NOT NULL,
  `Name_Address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Loc_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RideService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RideService` (
  `RideID` INT(5) NOT NULL,
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
    REFERENCES `mydb`.`Passenger` (`PassengerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RideService_Driver1`
    FOREIGN KEY (`Driver_DriverID`)
    REFERENCES `mydb`.`Driver` (`DriverID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PickUpID`
    FOREIGN KEY (`PickUpID`)
    REFERENCES `mydb`.`Location` (`Loc_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `DropOffID`
    FOREIGN KEY (`DropOffID`)
    REFERENCES `mydb`.`Location` (`Loc_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Car` (
  `CarID` INT(5) NOT NULL,
  `LicensePlate` VARCHAR(45) NOT NULL,
  `Make` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `Year` VARCHAR(45) NOT NULL,
  `Driver_DriverID` INT(5) NOT NULL,
  PRIMARY KEY (`CarID`),
  INDEX `fk_Car_Driver1_idx` (`Driver_DriverID` ASC) VISIBLE,
  CONSTRAINT `fk_Car_Driver1`
    FOREIGN KEY (`Driver_DriverID`)
    REFERENCES `mydb`.`Driver` (`DriverID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DriverRating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DriverRating` (
  `RatingID` INT NOT NULL,
  `Comment` MEDIUMTEXT NULL,
  `DriverRating` TINYINT(5) UNSIGNED NULL,
  `Driver_DriverID` INT(5) NOT NULL,
  `Passenger_PassengerID` INT(5) NOT NULL,
  PRIMARY KEY (`RatingID`),
  INDEX `fk_DriverRating_Driver1_idx` (`Driver_DriverID` ASC) VISIBLE,
  INDEX `fk_DriverRating_Passenger1_idx` (`Passenger_PassengerID` ASC) VISIBLE,
  CONSTRAINT `fk_DriverRating_Driver1`
    FOREIGN KEY (`Driver_DriverID`)
    REFERENCES `mydb`.`Driver` (`DriverID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DriverRating_Passenger1`
    FOREIGN KEY (`Passenger_PassengerID`)
    REFERENCES `mydb`.`Passenger` (`PassengerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Payment` (
  `PaymentID` INT(5) NOT NULL,
  `Amount` INT NOT NULL,
  `DiscountAmount` INT NULL,
  `Date` VARCHAR(10) NOT NULL,
  `RideService_RideID` INT(5) NOT NULL,
  PRIMARY KEY (`PaymentID`),
  INDEX `fk_Payment_RideService1_idx` (`RideService_RideID` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_RideService1`
    FOREIGN KEY (`RideService_RideID`)
    REFERENCES `mydb`.`RideService` (`RideID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Promotion` (
  `PromotionID` INT(5) NOT NULL,
  `Code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`PromotionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Payment_has_Promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Payment_has_Promotion` (
  `Payment_PaymentID` INT(5) NOT NULL,
  `Promotion_PromotionID` INT(5) NOT NULL,
  PRIMARY KEY (`Payment_PaymentID`, `Promotion_PromotionID`),
  INDEX `fk_Payment_has_Promotion_Promotion1_idx` (`Promotion_PromotionID` ASC) VISIBLE,
  INDEX `fk_Payment_has_Promotion_Payment1_idx` (`Payment_PaymentID` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_has_Promotion_Payment1`
    FOREIGN KEY (`Payment_PaymentID`)
    REFERENCES `mydb`.`Payment` (`PaymentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_has_Promotion_Promotion1`
    FOREIGN KEY (`Promotion_PromotionID`)
    REFERENCES `mydb`.`Promotion` (`PromotionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`City`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`City` (
  `idCity` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Location_Loc_ID` INT(5) NOT NULL,
  PRIMARY KEY (`idCity`),
  INDEX `fk_City_Location1_idx` (`Location_Loc_ID` ASC) VISIBLE,
  CONSTRAINT `fk_City_Location1`
    FOREIGN KEY (`Location_Loc_ID`)
    REFERENCES `mydb`.`Location` (`Loc_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Country` (
  `idCountry` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `City_idCity` INT NOT NULL,
  PRIMARY KEY (`idCountry`),
  INDEX `fk_Country_City1_idx` (`City_idCity` ASC) VISIBLE,
  CONSTRAINT `fk_Country_City1`
    FOREIGN KEY (`City_idCity`)
    REFERENCES `mydb`.`City` (`idCity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
