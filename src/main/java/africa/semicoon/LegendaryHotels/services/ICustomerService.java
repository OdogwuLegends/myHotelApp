package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForCustomerRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;

import java.util.List;

public interface ICustomerService {
    ResponseForCustomerRegistration saveCustomer(RequestsForCustomers newCustomer) throws InvalidEmailException;
    ResponseToFindByEmail getCustomerByEmail(String email) throws InvalidEmailException, EntityDoesNotExistException;
    ResponseForDelete deleteByEmail(RequestsForCustomers newRequestsForCustomers) throws InvalidEmailException, EntityDoesNotExistException;
    List<Customer> getAllCustomers();
    boolean verifyPassword(String password);
}
