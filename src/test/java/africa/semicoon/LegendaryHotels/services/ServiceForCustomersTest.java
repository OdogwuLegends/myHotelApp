package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForCustomers;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForCustomerRegistration;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForDelete;
import africa.semicoon.LegendaryHotels.dto.response.ResponseToFindByEmail;
import africa.semicoon.LegendaryHotels.exceptions.EntityDoesNotExistException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidEmailException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.utils.Map;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceForCustomersTest {
    private final ICustomerService customerService = new ServiceForCustomers();

    @Test
    void testToSaveCustomer(){
        RequestsForCustomers requestsForCustomers = buildCustomer();
        ResponseForCustomerRegistration response = new ResponseForCustomerRegistration();
        try {
            response = customerService.saveCustomer(requestsForCustomers);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        assertNotNull(response);
        assertEquals("Legends",response.getFirstName());
        assertTrue(customerService.getAllCustomers().contains(Map.requestToCustomer(requestsForCustomers)));
    }
    @Test
    void testToFindCustomerByEmail(){
        RequestsForCustomers requestsForCustomers = buildCustomer();
        ResponseForCustomerRegistration response = new ResponseForCustomerRegistration();
        try {
            response = customerService.saveCustomer(requestsForCustomers);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }

        ResponseToFindByEmail foundEmail = new ResponseToFindByEmail();
        try {
            foundEmail = customerService.getCustomerByEmail("odogwu@gmail.com");
        } catch (InvalidEmailException | EntityDoesNotExistException ex){
            System.err.println(ex.getMessage());
        }

        assertEquals(response.getEmail(),foundEmail.getEmail());
    }

    @Test
    void deleteCustomerByEmail(){
        RequestsForCustomers requestsForCustomers = buildCustomer();
        try {
            customerService.saveCustomer(requestsForCustomers);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        ResponseForDelete deletedCustomer = new ResponseForDelete();
        try {
            deletedCustomer = customerService.deleteByEmail(requestsForCustomers);
        } catch (InvalidEmailException | EntityDoesNotExistException ex){
            System.err.println(ex.getMessage());
        }
        assertEquals("Customer successfully deleted.",deletedCustomer.getMessage());
        assertFalse(customerService.getAllCustomers().contains(Map.requestToCustomer(requestsForCustomers)));
    }
    @Test
    void findAllCustomers(){
        RequestsForCustomers requestsForCustomers = buildCustomer();
        try {
            customerService.saveCustomer(requestsForCustomers);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        List<Customer> allCustomers = customerService.getAllCustomers();
        assertEquals(1,allCustomers.size());
        assertTrue(customerService.getAllCustomers().contains(Map.requestToCustomer(requestsForCustomers)));
    }
    @Test
    void verifyPassword(){
        RequestsForCustomers requestsForCustomers = buildCustomer();
        try {
            customerService.saveCustomer(requestsForCustomers);
        } catch (InvalidEmailException ex){
            System.err.println(ex.getMessage());
        }
        String firstPassword = "1234";
        boolean actual = customerService.verifyPassword(firstPassword);
        assertTrue(actual);

    }



    private static RequestsForCustomers buildCustomer() {
        RequestsForCustomers requestsForCustomers = new RequestsForCustomers();

        requestsForCustomers.setFirstName("Legends");
        requestsForCustomers.setLastName("Odogwu");
        requestsForCustomers.setEmail("odogwu@gmail.com");
        requestsForCustomers.setPassword("1234");
        return requestsForCustomers;
    }

}