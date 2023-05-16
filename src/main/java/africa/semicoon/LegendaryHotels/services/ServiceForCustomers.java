package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.CustomerRequest;
import africa.semicoon.LegendaryHotels.dto.response.CustomerRegistrationResponse;
import africa.semicoon.LegendaryHotels.dto.response.DeleteResponse;
import africa.semicoon.LegendaryHotels.dto.response.GetEmailResponse;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.repositories.ICustomerRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForCustomers;
import africa.semicoon.LegendaryHotels.utils.Map;

import java.util.List;

public class ServiceForCustomers implements ICustomerService{

    private final ICustomerRepository customerRepository = new RepositoryForCustomers();
    @Override
    public CustomerRegistrationResponse saveCustomer(CustomerRequest newCustomerRequest) {
        Customer newCustomer = Map.map(newCustomerRequest);
        try{
            customerRepository.saveCustomer(newCustomer);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }

        return new CustomerRegistrationResponse();
    }

    @Override
    public GetEmailResponse getCustomerByEmail(String email) {
        GetEmailResponse getEmailResponse = new GetEmailResponse();

        try{
            Customer customer = customerRepository.getCustomerByEmail(email);
            getEmailResponse.setFirstName(customer.getFirstName());
            getEmailResponse.setLastName(customer.getLastName());
            getEmailResponse.setEmail(customer.getEmail());
            return getEmailResponse;
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        getEmailResponse.setMessage("Customer does not exist.");
        return getEmailResponse;
    }

    @Override
    public DeleteResponse deleteByEmail(CustomerRequest newCustomerRequest) {
        String email = newCustomerRequest.getEmail();
        try{
            customerRepository.deleteByEmail(email);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }

        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Customer successfully deleted.");
        return deleteResponse;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
