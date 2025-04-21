package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Evidence;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.EvidenceNotFoundException;

import java.util.List;

public interface EvidenceDao {
    boolean createEvidence(Evidence evidence) throws DataAccessException;
    Evidence getEvidenceById(int evidenceId) throws EvidenceNotFoundException, DataAccessException;
    List<Evidence> getAllEvidence() throws DataAccessException;
    boolean updateEvidence(Evidence evidence) throws DataAccessException;
    boolean deleteEvidence(int evidenceId) throws DataAccessException;
}