package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepositoryForCustomers implements ICustomerRepository {
    List<Customer> customers = new ArrayList<>();


    @Override
    public Customer saveCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    @Override
    public Customer getCustomerByEmail(String email) throws InvalidEmailException {
        if(!emailIsCorrect(email)){
            throw new InvalidEmailException("Invalid email.");
        }
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
    public void deleteByEmail(String email) throws InvalidEmailException {
        Customer foundCustomer = getCustomerByEmail(email);
        if(foundCustomer != null) customers.remove(foundCustomer);
    }

    private boolean emailIsCorrect(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
