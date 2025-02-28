package test;

import com.megacity.dao.CustomerDAO;
import com.megacity.dao.daoImpl.CustomerDAOImpl;
import com.megacity.dao.facory.CustomerDAOFactory;
import com.megacity.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerTest {
    public static void main(String[] args) throws SQLException {
        // Assuming CustomerDAOFactory is properly configured to return a DAO implementation
        CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();

        // 1. Add a new customer
        Customer customer1 = new Customer(0, "C123", "Saman Perera", "Colombo", "0712345678");
        customerDAO.addCustomer(customer1);

        // 2. Get a customer by ID
        Customer retrievedCustomer = customerDAO.getCustomerById(6);
        System.out.println("Retrieved Customer: " + retrievedCustomer);

        // 3. Update the customer details
        customer1.setAddress("456 Galle Road, Colombo");
        customerDAO.updateCustomer(customer1);

        // 4. Get all customers
        List<Customer> customers = customerDAO.getAllCustomers();
        customers.forEach(customer -> System.out.println(customer));

        // 5. Delete the customer
        customerDAO.deleteCustomer(1);
        System.out.println("Customer deleted.");
    }
}