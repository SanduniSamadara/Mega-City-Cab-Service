package test;

import com.megacity.dao.DriverDAO;
import com.megacity.dao.daoImpl.DriverDAOImpl;
import com.megacity.dao.facory.DriverDAOFactory;
import com.megacity.model.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverTest {
    public static void main(String[] args) throws SQLException {
        // Assuming DriverDAOFactory is properly configured to return a DAO implementation
        DriverDAO driverDAO = DriverDAOFactory.getDriverDAO();

        // 1. Add a new driver
        Driver driver1 = new Driver(0, "Lional Munasinghe", "567FG", "90, Maho", "0716317270");
        driverDAO.addDriver(driver1);

        // 2. Get a driver by ID
        Driver retrievedDriver = driverDAO.getDriverById(6);
        System.out.println("Retrieved Driver: " + retrievedDriver);

        // 3. Update the driver
        driver1.setAddress("456 Street, Matale");
        driverDAO.updateDriver(driver1);

        // 4. Get all drivers
        List<Driver> drivers = driverDAO.getAllDrivers();
        drivers.forEach(driver -> System.out.println(driver));

        // 5. Delete the driver
        driverDAO.deleteDriver(1);
        System.out.println("Driver deleted.");
    }
}
