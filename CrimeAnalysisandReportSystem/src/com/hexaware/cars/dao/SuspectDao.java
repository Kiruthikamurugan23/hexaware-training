package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Suspect;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.SuspectNotFoundException;

import java.util.List;

public interface SuspectDao {
    boolean createSuspect(Suspect suspect) throws DataAccessException;
    Suspect getSuspectById(int suspectId) throws SuspectNotFoundException, DataAccessException;
    List<Suspect> getAllSuspects() throws DataAccessException;
    boolean updateSuspect(Suspect suspect) throws DataAccessException;
    boolean deleteSuspect(int suspectId) throws DataAccessException;
}