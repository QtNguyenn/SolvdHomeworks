package com.project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.Exception.NoAccountExist;
import com.project.Exception.DataExistException;
import com.project.interfaces.GenericDAO;
import com.project.models.Account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountDAO implements GenericDAO<Account,Integer>{

    final Logger logger = LogManager.getLogger(AccountDAO.class);

    private Connection connection;
    //accept connection object as parameter
    //allows DAO methods to use the connection to perform ddatabase operations
    public AccountDAO(Connection connection) {
        this.connection = connection;
    }
    
    //search by id primary key
    @Override
    public Account getById(Integer id) throws NoAccountExist
    {
        Account account = null;
        String query = "SELECT * FROM Account WHERE AccountID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountID(rs.getInt("AccountID"));
                account.setPassword(rs.getString("AccountPW"));
                account.setUserName(rs.getString("AccountUser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (account == null) 
        {
        throw new NoAccountExist();
        }
        return account;
    }

    @Override
    public List<Account> getAll()
    {
        List<Account> accounts = new ArrayList<>();

        String query = "SELECT * FROM Account";

        try (Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query))
            {
                while(rs.next())
                {
                    Account account = new Account();
                    account.setAccountID(rs.getInt("AccountID"));
                    account.setPassword(rs.getString("AccountPW"));
                    account.setUserName(rs.getString("AccountUser"));

                    accounts.add(account);
                }
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
            
            return accounts;
    }

    @Override
    public void create(Account account) throws DataExistException, SQLException {
        if (isUsernameExists(account.getUserName())) {
            throw new DataExistException("Create account failed. " + account.getUserName() + " already exist.");
        }

        String query = "INSERT INTO Account (AccountUser, AccountPW) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Account account) 
    {   
        //create temp account object to hold information for comparison given accountID
        Account currentAccount = new Account();
        //to retrieve current account info
        try
        {
            currentAccount = getById(account.getAccountID());
        } catch (NoAccountExist e)
        {
            logger.info(e.getMessage());
        }

        //if current account user and pw (retrieved) is the same as the user and pw being passed in the parameter
        if (currentAccount.getUserName().equals(account.getUserName()) && currentAccount.getPassword().equals(account.getPassword())) {
        logger.info("New username and password are the same as the current ones. No update performed.");
        return;
        }

        //compare if the  new username already exist
        if (isUsernameExists(account.getUserName())) 
        {
            //case1: if it matches the current username
            if(currentAccount.getUserName().equals(account.getUserName()))
            {
                logger.info("Account username can't be the same as current username. No update performed on username.");

            }
            //if username exist but other user already taken
            else
            {
                logger.info("Account username already taken.");
                logger.info("Update Failed.");
                return;
            }
            
        }
  

        String query = "UPDATE Account SET AccountUser = ? , AccountPW = ? WHERE AccountID = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setInt(3,account.getAccountID());

            statement.executeUpdate();
            logger.info("Update Success");
            logger.info("Account Info: " +"\n"+ account);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Account account)
    {
        String query = "DELETE FROM Account WHERE AccountID = ?";

        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, account.getAccountID());

            statement.executeUpdate();
            logger.info("Account deleted: " +"\n"+ account);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method to check if a username already exists in the accounts
    private boolean isUsernameExists(String username) {
    String query = "SELECT COUNT(*) FROM Account WHERE AccountUser = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, username);
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
