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
import com.project.models.RideService;

public class RideServiceDAO implements GenericDAO<RideService,Integer>{
    
    final Logger logger = LogManager.getLogger(RideServiceDAO.class);

    private Connection connection;

    public RideServiceDAO(Connection connection) 
    {
        this.connection = connection;
    }

    @Override
    public RideService getById(Integer id) throws NoAccountExist{
        
        RideService ride = null;
        String query = "SELECT * FROM RideService WHERE RideID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ride =  extractRideInfoFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ride == null) 
        {
        throw new NoAccountExist();
        }
        return ride;
    }

     @Override
    public void delete(RideService ride) {
        String query = "DELETE FROM RideService WHERE RideID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ride.getRideID());
            statement.executeUpdate();
            logger.info("Ride deleted: " +"\n"+ ride);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RideService> getAll() {
        List<RideService> rides = new ArrayList<>();
        String query = "SELECT * FROM Passenger";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RideService ride = extractRideInfoFromResultSet(resultSet);
                rides.add(ride);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rides;
    }

    @Override
    public void create(RideService ride) throws DataExistException 
    {

        String query = "INSERT INTO RideService (Cost, Distance, PickUp_time,DropOff_time,Passenger_PassengerID,Driver_DriverID, PickUpID,DropOffID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, ride.getCost());
                statement.setInt(2, ride.getDistant());
                statement.setInt(3, ride.getPickUpTime());
                statement.setInt(4, ride.getDropOffTime());
                statement.setInt(5, ride.getPassengerID());
                statement.setInt(6, ride.getDriverID());
                statement.setInt(7, ride.getPickUpID());
                statement.setInt(8, ride.getDropOffID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(RideService ride) {
        //Temp Passenger
       RideService currentRide = new RideService();
        //retrieve info
        try{
            currentRide = getById(ride.getRideID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        //Case1: PassengerID cant be modified       
        if(currentRide.getPassengerID()!=(ride.getPassengerID()))
        {
            logger.info("Passenger's ID can't be modified. No update performed.");
            return;
        }  
        
        //Case3: check if driver with the same id and same pickup time already exist in database
        // if that the case cant update
        if (isRideExists(ride.getDriverID(),ride.getPickUpTime())) 
        {
            //if it is in database but with didfferent drive at the same time can't update since that driver is already taken
            if(currentRide.getDriverID() != ride.getDriverID())
            {
               logger.info("Driver already taken at this time.");
                logger.info("Update Failed.");
                return;

            } 
        }

        String query = "UPDATE RideService SET Cost = ?, Distance = ?, PickUp_time = ?, DropOff_time = ?,Passenger_PassengerID = ?, Driver_DriverID = ?, PickUpID = ?, DropOffID = ? WHERE RideID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ride.getCost());
            statement.setInt(2, ride.getDistant());
            statement.setInt(3, ride.getPickUpTime());
            statement.setInt(4, ride.getDropOffTime());
            statement.setInt(5, ride.getPassengerID());
            statement.setInt(6, ride.getDriverID());
            statement.setInt(7, ride.getPickUpID());
            statement.setInt(8, ride.getDropOffID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isRideExists(int driverID, int pickUpTime) 
    {
        String query = "SELECT COUNT(*) FROM RideService WHERE Driver_DriverID = ? AND PickUp_time = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, driverID);
            statement.setInt(2, pickUpTime);
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


    private RideService extractRideInfoFromResultSet(ResultSet resultSet) throws SQLException {
        int rideID = resultSet.getInt("RideID");
        int cost = resultSet.getInt("Cost");
        int distant = resultSet.getInt("Distance");
        int pickUpTime = resultSet.getInt("PickUp_time");
        int dropOffTime = resultSet.getInt("DropOff_time");
        int driverID = resultSet.getInt("Driver_DriverID");
        int passengerID = resultSet.getInt("Passenger_PassengerID");
        int pickUpID = resultSet.getInt("PickUpID");
        int dropOffID = resultSet.getInt("DropOffID");
        return new RideService(rideID, cost, distant, pickUpTime, dropOffTime, passengerID, driverID, pickUpID, dropOffID);
    }

}
