package com.project.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.project.Exception.NoAccountExist;
import com.project.Exception.DataExistException;
//ID provide flexibility incase of different date type for primary key
public interface GenericDAO<T, ID> {
    void create(T entity) throws SQLException, DataExistException;
    void update(T entity) throws SQLException, DataExistException;
    void delete(T entity) throws SQLException;
    T getById(ID id) throws SQLException, NoAccountExist;
    List<T> getAll()  throws SQLException;
}
