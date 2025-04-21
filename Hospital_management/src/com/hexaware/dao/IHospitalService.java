package com.hexaware.dao;

import com.hexaware.entity.Appointment;
import com.hexaware.exception.PatientNumberNotFoundException;

import java.util.List;

public interface IHospitalService {

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;

    List<Appointment> getAppointmentsForDoctor(int doctorId);

    boolean scheduleAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean cancelAppointment(int appointmentId);
}
