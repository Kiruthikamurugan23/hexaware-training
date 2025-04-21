package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Incident;
import com.hexaware.cars.entity.IncidentStatus;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.IncidentNumberNotFoundException;

import java.util.Date;
import java.util.List;

public interface IncidentDao {
    boolean createIncident(Incident incident) throws DataAccessException;
    Incident getIncidentById(int incidentId) throws IncidentNumberNotFoundException, DataAccessException;
    List<Incident> getIncidentsInDateRange(Date startDate, Date endDate) throws DataAccessException;
    List<Incident> searchIncidents(String incidentType) throws DataAccessException;
    boolean updateIncidentStatus(IncidentStatus status, int incidentId) throws DataAccessException;
    List<Incident> getAllIncidents() throws DataAccessException;
}