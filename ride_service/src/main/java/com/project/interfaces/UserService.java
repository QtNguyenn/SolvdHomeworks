package com.project.interfaces;

import java.util.List;

import com.project.models.Account;
import com.project.models.Passenger;
import com.project.models.RideDriver;

public interface UserService {
    // Account operations
    void createAccount(Account account);
    Account getAccountById(int accountId);
    void updateAccount(Account account);
    void deleteAccount(int accountId);
    List<Account> getAllAccounts();
    // Passenger operations
    void createPassenger(Passenger passenger);
    Passenger getPassengerById(int passengerId);
    void updatePassenger(Passenger passenger);
    void deletePassenger(int passengerId);
    List<Passenger> getAllPassengers();
    // RideDriver operations
    void createRideDriver(RideDriver driver);
    RideDriver getRideDriverById(int driverId);
    void updateRideDriver(RideDriver driver);
    void deleteRideDriver(int driverId);
    List<RideDriver> getAllDrivers();
}
