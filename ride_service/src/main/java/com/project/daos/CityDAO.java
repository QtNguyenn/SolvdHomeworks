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
import com.project.models.City;

public class CityDAO implements GenericDAO<City,Integer>{

    final Logger logger = LogManager.getLogger(CityDAO.class);
    private Connection connection;

    public CityDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public City getById(Integer id) throws NoAccountExist{
        
        City city= null;
        String query = "SELECT * FROM City WHERE idCity = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               city = extractCityFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (city == null) 
        {
        throw new NoAccountExist();
        }
        return city;  
    }

    @Override
    public List<City> getAll()
    {
        List<City> citys = new ArrayList<>();

        String query = "SELECT * FROM City";

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
            {
                while (resultSet.next()) {
                City city = extractCityFromResultSet(resultSet);
                citys.add(city);
            }
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
            
            return citys;
    }

    @Override
    public void delete(City city) {
        String query = "DELETE FROM City WHERE Loc_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, city.getCityID());
            statement.executeUpdate();
            logger.info("City deleted: " +"\n"+ city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(City city) throws DataExistException 
    {
        if (isLocationExists(city.getLocationID())) 
        {
            throw new DataExistException("Failed to add city: " + city.getCityName() + " is already in database.");
        }
        String query = "INSERT INTO City (Name, Location_Loc_ID) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getCityName());
                statement.setInt(2, city.getLocationID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(City city)
    {
        City currentCity = new City();
        //to retrieve current account info
        try
        {
            currentCity = getById(city.getCityID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }

        if(currentCity.getLocationID()!=(city.getLocationID()))
        {
            logger.info("Location's ID can't be modified. No update performed.");
            return;
        }  
        
         //compare if the  new name address already exist
        if (isCityExists(city.getCityName())) 
        {
            //case1: if it matches the current username
            if(currentCity.getCityName().equals(city.getCityName()))
            {
                logger.info("City's name can't be the same as current city. No update performed on city.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("City already in database.");
                logger.info("Update Failed.");
                return;
            }
            
        }
  

        String query = "UPDATE City SET Name = ?, Location_Loc_ID =? WHERE idCity = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,city.getCityName());
            statement.setInt(2,city.getLocationID());
            statement.setInt(3,city.getCityID());

            statement.executeUpdate();
            logger.info("Update Success");
            logger.info("City Info: " +"\n"+ city);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private City extractCityFromResultSet(ResultSet resultSet) throws SQLException {
        int cityID = resultSet.getInt("idCity");
        String cityName = resultSet.getString("Name");
         int locationID = resultSet.getInt("Location_Loc_ID");
        return new City(cityID,cityName,locationID);
    }

    private boolean isCityExists(String cityName) 
    {
        String query = "SELECT COUNT(*) FROM City WHERE Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cityName);
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

     private boolean isLocationExists(int locationID) 
    {
        String query = "SELECT COUNT(*) FROM City WHERE Location_Loc_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, locationID);
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
