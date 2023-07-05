package com.project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.Database.DatabaseConnection;

import com.project.Exception.DataExistException;
import com.project.Exception.NoAccountExist;
import com.project.daos.*;
import com.project.models.*;
public class Main {
    public static void main(String[] args) 
    {

        final Logger logger = LogManager.getLogger(AccountDAO.class);
        
        //Testing accountDAO   
        try(Connection connection = DatabaseConnection.getConnection())
        {
            AccountDAO accountDAO = new AccountDAO(connection);
            //Test Create method
            for (int i = 1; i <= 15; i++) {
            Account newAccount = new Account();
            newAccount.setUserName("user" + i);
            newAccount.setPassword("password" + i);
            try {
                accountDAO.create(newAccount);
                logger.info("New account created: " + newAccount.getUserName());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }//End-Of-Test Create Method

            //Test getById and update methods
            Account account = new Account();
            try{
            account = accountDAO.getById(15);
            logger.info("Account with ID 15: "+"\n" + account);
            } catch(NoAccountExist e)
            {
                logger.info(e.getMessage());
            }
            logger.info("==========================================");
            if(account != null)
            {   //check if username already taken
                logger.info("Check if username already taken.");
                account.setUserName("user1");
                account.setPassword("upd1");   
                accountDAO.update(account);

                //Check if username and password updated.
                logger.info("Check if username and password updated.");
                account.setUserName("userUpdate");
                account.setPassword("passwordUpdate");   
                accountDAO.update(account);
                
            }//End-Of-Test getById and update method
             
            //Test getAll method and Delete method
            List<Account> allAccounts = accountDAO.getAll();
            logger.info("All accounts:");
            for (Account acc : allAccounts) {
                logger.info(acc.toString());
            }
            logger.info("============================================");
            try{Account accountToDelete = accountDAO.getById(19);
            if (accountToDelete != null) {
                accountDAO.delete(accountToDelete);
            } 
            }catch(NoAccountExist e)
            {
                logger.info(e.getMessage());
            }

            logger.info("============================================");
            List<Account> allAccountss = accountDAO.getAll();
            logger.info("All accounts:");
            for (Account acc : allAccountss) {
                logger.info(acc.toString());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }//End Of-Testing Account


        //Start of testing driver

        try(Connection connection = DatabaseConnection.getConnection())
        {
            DriverDAO driverDAO = new DriverDAO(connection);
            //Test Create method
            for (int i = 1; i <= 15; i++) {
            RideDriver newDriver = new RideDriver();
            newDriver.setName("Name: " + i);
            newDriver.setEmail("Email: " + i);
            newDriver.setPhone(i);
            newDriver.setAccountID(i);
            try {
                driverDAO.create(newDriver);
                logger.info("New driver created: " + newDriver.getName());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    

    //Start test passenger
    try(Connection connection = DatabaseConnection.getConnection())
        {
            PassengerDAO passengerDAO = new PassengerDAO(connection);
            //Test Create method
            for (int i = 1; i <= 15; i++) {
            Passenger newPassenger = new Passenger();
            newPassenger.setName("Name: " + i);
            newPassenger.setEmail("Email: " + i);
            newPassenger.setPhone(i);
            newPassenger.setAccountID(i);
            try {
                passengerDAO.create(newPassenger);
                logger.info("New driver created: " + newPassenger.getName());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        


         //Start test car
    try(Connection connection = DatabaseConnection.getConnection())
        {
            CarDAO carDAO = new CarDAO(connection);
            //Test Create method
            for (int i = 1; i <= 15; i++) {
            Car newCar = new Car();
            newCar.setLicensePlate("Plate: " + i);
            newCar.setMake("Make: " + i);
            newCar.setModel("Model: " + i);
            newCar.setYear(i);
            newCar.setDriverID(i);
            try {
                carDAO.create(newCar);
                logger.info("New car added for driver: " + newCar.getDriverID());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        //Start test driver rating
    try(Connection connection = DatabaseConnection.getConnection())
        {
            RatingDAO ratingDAO = new RatingDAO(connection);
            //Test Create method
            for (int i = 1; i <=  5; i++) {
            Rating newRating = new Rating();
            newRating.setComment("Bad: " + i);
            newRating.setRating( i);
            newRating.setDriverID(i);
            newRating.setPassengerID((i +4) -1);
            
            try {
                ratingDAO.create(newRating);
                logger.info("New rating added for driver: " + newRating.getDriverID());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }

            for (int i = 1; i <=  5; i++) {
            Rating newRating = new Rating();
            newRating.setComment("Good " + i);
            newRating.setRating( i);
            newRating.setDriverID(i);
            newRating.setPassengerID((i +4) -2);
            
            try {
                ratingDAO.create(newRating);
                logger.info("New rating added for driver: " + newRating.getDriverID());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }
            //End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        //Start test location city and country
    try(Connection connection = DatabaseConnection.getConnection())
        {
            LocationDAO locationDAO = new LocationDAO(connection);
            CityDAO cityDao = new CityDAO(connection);
            CountryDAO countryDAO = new CountryDAO(connection);
            //Test Create method
            for (int i = 1; i <= 15; i++) {
            Location newLocation = new Location();
            City newCity = new City();
            Country newCountry = new Country();
            
            //Location
            newLocation.setNameAddress("Street: " + i);
            
            //City
            newCity.setCityName("Name: " + i);
            newCity.setLocation(i);

            //Country
            newCountry.setCountryName("Name " + i );
            newCountry.setCityID(i);
            try {
                locationDAO.create(newLocation);
                cityDao.create(newCity);
                countryDAO.create(newCountry);
                logger.info("Created new location" + newLocation.getNameAddress() + " with city " 
                                    + newCity.getCityName() + " and country " + newCountry.getCountryName());
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        // start test rideservice
        try(Connection connection = DatabaseConnection.getConnection())
        {
            RideServiceDAO rideServiceDAO = new RideServiceDAO(connection);

            //Test create method
            for (int i = 1; i <=5; i++)
            {
                RideService newService = new RideService();

                newService.setCost(i+i);
                newService.setDistant(i);
                newService.setPickUpTime(100+i);
                newService.setDropOffTime(100+50+i);
                newService.setPassengerID(i+i);
                newService.setDriverID(i+1);
                newService.setPickUpLocationID(i);
                newService.setDropOffLocationID(i);

                //create payment and promotion
                try {
                    rideServiceDAO.create(newService);
                    logger.info("New service created.");
                } catch (DataExistException e) {
                    logger.info(e.getMessage());
            } 
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        //Start test payment, payment has promo, and promotion
        try(Connection connection = DatabaseConnection.getConnection())
        {
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            //Test Create method
            for (int i = 1; i <= 5; i++) {
            Payment newPayment = new Payment();

            //Payment
            newPayment.setAmount(i);
            newPayment.setDiscountAmount(i);
            newPayment.setDate("Jan " + i);
            newPayment.setRideService(i);

            //create payment 
            try {
                paymentDAO.create(newPayment);            
                logger.info("Payment created.");
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            }          
           
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        try(Connection connection = DatabaseConnection.getConnection())
        {

            PromotionDAO promotionDAO = new PromotionDAO(connection);
            //Test Create method
            for (int i = 1; i <= 5; i++) {
            Promotion newPromotion = new Promotion();

            //Promotion
            newPromotion.setPromotionCode("Code:  " + i );

            //create payment and promotion
            try {
                promotionDAO.create(newPromotion);
                logger.info("Promotion created");
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
           
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        try(Connection connection = DatabaseConnection.getConnection())
        {
     
            PaymentHasPromotionDAO hasPromotionDao = new PaymentHasPromotionDAO(connection);

            //Test Create method
            for (int i = 1; i <= 5; i++) {
   
            PaymentHasPromotion newHasPromo = new PaymentHasPromotion();

            //Has promo
            newHasPromo.setPaymentID(i);
            newHasPromo.setPromotionID(i);

            //create payment has promotion
            try {
                hasPromotionDao.create(newHasPromo);
                logger.info("Payment has promotion created");
            } catch (DataExistException e) {
                logger.info(e.getMessage());
            } 
            
            }//End-Of-Test Create Method
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
