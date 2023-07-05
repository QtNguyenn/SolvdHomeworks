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
import com.project.models.Rating;

public class RatingDAO implements GenericDAO<Rating,Integer>{

    final Logger logger = LogManager.getLogger(RatingDAO.class);

    private Connection connection;

    public RatingDAO(Connection connection) 
    {
        this.connection = connection;
    }

    @Override
    public Rating getById(Integer id) throws NoAccountExist{
        
        Rating rating = null;
        String query = "SELECT * FROM DriverRating WHERE RatingID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rating =  extractRatingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rating == null) 
        {
        throw new NoAccountExist();
        }
        return rating;
    }

    @Override
    public void create(Rating rating) throws DataExistException 
    {
        if (isRatingExists(rating.getDriverID(),rating.getPassengerID())) 
        {
            throw new DataExistException("Failed. Passenger id " + rating.getPassengerID() + " can't rate driver with id "+ rating.getDriverID() +" twice.");
        }

        String query = "INSERT INTO DriverRating (Comment, DriverRating, Driver_DriverID,Passenger_PassengerID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, rating.getComment());
                statement.setInt(2, rating.getRating());
                statement.setInt(3, rating.getDriverID());
                statement.setInt(4, rating.getPassengerID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    @Override
    public void update(Rating rating) {
        //Temp DriverRating
       Rating currentRating = new Rating();
        //retrieve info
        try{
            currentRating = getById(rating.getRatingID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        //Case1: PassengerID cant be modified       
        if(currentRating.getPassengerID()!=(rating.getPassengerID()))
        {
            logger.info("Passenger's ID can't be modified. No update performed.");
            return;
        }  
        //Case2: if all the info the user want to update is the same as current user's info
        if (currentRating.getComment().equals(rating.getComment()) && currentRating.getRating()== rating.getRating()
                &&currentRating.getDriverID()==rating.getDriverID()  &&currentRating.getPassengerID()==rating.getPassengerID()) 
        {
            logger.info("Update informations cant be the same as current information. No update performed.");
            return;
        }      
        //Case3: if the updated exist and taken
        if (isRatingExists(rating.getDriverID(),rating.getPassengerID())) 
        {
            //if the updated driverID matches an id in database
            if(currentRating.getDriverID() != rating.getDriverID())
            {
                logger.info("Already rate this driver.");
                logger.info("Update Failed.");
                return;
            }
            
        }

        String query = "UPDATE DriverRating SET Comment = ?, Rating = ?, Driver_DriverID = ?, Passenger_PassengerID = ? WHERE RatingID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, rating.getComment());
            statement.setInt(2, rating.getRating());
            statement.setInt(3, rating.getDriverID());
            statement.setInt(4, rating.getPassengerID());
            statement.setInt(5, rating.getRatingID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Rating> getAll() {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT * FROM DriverRating";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Rating rating = extractRatingFromResultSet(resultSet);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }
     @Override
    public void delete(Rating rating) {
        String query = "DELETE FROM DriverRating WHERE RatingID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rating.getRatingID());
            statement.executeUpdate();
            logger.info("Rating deleted: " +"\n"+ rating);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Rating extractRatingFromResultSet(ResultSet resultSet) throws SQLException 
    {
        int ratingID = resultSet.getInt("RatingID");
        String comment = resultSet.getString("Comment");
        int driverRating = resultSet.getInt("DriverRating");
        int driverID = resultSet.getInt("Driver_DriverID");
        int passengerID = resultSet.getInt("Passenger_PassengerID");
        return new Rating(ratingID,comment,driverRating,driverID,passengerID);
    }

    private boolean isRatingExists(int driverID, int passengerID) 
    {
        String query = "SELECT COUNT(*) FROM DriverRating WHERE Driver_DriverID = ? AND Passenger_PassengerID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, driverID);
            statement.setInt(2, passengerID);
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
