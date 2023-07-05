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
import com.project.models.Promotion;

public class PromotionDAO implements GenericDAO<Promotion,Integer>{

    final Logger logger = LogManager.getLogger(PromotionDAO.class);
    private Connection connection;

    public PromotionDAO(Connection connection) 
    {
        this.connection = connection;
    }
    
     @Override
    public Promotion getById(Integer id) throws NoAccountExist{
        
        Promotion promotion = null;
        String query = "SELECT * FROM Promotion WHERE PromotionID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                promotion =  extractPromotionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (promotion == null) 
        {
        throw new NoAccountExist();
        }
        return promotion;
    }

    @Override
    public void create(Promotion promotion) throws DataExistException 
    {   //can't have 2 different promotion for the same ride
        if (isCodeExists(promotion.getPromotionCode())) 
        {
            throw new DataExistException("Failed. Promotion Code: " + promotion.getPromotionCode() + " is already in the database.");
        }
        
        String query = "INSERT INTO Promotion (Code) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, promotion.getPromotionCode());
                
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    @Override
    public void update(Promotion promotion) {
        //Temp Promotion
       Promotion currentPromotion = new Promotion();
        //retrieve info
        try{
            currentPromotion = getById(promotion.getPromotionID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }
        
         if (isCodeExists(promotion.getPromotionCode())) 
        {
            //if it matches the current email
            if(currentPromotion.getPromotionCode()==(promotion.getPromotionCode()))
            {
                logger.info("Code can't be the same as current code. No update performed on phone.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("Code already in the database.");
                logger.info("Update Failed.");
                return;
            }   
        }
        
        String query = "UPDATE Promotion SET Code = ? WHERE PromotionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, promotion.getPromotionCode());
            statement.setInt(2, promotion.getPromotionID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }

    @Override
    public void delete(Promotion promotion) {
        String query = "DELETE FROM Promotion WHERE PromotionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, promotion.getPromotionID());
            statement.executeUpdate();
            logger.info("Promotion deleted: " +"\n"+ promotion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Promotion> getAll() {
        List<Promotion> promotions = new ArrayList<>();
        String query = "SELECT * FROM Promotion";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Promotion promotion = extractPromotionFromResultSet(resultSet);
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }

     private Promotion extractPromotionFromResultSet(ResultSet resultSet) throws SQLException {
        int promotionID = resultSet.getInt("PromotionID");
        String code = resultSet.getString("Code");
        
        return new Promotion(promotionID, code);
    }

    private boolean isCodeExists(String promoCode) 
    {
        String query = "SELECT COUNT(*) FROM Promotion WHERE Code = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, promoCode);
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
