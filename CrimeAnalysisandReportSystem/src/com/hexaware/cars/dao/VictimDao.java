package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Victim;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.VictimNotFoundException;

import java.util.List;

public interface VictimDao {
    boolean createVictim(Victim victim) throws DataAccessException;
    Victim getVictimById(int victimId) throws VictimNotFoundException, DataAccessException;
    List<Victim> getAllVictims() throws DataAccessException;
    boolean updateVictim(Victim victim) throws DataAccessException;
    boolean deleteVictim(int victimId) throws DataAccessException;
}