package com.hexaware.cars.test;

import com.hexaware.cars.dao.IncidentDao;
import com.hexaware.cars.entity.Incident;
import com.hexaware.cars.entity.IncidentStatus;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.IncidentNumberNotFoundException;
import com.hexaware.cars.service.IncidentDaoImpl;
import com.hexaware.cars.util.DatabaseConnection;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CrimeAnalysisTest {

    private IncidentDao incidentDao;
    private Incident incident;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Database connection is not available.");
        }
        connection.setAutoCommit(false);

        incidentDao = new IncidentDaoImpl(connection);

        // Ensure that the OfficerId exists (e.g., Officer ID = 1)
        int validOfficerId = 1;  // Replace with an actual valid OfficerId from the 'officers' table
        incident = new Incident("Theft", new Date(), new BigDecimal("34.0522"), new BigDecimal("-118.2437"),
                "Suspected robbery", IncidentStatus.OPEN, validOfficerId, 1, 4);
    }


    @Test
    void testCreateIncident_Success() throws DataAccessException, IncidentNumberNotFoundException {
        Incident newIncident = new Incident("Theft", new Date(), new BigDecimal("34.0522"),
                new BigDecimal("-118.2437"), "Suspected robbery", IncidentStatus.OPEN, 1, 1, 4);

        boolean result = incidentDao.createIncident(newIncident);
        assertTrue(result, "Incident creation failed");
        assertNotEquals(0, newIncident.getIncidentID(), "Incident ID should not be zero");

        // Verify the retrieved incident
        Incident retrievedIncident = incidentDao.getIncidentById(newIncident.getIncidentID());
        assertNotNull(retrievedIncident, "Retrieved incident should not be null");
        assertEquals(newIncident.getIncidentType(), retrievedIncident.getIncidentType(), "Incident types should match");
        assertEquals(newIncident.getDescription(), retrievedIncident.getDescription(), "Descriptions should match");
    }

    @Test
    void testCreateIncident_Failure() {
        Exception exception = assertThrows(DataAccessException.class, () -> {
            incidentDao.createIncident(null);
        });
        assertEquals("Incident cannot be null", exception.getMessage(), "Exception message should match");
    }
    
    @Test
    void testGetIncidentById() throws DataAccessException, IncidentNumberNotFoundException {
        incidentDao.createIncident(incident);
        Incident retrieved = incidentDao.getIncidentById(incident.getIncidentID());
        
        assertNotNull(retrieved, "Retrieved incident should not be null");
        assertEquals(incident.getIncidentID(), retrieved.getIncidentID(), "Incident IDs should match");
    }

    @Test
    void testUpdateIncidentStatus() throws DataAccessException, IncidentNumberNotFoundException {
        assertTrue(incidentDao.createIncident(incident), "Incident creation failed");
        assertNotEquals(0, incident.getIncidentID(), "Incident ID should not be zero");

        boolean updated = incidentDao.updateIncidentStatus(IncidentStatus.CLOSED, incident.getIncidentID());
        assertTrue(updated, "Incident status should be updated successfully");

        Incident updatedIncident = incidentDao.getIncidentById(incident.getIncidentID());
        assertNotNull(updatedIncident, "Retrieved incident should not be null");
        assertEquals(IncidentStatus.CLOSED, updatedIncident.getStatus(), "Status should be updated to CLOSED");
    }


    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback(); // Rollback any changes made during the test
                DatabaseConnection.closeConnection();
            }
        } catch (SQLException e) {
            fail("Failed to roll back transaction: " + e.getMessage());
        }
    }
}
    
    

