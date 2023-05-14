package africa.semicoon.AprilHotels.repositories;

import africa.semicoon.AprilHotels.models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryForCustomers implements ICustomer{
    List<Customer> customers = new ArrayList<>();


    @Override
    public Customer saveCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        for(Customer eachCustomer : customers){
            if(Objects.equals(eachCustomer.getEmail(),email)) return eachCustomer;
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public void deleteByEmail(String email) {
        Customer foundCustomer = getCustomerByEmail(email);
        if(foundCustomer != null) customers.remove(foundCustomer);
    }
}
