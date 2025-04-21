module hospital.management.system {
    // Packages you want to export to other modules (like entity classes)
    exports com.hexaware.entity;
    exports com.hexaware.dao;
    exports com.hexaware.exception;
    exports com.hexaware.util;
    exports com.hexaware.main;

    // Requires modules from the JDK or external libs
    requires java.sql;     // For JDBC connections
}
