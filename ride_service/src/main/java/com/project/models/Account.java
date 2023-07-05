package com.project.models;

public class Account {
    private int accountID;
    private String userName;
    private String password;

    public Account()
    {}
    public Account(int accountID,String userName,String password)
    {
        this.accountID = accountID;
        this.userName = userName;
        this.password = password;
    }

    //getter
    public int getAccountID()
    {
        return accountID;
    }

    public String getUserName()
    {
        return userName;
    }
    
    public String getPassword()
    {
        return password;
    }

    //setter
    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString() {
        String information = "Account ID: " + accountID +
                            "\nUsername: " + userName +
                            "\nPassword: " + password;

        return information;
    }
}
