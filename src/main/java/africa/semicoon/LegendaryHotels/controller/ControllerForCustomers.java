package africa.semicoon.LegendaryHotels.controller;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForCustomerRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.services.ICustomerService;
import africa.semicoon.LegendaryHotels.services.ServiceForCustomers;

import java.util.List;

public class ControllerForCustomers {
    private final ICustomerService customerService = new ServiceForCustomers();

    public ResponseForCustomerRegistration saveCustomer(RequestsForCustomers requestsForCustomers){
        try{
            return customerService.saveCustomer(requestsForCustomers);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        return new ResponseForCustomerRegistration();
    }

    public ResponseToFindByEmail findCustomerByEmail(RequestsForCustomers requestsForCustomers){
        try{
            return customerService.getCustomerByEmail(requestsForCustomers.getEmail());
        } catch (InvalidEmailException | EntityDoesNotExistException ex){
            System.err.println(ex.getMessage());
        }
        return new ResponseToFindByEmail();
    }

    public ResponseForDelete deleteCustomerByEmail(RequestsForCustomers requestsForCustomers){
        try{
            customerService.deleteByEmail(requestsForCustomers);
        }  catch (InvalidEmailException | EntityDoesNotExistException ex){
            System.err.println(ex.getMessage());
        }
        return new ResponseForDelete();
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public boolean verifyPassword(String password){ return customerService.verifyPassword(password);}
    public boolean verifyCustomerByEmail(String email){ return customerService.verifyCustomerByEmail(email); }
}
