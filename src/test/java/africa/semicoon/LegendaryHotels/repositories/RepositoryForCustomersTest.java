package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryForCustomersTest {
    private final ICustomerRepository iCustomerRepository = new RepositoryForCustomers();
    private Customer firstCustomer;
    private Customer secondCustomer;
    private Customer thirdCustomer;

    @BeforeEach
    void setUp(){
        firstCustomer = buildFirstCustomer();
        secondCustomer = buildSecondCustomer();
        thirdCustomer = buildThirdCustomer();
        iCustomerRepository.saveCustomer(firstCustomer);
        iCustomerRepository.saveCustomer(secondCustomer);
        iCustomerRepository.saveCustomer(thirdCustomer);
    }

    @Test
    void testToSaveCustomer(){
        assertNotNull(firstCustomer);
        assertNotNull(firstCustomer.getFirstName());
        List<Customer> customerList = iCustomerRepository.getAllCustomers();
        int actual = customerList.size();
        assertEquals(THREE, actual);
        assertNotNull(thirdCustomer.getFirstName());
    }
    @Test
    void testToGetCustomerByEmail(){
        Customer foundCustomer = iCustomerRepository.getCustomerByEmail(firstCustomer.getEmail());
        assertNotNull(foundCustomer);
        assertEquals(firstCustomer.getEmail(),foundCustomer.getEmail());
        assertSame(foundCustomer, firstCustomer);
    }
    @Test
    void testToGetAllCustomers(){
        List<Customer> customerList = iCustomerRepository.getAllCustomers();
        int actual = customerList.size();
        assertEquals(THREE,actual);
        assertNotNull(secondCustomer.getLastName());
        assertNotNull(thirdCustomer.getEmail());

    }

    @Test
    void testToDeleteByEmail(){
        iCustomerRepository.deleteByEmail(secondCustomer.getEmail());
        List<Customer> customerList = iCustomerRepository.getAllCustomers();
        int actual = customerList.size();
        assertEquals(TWO,actual);
    }

    @Test
    void testToVerifyPassword(){
        String firstPassword = "12345";
        boolean actual = iCustomerRepository.verifyPassword(firstPassword);
        assertTrue(actual);

        String secondPassword = "22222";
        assertEquals(secondCustomer.getPassword(),secondPassword);
    }

    private Customer buildFirstCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Legends");
        customer.setLastName("Ken");
        customer.setEmail("Leg@gmail.com");
        customer.setPassword("12345");
        return customer;
    }
    private Customer buildSecondCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Mike");
        customer.setLastName("Boyo");
        customer.setEmail("mike@gmail.com");
        customer.setPassword("22222");
        return customer;
    }
    private Customer buildThirdCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Ned");
        customer.setLastName("Stark");
        customer.setEmail("lordStark@gmail.com");
        customer.setPassword("33333");
        return customer;
    }

}