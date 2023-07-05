package com.project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.Exception.DataExistException;
import com.project.Exception.NoAccountExist;
import com.project.interfaces.GenericDAO;
import com.project.models.RideDriver;

public class DriverDAO implements GenericDAO<RideDriver,Integer>{
    
    final Logger logger = LogManager.getLogger(DriverDAO.class);
    private Connection connection;

    public DriverDAO(Connection connection) 
    {
        this.connection = connection;
    }

    @Override
    public RideDriver getById(Integer id) throws NoAccountExist{
        
        RideDriver driver = null;
        String query = "SELECT * FROM Driver WHERE DriverID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                driver =  extractDriverFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (driver == null) 
        {
        throw new NoAccountExist();
        }
        return driver;
    }

    @Override
    public void create(RideDriver driver) throws DataExistException 
    {
        if (isAccountIDExists(driver.getAccountID())) 
        {
            throw new DataExistException("Failed. Account id: " + driver.getAccountID() + " is already taken.");
        }
        if (isEmailExists(driver.getEmail())) 
        {
            throw new DataExistException("Failed. Email: " + driver.getEmail() + " already taken.");
        }
        if (isPhoneExists(driver.getPhone())) 
        {
            throw new DataExistException("Failed. Phone: " + driver.getPhone() + " already taken.");
        }

        String query = "INSERT INTO Driver (Name, Email, Phone, Account_AccountID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, driver.getName());
                statement.setString(2, driver.getEmail());
                statement.setLong(3, driver.getPhone());
                statement.setInt(4, driver.getAccountID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(RideDriver driver) {
        //Temp driver
        RideDriver currentDriver = new RideDriver();
        //retrieve info
        try{
            currentDriver = getById(driver.getDriverID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        //Case1: AccountID cant be modified       
        if(currentDriver.getAccountID()!=(driver.getAccountID()))
        {
            logger.info("Account's ID can't be modified. No update performed.");
            return;
        }  
        //Case2: if all the info the user want to update is the same as current user's info
        if (currentDriver.getName().equals(driver.getName()) && currentDriver.getEmail().equals(driver.getEmail())
                &&currentDriver.getPhone()==driver.getPhone()  &&currentDriver.getAccountID()==driver.getAccountID()) 
        {
            logger.info("Update informations cant be the same as current information. No update performed.");
            return;
        }      
        //Case3: if email exist and taken
        if (isEmailExists(driver.getEmail())) 
        {
            //if it matches the current email
            if(currentDriver.getEmail().equals(driver.getEmail()))
            {
                logger.info("Email can't be the same as current email. No update performed on email.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("Email already taken.");
                logger.info("Update Failed.");
                return;
            }   
        }

        //Case3: if phone exist and taken
        if (isEmailExists(driver.getEmail())) 
        {
            //if it matches the current email
            if(currentDriver.getPhone()==(driver.getPhone()))
            {
                logger.info("Phone number can't be the same as current phone. No update performed on phone.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("Phone already taken.");
                logger.info("Update Failed.");
                return;
            }   
        }
        String query = "UPDATE Driver SET Name = ?, Email = ?, Phone = ? WHERE DriverID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getEmail());
            statement.setLong(3, driver.getPhone());
            statement.setInt(4, driver.getDriverID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(RideDriver driver) {
        String query = "DELETE FROM Driver WHERE DriverID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, driver.getDriverID());
            statement.executeUpdate();
            logger.info("Driver deleted: " +"\n"+ driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RideDriver> getAll() {
        List<RideDriver> drivers = new ArrayList<>();
        String query = "SELECT * FROM Driver";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RideDriver driver = extractDriverFromResultSet(resultSet);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    private RideDriver extractDriverFromResultSet(ResultSet resultSet) throws SQLException {
        int driverID = resultSet.getInt("DriverID");
        String name = resultSet.getString("Name");
        String email = resultSet.getString("Email");
        int phone = resultSet.getInt("Phone");
        int accountID = resultSet.getInt("Account_AccountID");
        return new RideDriver(driverID, name, email, phone, accountID);
    }

    // Method to check if an accountID exist already exists in the accounts
    private boolean isAccountIDExists(int accountID) 
    {
        String query = "SELECT COUNT(*) FROM Driver WHERE Account_AccountID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method to check if an email already exist
    private boolean isEmailExists(String email) 
    {
        String query = "SELECT COUNT(*) FROM Driver WHERE Email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method to check if an accountID exist already exists in the accounts
    private boolean isPhoneExists(int phoneNum) 
    {
        String query = "SELECT COUNT(*) FROM Driver WHERE Phone = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
