package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Officer;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.OfficerNotFoundException;

import java.util.List;

public interface OfficerDao {
    boolean createOfficer(Officer officer) throws DataAccessException;
    Officer getOfficerById(int officerId) throws OfficerNotFoundException, DataAccessException;
    List<Officer> getAllOfficers() throws DataAccessException;
    boolean updateOfficer(Officer officer) throws DataAccessException;
    boolean deleteOfficer(int officerId) throws DataAccessException;
    
}