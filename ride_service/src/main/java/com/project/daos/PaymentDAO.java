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
import com.project.models.Payment;

public class PaymentDAO implements GenericDAO<Payment,Integer>{
    
    final Logger logger = LogManager.getLogger(PaymentDAO.class);
    
    private Connection connection;

    public PaymentDAO(Connection connection) 
    {
        this.connection = connection;
    }
    
    @Override
    public Payment getById(Integer id) throws NoAccountExist{
        
        Payment payment = null;
        String query = "SELECT * FROM Payment WHERE PaymentID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment =  extractPaymentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (payment == null) 
        {
        throw new NoAccountExist();
        }
        return payment;
    }

    @Override
    public void create(Payment payment) throws DataExistException 
    {   //can't have 2 different payment for the same ride
        if (isRideIDExists(payment.getRideServiceID())) 
        {
            throw new DataExistException("Failed. Payment id: " + payment.getRideServiceID() + " is already in the database.");
        }
        
        String query = "INSERT INTO Payment (Amount, DiscountAmount, Date, RideService_RideID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, payment.getAmount());
                statement.setDouble(2, payment.getDiscountAmount());
                statement.setString(3, payment.getDate());
                statement.setInt(4, payment.getRideServiceID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(Payment payment) {
        //Temp Payment
       Payment currentPayment = new Payment();
        //retrieve info
        try{
            currentPayment = getById(payment.getPaymentID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        //Case1: rideID cant be modified       
        if(currentPayment.getRideServiceID()!=(payment.getRideServiceID()))
        {
            logger.info("Ride's ID can't be modified. No update performed.");
            return;
        }  
        
        String query = "UPDATE Payment SET Amount = ?, Discount = ?, Date = ? WHERE PaymentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, payment.getAmount());
            statement.setDouble(2, payment.getDiscountAmount());
            statement.setString(3, payment.getDate());
            statement.setInt(4, payment.getPaymentID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Payment payment) {
        String query = "DELETE FROM Payment WHERE PaymentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payment.getPaymentID());
            statement.executeUpdate();
            logger.info("Payment deleted: " +"\n"+ payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payment payment = extractPaymentFromResultSet(resultSet);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    private Payment extractPaymentFromResultSet(ResultSet resultSet) throws SQLException {
        int paymentID = resultSet.getInt("PaymentID");
        double amount = resultSet.getDouble("Amount");
        double discount = resultSet.getDouble("DiscountAmount");
        String date = resultSet.getString("Date");
        int rideID = resultSet.getInt("RideService_RideID");
        return new Payment(paymentID, amount, discount, date, rideID);
    }

    private boolean isRideIDExists(int rideID) 
    {
        String query = "SELECT COUNT(*) FROM Payment WHERE RideService_RideID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rideID);
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
