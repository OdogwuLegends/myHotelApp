package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.CustomerRequest;
import africa.semicoon.LegendaryHotels.dto.response.CustomerRegistrationResponse;
import africa.semicoon.LegendaryHotels.dto.response.DeleteResponse;
import africa.semicoon.LegendaryHotels.dto.response.GetEmailResponse;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;

import java.util.List;

public interface ICustomerService {
    CustomerRegistrationResponse saveCustomer(CustomerRequest newCustomer);
    GetEmailResponse getCustomerByEmail(String email);
    DeleteResponse deleteByEmail(CustomerRequest newCustomerRequest);
    List<Customer> getAllCustomers();
}
