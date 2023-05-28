package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Customer;

import java.util.List;

public interface ICustomerRepository {
    Customer saveCustomer(Customer newCustomer);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    void deleteByEmail(String email);
    boolean verifyPassword(String password);
    boolean verifyCustomerByEmail(String email);
}
