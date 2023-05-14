package africa.semicoon.AprilHotels.repositories;

import africa.semicoon.AprilHotels.models.Customer;

import java.util.List;

public interface ICustomer {
    Customer saveCustomer(Customer newCustomer);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    void deleteByEmail(String email);
}
