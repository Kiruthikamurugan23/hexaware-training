package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Agency;
import com.hexaware.cars.exception.AgencyNotFoundException;
import com.hexaware.cars.exception.DataAccessException;

import java.util.List;

public interface AgencyDao {
    boolean createAgency(Agency agency) throws DataAccessException;
    Agency getAgencyById(int agencyId) throws AgencyNotFoundException, DataAccessException;
    List<Agency> getAllAgencies() throws DataAccessException;
    boolean updateAgency(Agency agency) throws DataAccessException;
    boolean deleteAgency(int agencyId) throws DataAccessException;
}