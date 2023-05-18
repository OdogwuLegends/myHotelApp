package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForCustomerRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.repositories.ICustomerRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForCustomers;
import africa.semicoon.LegendaryHotels.utils.Map;

import java.util.List;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.emailIsCorrect;

public class ServiceForCustomers implements ICustomerService{

    private final ICustomerRepository customerRepository = new RepositoryForCustomers();
    @Override
    public ResponseForCustomerRegistration saveCustomer(RequestsForCustomers newRequestsForCustomers) throws InvalidEmailException {
        if(!emailIsCorrect(newRequestsForCustomers.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        Customer newCustomer = Map.requestToCustomer(newRequestsForCustomers);
        Customer savedCustomer =  customerRepository.saveCustomer(newCustomer);
        return Map.customerToRegistrationResponse(savedCustomer);
    }

    @Override
    public ResponseToFindByEmail getCustomerByEmail(String email) throws InvalidEmailException, EntityDoesNotExistException {
        if(!emailIsCorrect(email)){
            throw new InvalidEmailException("Invalid email.");
        }
        ResponseToFindByEmail responseToFindByEmail;

        Customer customer = customerRepository.getCustomerByEmail(email);
        if(customer == null) {
           throw new EntityDoesNotExistException("Customer does not exist.");
        }
        else {
//            getEmailResponse.setFirstName(customer.getFirstName());
//            getEmailResponse.setLastName(customer.getLastName());
//            getEmailResponse.setEmail(customer.getEmail());
            responseToFindByEmail = Map.customerToEmailResponse(customer);
        }
        return responseToFindByEmail;
    }

    @Override
    public ResponseForDelete deleteByEmail(RequestsForCustomers newRequestsForCustomers) throws InvalidEmailException, EntityDoesNotExistException {
        if(!emailIsCorrect(newRequestsForCustomers.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        String email = newRequestsForCustomers.getEmail();

        if(email == null){
            throw new EntityDoesNotExistException("Customer does not exist.");
        } else{
            customerRepository.deleteByEmail(email);
        }

        ResponseForDelete responseForDelete = new ResponseForDelete();
        responseForDelete.setMessage("Customer successfully deleted.");
        return responseForDelete;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public boolean verifyPassword(String password) {
        return customerRepository.verifyPassword(password);
    }

}
