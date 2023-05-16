package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryForCustomers implements ICustomerRepository {
    List<Customer> customers = new ArrayList<>();


    @Override
    public Customer saveCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        for(Customer eachCustomer : customers){
            if(!Objects.equals(eachCustomer.getEmail(),email)){
                throw new InvalidEmailException("Customer does not exist.");
            }else{ return eachCustomer; }
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
