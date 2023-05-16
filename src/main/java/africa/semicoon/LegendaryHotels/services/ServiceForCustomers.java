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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceForCustomers implements ICustomerService{

    private final ICustomerRepository customerRepository = new RepositoryForCustomers();
    @Override
    public CustomerRegistrationResponse saveCustomer(CustomerRequest newCustomerRequest) throws InvalidEmailException {
        if(!emailIsCorrect(newCustomerRequest.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        Customer newCustomer = Map.map(newCustomerRequest);

        customerRepository.saveCustomer(newCustomer);
        return new CustomerRegistrationResponse();
    }

    @Override
    public GetEmailResponse getCustomerByEmail(String email) throws InvalidEmailException {
        if(!emailIsCorrect(email)){
            throw new InvalidEmailException("Invalid email.");
        }
        GetEmailResponse getEmailResponse = new GetEmailResponse();

        Customer customer = customerRepository.getCustomerByEmail(email);
        if(customer != null) {
            getEmailResponse.setFirstName(customer.getFirstName());
            getEmailResponse.setLastName(customer.getLastName());
            getEmailResponse.setEmail(customer.getEmail());
        }
        else {
            getEmailResponse.setMessage("Customer does not exist.");
        }
        return getEmailResponse;
    }

    @Override
    public DeleteResponse deleteByEmail(CustomerRequest newCustomerRequest) throws InvalidEmailException {
        if(!emailIsCorrect(newCustomerRequest.getEmail())){
            throw new InvalidEmailException("Invalid email.");
        }
        String email = newCustomerRequest.getEmail();
        customerRepository.deleteByEmail(email);

        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Customer successfully deleted.");
        return deleteResponse;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    private boolean emailIsCorrect(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
