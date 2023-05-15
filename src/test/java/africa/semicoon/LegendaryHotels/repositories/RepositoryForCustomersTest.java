package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryForCustomersTest {
    private final ICustomer iCustomer = new RepositoryForCustomers();
    private Customer firstCustomer;
    private Customer secondCustomer;
    private Customer thirdCustomer;

    @BeforeEach
    void setUp(){
        firstCustomer = buildFirstCustomer();
        secondCustomer = buildSecondCustomer();
        thirdCustomer = buildThirdCustomer();
        iCustomer.saveCustomer(firstCustomer);
        iCustomer.saveCustomer(secondCustomer);
        iCustomer.saveCustomer(thirdCustomer);
    }

    @Test
    void testToSaveCustomer(){
        assertNotNull(firstCustomer);
        assertNotNull(firstCustomer.getFirstName());
        List<Customer> customerList = iCustomer.getAllCustomers();
        int actual = customerList.size();
        assertEquals(THREE, actual);
        assertNotNull(thirdCustomer.getFirstName());
    }
    @Test
    void testToGetCustomerByEmail(){
        Customer foundCustomer = iCustomer.getCustomerByEmail(firstCustomer.getEmail());
        assertNotNull(foundCustomer);
        assertEquals(firstCustomer.getEmail(),foundCustomer.getEmail());
        assertSame(foundCustomer, firstCustomer);
    }
    @Test
    void testToGetAllCustomers(){
        List<Customer> customerList = iCustomer.getAllCustomers();
        int actual = customerList.size();
        assertEquals(THREE,actual);
        assertNotNull(secondCustomer.getLastName());
        assertNotNull(thirdCustomer.getEmail());

    }

    @Test
    void testToDeleteByEmail(){
        iCustomer.deleteByEmail(secondCustomer.getEmail());
        List<Customer> customerList = iCustomer.getAllCustomers();
        int actual = customerList.size();
        assertEquals(TWO,actual);
    }

    private Customer buildFirstCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Legends");
        customer.setLastName("Ken");
        customer.setEmail("Leg@gmail.com");
        return customer;
    }
    private Customer buildSecondCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Mike");
        customer.setLastName("Boyo");
        customer.setEmail("mike@gmail.com");
        return customer;
    }
    private Customer buildThirdCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Ned");
        customer.setLastName("Stark");
        customer.setEmail("lordStark@gmail.com");
        return customer;
    }

}