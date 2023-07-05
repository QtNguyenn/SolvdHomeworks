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
import com.project.models.Car;

public class CarDAO implements GenericDAO<Car,Integer>{

    final Logger logger = LogManager.getLogger(CarDAO.class);

    private Connection connection;

    public CarDAO(Connection connection)
    {
        this.connection = connection;
    }
    @Override
    public Car getById(Integer id) throws NoAccountExist{
        
        Car car = null;
        String query = "SELECT * FROM Car WHERE CarID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car =  extractCarFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (car == null) 
        {
        throw new NoAccountExist();
        }
        return car;
    }

    @Override
    public void create(Car car) throws DataExistException 
    {
        if (isLicensePlateExists(car.getLicensePlate())) 
        {
            throw new DataExistException("Failed. License plate: " + car.getLicensePlate() + " is already taken.");
        }

        String query = "INSERT INTO Car (LicensePlate, Make, Model, Year,Driver_DriverID) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, car.getLicensePlate());
                statement.setString(2, car.getMake());
                statement.setString(3, car.getModel());
                statement.setInt(4, car.getYear());
                statement.setInt(5, car.getDriverID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(Car car) {
        //Temp Car
      Car currentCar = new Car();
        //retrieve info
        try{
            currentCar = getById(car.getCarID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        
        //Case2: if all the info the user want to update is the same as current user's info
        if (currentCar.getLicensePlate().equals(car.getLicensePlate()) && currentCar.getMake().equals(car.getMake())
                && currentCar.getModel().equals(car.getModel())  &&currentCar.getYear()== car.getYear() 
                && currentCar.getDriverID() == car.getDriverID()) 
        {
            logger.info("Update informations cant be the same as current information. No update performed.");
            return;
        }      
        //Case3: if plate exist and taken
        if (isLicensePlateExists(car.getLicensePlate())) 
        {
            //if it matches the current plate
            if(currentCar.getLicensePlate().equals(car.getLicensePlate()))
            {
                logger.info("Plate can't be the same as current plate. No update performed on plate.");

            }
            //if plate exist but other car already taken
            else
            {
                logger.info("License already taken.");
                logger.info("Update Failed.");
                return;
            }   
        }

        
        String query = "UPDATE Car SET LicensePlate = ? , Make = ? , Model = ? , Year = ? , Driver_DriverID = ? WHERE CarID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, car.getLicensePlate());
                statement.setString(2, car.getMake());
                statement.setString(3, car.getModel());
                statement.setInt(4, car.getYear());
                statement.setInt(5, car.getDriverID());
                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Car car) 
    {
        String query = "DELETE FROM Car WHERE CarID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) 
        {
            statement.setInt(1, car.getCarID());
            statement.executeUpdate();
            logger.info("Car deleted: " +"\n"+ car);
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }    

        @Override
        public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Car";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = extractCarFromResultSet(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
    
    private Car extractCarFromResultSet(ResultSet resultSet) throws SQLException {
        int carID = resultSet.getInt("CarID");
        String licensePlate = resultSet.getString("LicensePlate");
        String make = resultSet.getString("Make");
        String model = resultSet.getString("Model");
        int year = resultSet.getInt("Year");
        int driverID = resultSet.getInt("Driver_DriverID");
        return new Car(carID, licensePlate, make, model,year,driverID);
    }

    private boolean isLicensePlateExists(String licensePlate) 
    {
        String query = "SELECT COUNT(*) FROM Car WHERE LicensePlate = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, licensePlate);
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
