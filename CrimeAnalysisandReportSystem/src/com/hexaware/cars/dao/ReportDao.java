package com.hexaware.cars.dao;

import com.hexaware.cars.entity.Report;
import com.hexaware.cars.exception.DataAccessException;
import com.hexaware.cars.exception.ReportNotFoundException;

import java.util.List;

public interface ReportDao {
    boolean createReport(Report report) throws DataAccessException;
    Report getReportById(int reportId) throws ReportNotFoundException, DataAccessException;
    List<Report> getAllReports() throws DataAccessException;
    boolean updateReport(Report report) throws DataAccessException;
    boolean deleteReport(int reportId) throws DataAccessException;
}