package com.project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.Exception.DataExistException;
import com.project.Exception.NoAccountExist;
import com.project.interfaces.GenericDAO;
import com.project.models.Location;


public class LocationDAO implements GenericDAO<Location,Integer>{

    final Logger logger = LogManager.getLogger(LocationDAO.class);
    private Connection connection;

    public LocationDAO(Connection connection)
    {
        this.connection = connection;
    }
    
     @Override
    public Location getById(Integer id) throws NoAccountExist{
        
        Location location = null;
        String query = "SELECT * FROM Location WHERE Loc_ID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               location = extractLocationFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (location == null) 
        {
        throw new NoAccountExist();
        }
        return location;
    }

    @Override
    public List<Location> getAll()
    {
        List<Location> locations = new ArrayList<>();

        String query = "SELECT * FROM Location";

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
            {
                while (resultSet.next()) {
                Location location = extractLocationFromResultSet(resultSet);
                locations.add(location);
            }
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
            
            return locations;
    }

    @Override
    public void delete(Location location) {
        String query = "DELETE FROM Location WHERE Loc_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, location.getLocationID());
            statement.executeUpdate();
            logger.info("Location deleted: " +"\n"+ location);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void create(Location location) throws DataExistException 
    {
        if (isNameAddressExists(location.getNameAddress())) 
        {
            throw new DataExistException("Failed to add address: " + location.getNameAddress() + " is already in database.");
        }
        String query = "INSERT INTO Location (Name_Address) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, location.getNameAddress());
                
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(Location location)
    {
        Location currentLocation = new Location();
        //to retrieve current account info
        try
        {
            currentLocation = getById(location.getLocationID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
         //compare if the  new name address already exist
        if (isNameAddressExists(location.getNameAddress())) 
        {
            //case1: if it matches the current username
            if(currentLocation.getNameAddress().equals(location.getNameAddress()))
            {
                logger.info("Name address can't be the same as current address. No update performed on address.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("Address already in database.");
                logger.info("Update Failed.");
                return;
            }
            
        }
  

        String query = "UPDATE Location SET Name_Address = ? WHERE Loc_ID = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,location.getNameAddress());
            statement.setInt(2,location.getLocationID());

            statement.executeUpdate();
            logger.info("Update Success");
            logger.info("Location Info: " +"\n"+ location);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    private Location extractLocationFromResultSet(ResultSet resultSet) throws SQLException {
        int locationID = resultSet.getInt("Loc_ID");
        String nameAddress = resultSet.getString("Name_Address");
        
        return new Location(locationID,nameAddress);
    }

    private boolean isNameAddressExists(String nameAddress) 
    {
        String query = "SELECT COUNT(*) FROM Location WHERE Name_Address = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nameAddress);
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
