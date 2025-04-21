package com.hexaware.main;

import com.hexaware.dao.HospitalServiceImpl;
import com.hexaware.entity.Appointment;
import com.hexaware.exception.PatientNumberNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HospitalServiceImpl service = new HospitalServiceImpl();
        int mainChoice;

        do {
            System.out.println("\n======= Hospital Management System =======");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    userMenu(sc, service);
                    break;
                case 2:
                    adminMenu(sc, service);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (mainChoice != 3);

        sc.close();
    }

    private static void userMenu(Scanner sc, HospitalServiceImpl service) {
        int choice;
        do {
            System.out.println("\n======= User Menu =======");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for Patient");
            System.out.println("3. Get Appointments for Doctor");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter Appointment ID: ");
                    int id = sc.nextInt();
                    Appointment app = service.getAppointmentById(id);
                    if (app != null) {
                        System.out.println(app);
                    } else {
                        System.out.println("No appointment found with ID: " + id);
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    try {
                        List<Appointment> list = service.getAppointmentsForPatient(pid);
                        if (list.isEmpty()) {
                            System.out.println("No appointments found for this patient.");
                        } else {
                            list.forEach(System.out::println);
                        }
                    } catch (PatientNumberNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();
                    List<Appointment> list = service.getAppointmentsForDoctor(did);
                    if (list.isEmpty()) {
                        System.out.println("No appointments found for this doctor.");
                    } else {
                        list.forEach(System.out::println);
                    }
                    break;
                }
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void adminMenu(Scanner sc, HospitalServiceImpl service) {
        int choice;
        do {
            System.out.println("\n======= Admin Menu =======");
            System.out.println("1. Schedule New Appointment");
            System.out.println("2. Update Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter Appointment Details:");
                    System.out.print("Appointment ID: ");
                    int aid = sc.nextInt();
                    System.out.print("Patient ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Doctor ID: ");
                    int did = sc.nextInt();
                    System.out.print("Appointment Date (YYYY-MM-DD): ");
                    String date = sc.next();
                    System.out.print("Description: ");
                    sc.nextLine(); 
                    String desc = sc.nextLine();

                    Appointment appointment = new Appointment(aid, pid, did, date, desc);
                    boolean added = service.scheduleAppointment(appointment);
                    if (added) {
                        System.out.println("Appointment scheduled successfully!");
                    } else {
                        System.out.println("Failed to schedule appointment. It may already exist.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter Appointment ID to Update:");
                    int aid = sc.nextInt();
                    System.out.print("New Patient ID: ");
                    int pid = sc.nextInt();
                    System.out.print("New Doctor ID: ");
                    int did = sc.nextInt();
                    System.out.print("New Appointment Date (YYYY-MM-DD): ");
                    String date = sc.next();
                    System.out.print("New Description: ");
                    sc.nextLine(); // consume newline
                    String desc = sc.nextLine();

                    Appointment appointment = new Appointment(aid, pid, did, date, desc);
                    boolean updated = service.updateAppointment(appointment);
                    if (updated) {
                        System.out.println("Appointment updated successfully!");
                    } else {
                        System.out.println("Update failed. Please check if appointment ID exists.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter Appointment ID to Cancel: ");
                    int aid = sc.nextInt();
                    boolean deleted = service.cancelAppointment(aid);
                    if (deleted) {
                        System.out.println("Appointment cancelled successfully.");
                    } else {
                        System.out.println("Cancellation failed. Appointment may not exist.");
                    }
                    break;
                }
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}
