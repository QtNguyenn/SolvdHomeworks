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
import com.project.models.Country;

public class CountryDAO implements GenericDAO<Country,Integer> {
    
    final Logger logger = LogManager.getLogger(CountryDAO.class);
    
    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Country getById(Integer id) throws NoAccountExist{
        
        Country country= null;
        String query = "SELECT * FROM Country WHERE idCountry = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               country = extractCountryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (country == null) 
        {
        throw new NoAccountExist();
        }
        return country;  
    }

    @Override
    public List<Country> getAll()
    {
        List<Country> countries = new ArrayList<>();

        String query = "SELECT * FROM Country";

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
            {
                while (resultSet.next()) {
                Country country = extractCountryFromResultSet(resultSet);
                countries.add(country);
            }
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
            
            return countries;
    }

    @Override
    public void delete(Country country) {
        String query = "DELETE FROM Country WHERE idCountry = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, country.getCountryID());
            statement.executeUpdate();
            logger.info("Country deleted: " +"\n"+ country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Country country) throws DataExistException 
    {
        if (isCountryExists(country.getCountryName())) 
        {
            throw new DataExistException("Failed to add address: " + country.getCountryName() + " is already in database.");
        }
        String query = "INSERT INTO Country (Name, City_idCity) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getCountryName());
                statement.setInt(2, country.getCityID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(Country country)
    {
        Country currentCountry = new Country();
        //to retrieve current account info
        try
        {
            currentCountry = getById(country.getCountryID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        
         //compare if the  new name address already exist
        if (isCountryExists(country.getCountryName())) 
        {
            //case1: if it matches the current username
            if(currentCountry.getCountryName().equals(country.getCountryName()))
            {
                logger.info("Country's name can't be the same as current country. No update performed on country.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("Country already in database.");
                logger.info("Update Failed.");
                return;
            }
            
        }
  

        String query = "UPDATE Country SET Name = ?, City_idCity =? WHERE idCountry = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,country.getCountryName());
            statement.setInt(2,country.getCityID());
            statement.setInt(3,country.getCountryID());

            statement.executeUpdate();
            logger.info("Update Success");
            logger.info("Country Info: " +"\n"+ country);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private Country extractCountryFromResultSet(ResultSet resultSet) throws SQLException {
        int countryID = resultSet.getInt("idCountry");
        String countryName = resultSet.getString("Name");
         int locationID = resultSet.getInt("Location_Loc_ID");
        return new Country(countryID,countryName,locationID);
    }

    private boolean isCountryExists(String countryName) 
    {
        String query = "SELECT COUNT(*) FROM Country WHERE Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, countryName);
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
