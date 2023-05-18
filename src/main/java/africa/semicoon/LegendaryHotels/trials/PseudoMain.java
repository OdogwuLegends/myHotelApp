package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.repositories.ICustomerRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForCustomers;

public class PseudoMain {
    private static final ICustomerRepository repository = new RepositoryForCustomers();
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setPassword("myMan1993");
        repository.saveCustomer(customer);


        System.out.println(repository.verifyPassword("myMan1993"));


        Customer secondCustomer = new Customer();
        customer.setPassword("myMan1992");
        repository.saveCustomer(secondCustomer);
        System.out.println(repository.verifyPassword("myMan1992"));
        //System.out.println(repository.verifyPassword("myMan1991"));


    }



}
