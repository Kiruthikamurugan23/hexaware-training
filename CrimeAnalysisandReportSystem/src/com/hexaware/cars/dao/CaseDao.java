package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Case;
import com.hexaware.cars.entity.Incident;
import com.hexaware.cars.exception.CaseNotFoundException;
import com.hexaware.cars.exception.DataAccessException;

import java.util.List;

public interface CaseDao {
    Case createCase(String caseDescription, String caseStatus, int assignedOfficerId, List<Incident> incidents) throws DataAccessException;
    Case getCaseDetails(int caseId) throws CaseNotFoundException, DataAccessException;
    boolean updateCaseDetails(Case aCase) throws DataAccessException;
    List<Case> getAllCases() throws DataAccessException;
    List<Case> getCasesByTopOfficer() throws DataAccessException;
}