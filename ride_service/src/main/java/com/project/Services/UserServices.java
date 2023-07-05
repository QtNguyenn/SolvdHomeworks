package com.project.Services;

import java.sql.SQLException;
import java.util.List;

import com.project.Exception.DataExistException;
import com.project.Exception.NoAccountExist;
import com.project.daos.AccountDAO;
import com.project.daos.DriverDAO;
import com.project.daos.PassengerDAO;
import com.project.interfaces.UserService;
import com.project.models.Account;
import com.project.models.Passenger;
import com.project.models.RideDriver;

public class UserServices implements UserService{

    private AccountDAO accountDAO;
    private PassengerDAO passengerDAO;
    private DriverDAO driverDAO;

    public UserServices(AccountDAO accountDAO, PassengerDAO passengerDAO, DriverDAO driverDAO) {
        this.accountDAO = accountDAO;
        this.passengerDAO = passengerDAO;
        this.driverDAO = driverDAO;
    }

    @Override
    public void createAccount(Account account) {
        try {
            accountDAO.create(account);
        } catch (DataExistException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccountById(int accountID) {
        try {
            return accountDAO.getById(accountID);
        } catch (NoAccountExist e) {
            e.getMessage();
            return null;
        }

    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.update(account);
    }

    @Override
    public void deleteAccount(int accountId) {
        Account account = null;
        try {
            account = accountDAO.getById(accountId);
        } catch (NoAccountExist e) {
            e.getMessage();
        }
        if (account != null) {
            accountDAO.delete(account);
        }
    }

    @Override
    public void createPassenger(Passenger passenger) {
        try {
            passengerDAO.create(passenger);
        } catch (DataExistException e) {

            e.getMessage();
        }
    }

    @Override
    public Passenger getPassengerById(int passengerId) {
        try {
            return passengerDAO.getById(passengerId);
        } catch (NoAccountExist e) {

            e.getMessage();
            return null;
        }
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        passengerDAO.update(passenger);
    }

    @Override
    public void deletePassenger(int passengerId) {
        Passenger passenger = null;
        try {
            passenger = passengerDAO.getById(passengerId);
        } catch (NoAccountExist e) {
   
            e.getMessage();
        }
        if (passenger != null) {
            passengerDAO.delete(passenger);
        }
    }

    @Override
    public void createRideDriver(RideDriver driver) {
        try {
            driverDAO.create(driver);
        } catch (DataExistException e) {
   
            e.getMessage();
        }
    }

    @Override
    public RideDriver getRideDriverById(int driverId) {
        try {
            return driverDAO.getById(driverId);
        } catch (NoAccountExist e) {
   
            e.getMessage();
            return null;
        }
    }

    @Override
    public void updateRideDriver(RideDriver driver) {
        driverDAO.update(driver);
    }

    @Override
    public void deleteRideDriver(int driverId) {
        RideDriver driver = null;
        try {
            driver = driverDAO.getById(driverId);
        } catch (NoAccountExist e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driverDAO.delete(driver);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAll();
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerDAO.getAll();
    }

    @Override
    public List<RideDriver> getAllDrivers() {
        return driverDAO.getAll();
    }
}
