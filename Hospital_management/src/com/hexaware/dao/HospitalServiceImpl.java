package com.hexaware.dao;

import com.hexaware.entity.Appointment;
import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements IHospitalService {
    private Connection conn = DBConnUtil.getConnection();

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        try {
            String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, appointmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                appointment = new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("appointmentDate"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String query = "SELECT * FROM Appointment WHERE patientId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("appointmentDate"),
                        rs.getString("description")
                ));
            }

            if (appointments.isEmpty()) {
                throw new PatientNumberNotFoundException("Patient ID " + patientId + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String query = "SELECT * FROM Appointment WHERE doctorId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("appointmentDate"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        try {
            String query = "INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, appointment.getAppointmentId());
            ps.setInt(2, appointment.getPatientId());
            ps.setInt(3, appointment.getDoctorId());
            ps.setString(4, appointment.getAppointmentDate());
            ps.setString(5, appointment.getDescription());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error while scheduling appointment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        try {
            String query = "UPDATE Appointment SET patientId=?, doctorId=?, appointmentDate=?, description=? WHERE appointmentId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setString(3, appointment.getAppointmentDate());
            ps.setString(4, appointment.getDescription());
            ps.setInt(5, appointment.getAppointmentId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error while updating appointment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        try {
            String query = "DELETE FROM Appointment WHERE appointmentId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, appointmentId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error while cancelling appointment: " + e.getMessage());
            return false;
        }
    }
}

