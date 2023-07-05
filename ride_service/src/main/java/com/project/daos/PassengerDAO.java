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
import com.project.models.Passenger;

public class PassengerDAO implements GenericDAO<Passenger,Integer>{
    
    final Logger logger = LogManager.getLogger(PassengerDAO.class);

    private Connection connection;

    public PassengerDAO(Connection connection) 
    {
        this.connection = connection;
    }

    @Override
    public Passenger getById(Integer id) throws NoAccountExist{
        
        Passenger passenger = null;
        String query = "SELECT * FROM Passenger WHERE PassengerID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passenger =  extractPassengerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (passenger == null) 
        {
        throw new NoAccountExist();
        }
        return passenger;
    }

    @Override
    public void create(Passenger passenger) throws DataExistException 
    {
        if (isAccountIDExists(passenger.getAccountID())) 
        {
            throw new DataExistException("Failed. Account id: " + passenger.getAccountID() + " is already taken.");
        }
        if (isEmailExists(passenger.getEmail())) 
        {
            throw new DataExistException("Failed. Email: " + passenger.getEmail() + " already taken.");
        }
        if (isPhoneExists(passenger.getPhone())) 
        {
            throw new DataExistException("Failed. Phone: " + passenger.getPhone() + " already taken.");
        }

        String query = "INSERT INTO Passenger (Name, Email, Phone, Account_AccountID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, passenger.getName());
                statement.setString(2, passenger.getEmail());
                statement.setLong(3, passenger.getPhone());
                statement.setInt(4, passenger.getAccountID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(Passenger passenger) {
        //Temp Passenger
       Passenger currentPassenger = new Passenger();
        //retrieve info
        try{
            currentPassenger = getById(passenger.getPassengerID());
        } catch (NoAccountExist e)
        {
            System.out.println(e.getMessage());
        }
        //Case1: AccountID cant be modified       
        if(currentPassenger.getAccountID()!=(passenger.getAccountID()))
        {
            System.out.println("Account's ID can't be modified. No update performed.");
            return;
        }  
        //Case2: if all the info the user want to update is the same as current user's info
        if (currentPassenger.getName().equals(passenger.getName()) && currentPassenger.getEmail().equals(passenger.getEmail())
                &&currentPassenger.getPhone()==passenger.getPhone()  &&currentPassenger.getAccountID()==passenger.getAccountID()) 
        {
            System.out.println("Update informations cant be the same as current information. No update performed.");
            return;
        }      
        //Case3: if email exist and taken
        if (isEmailExists(passenger.getEmail())) 
        {
            //if it matches the current email
            if(currentPassenger.getEmail().equals(passenger.getEmail()))
            {
                System.out.println("Email can't be the same as current email. No update performed on email.");

            }
            //if username exist but other user already taken
            else
            {
                System.out.println("Email already taken.");
                System.out.println("Update Failed.");
                return;
            }   
        }

        //Case3: if phone exist and taken
        if (isEmailExists(passenger.getEmail())) 
        {
            //if it matches the current email
            if(currentPassenger.getPhone()==(passenger.getPhone()))
            {
                System.out.println("Phone number can't be the same as current phone. No update performed on phone.");

            }
            //if username exist but other user already taken
            else
            {
                System.out.println("Phone already taken.");
                System.out.println("Update Failed.");
                return;
            }   
        }
        String query = "UPDATE Passenger SET Name = ?, Email = ?, Phone = ? WHERE PassengerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, passenger.getName());
            statement.setString(2, passenger.getEmail());
            statement.setLong(3, passenger.getPhone());
            statement.setInt(4, passenger.getPassengerID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Passenger passenger) {
        String query = "DELETE FROM Passenger WHERE PassengerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, passenger.getPassengerID());
            statement.executeUpdate();
            System.out.println("Passenger deleted: " +"\n"+ passenger);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Passenger> getAll() {
        List<Passenger> passengers = new ArrayList<>();
        String query = "SELECT * FROM Passenger";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = extractPassengerFromResultSet(resultSet);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    private Passenger extractPassengerFromResultSet(ResultSet resultSet) throws SQLException {
        int passengerID = resultSet.getInt("PassengerID");
        String name = resultSet.getString("Name");
        String email = resultSet.getString("Email");
        int phone = resultSet.getInt("Phone");
        int accountID = resultSet.getInt("Account_AccountID");
        return new Passenger(passengerID, name, email, phone, accountID);
    }

    // Method to check if an accountID exist already exists in the accounts
    private boolean isAccountIDExists(int accountID) 
    {
        String query = "SELECT COUNT(*) FROM Passenger WHERE Account_AccountID = ?";

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
        String query = "SELECT COUNT(*) FROM Passenger WHERE Email = ?";

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
        String query = "SELECT COUNT(*) FROM Passenger WHERE Phone = ?";

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
