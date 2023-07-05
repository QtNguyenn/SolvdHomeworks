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
import com.project.models.PaymentHasPromotion;


public class PaymentHasPromotionDAO implements GenericDAO<PaymentHasPromotion,Integer> {
    
    final Logger logger = LogManager.getLogger(PaymentHasPromotionDAO.class);
    
    private Connection connection;

    public PaymentHasPromotionDAO(Connection connection) 
    {
        this.connection = connection;
    }
    
     @Override
    public PaymentHasPromotion getById(Integer id) throws NoAccountExist{
        
        PaymentHasPromotion paymentHasPromotion = null;
        String query = "SELECT * FROM Payment_Has_Promotion WHERE Payment_PaymentID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                paymentHasPromotion =  extractInformationFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (paymentHasPromotion == null) 
        {
        throw new NoAccountExist();
        }
        return paymentHasPromotion;
    }

    @Override
    public void create(PaymentHasPromotion paymentHasPromotion) throws DataExistException 
    {   //can't have 2 same payment with same promotion
        if (isPaymentHasPromoExists(paymentHasPromotion.getPaymentID(), paymentHasPromotion.getPromotionID()) )
        {
            throw new DataExistException("Failed. Payment id:" + paymentHasPromotion.getPaymentID() +
            " with promotion id: " + paymentHasPromotion.getPromotionID() + " is already in the database.");
        }
        
        String query = "INSERT INTO Payment_Has_Promotion (Payment_PaymentID, Promotion_PromotionID) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, paymentHasPromotion.getPaymentID());
                statement.setInt(2, paymentHasPromotion.getPromotionID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    @Override
    public void update(PaymentHasPromotion paymentHasPromotion) throws DataExistException{
        
        //can't have 2 same payment with same promotion
        if (isPaymentHasPromoExists(paymentHasPromotion.getPaymentID(), paymentHasPromotion.getPromotionID()) )
        {
            throw new DataExistException("Failed. Payment id:" + paymentHasPromotion.getPaymentID() +
            " with promotion id: " + paymentHasPromotion.getPromotionID() + " is already in the database.");
        }  
         
        String query = "UPDATE Payment_Has_Promotion SET Promotion_PromotionID = ? WHERE Payment_PaymentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, paymentHasPromotion.getPromotionID());
            statement.setInt(1, paymentHasPromotion.getPaymentID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }

    @Override
    public void delete(PaymentHasPromotion paymentHasPromotion) {
        String query = "DELETE FROM Payment_Has_Promotion WHERE Promotion_PromotionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, paymentHasPromotion.getPromotionID());
            statement.executeUpdate();
            logger.info("Deleted: " +"\n"+ paymentHasPromotion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PaymentHasPromotion> getAll() {
        List<PaymentHasPromotion> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment_Has_Promotion";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PaymentHasPromotion payment = extractInformationFromResultSet(resultSet);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

     private PaymentHasPromotion extractInformationFromResultSet(ResultSet resultSet) throws SQLException {
        int promotionID = resultSet.getInt("Promotion_PromotionID");
        int paymentID = resultSet.getInt("Payment_PaymentID");
        
        return new PaymentHasPromotion(paymentID,promotionID);
    }

    private boolean isPaymentHasPromoExists(int paymentID, int promoID) 
    {
        String query = "SELECT COUNT(*) FROM Payment_Has_Promotion WHERE Payment_PaymentID = ? AND Promotion_PromotionID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, paymentID);
            statement.setInt(2, promoID);

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
