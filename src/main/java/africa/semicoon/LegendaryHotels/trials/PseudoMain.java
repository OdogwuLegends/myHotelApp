package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.dto.response.ResponseForRoomBooking;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.repositories.ICustomerRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForCustomers;

public class PseudoMain {
    private static final ICustomerRepository repository = new RepositoryForCustomers();
    public static void main(String[] args) {
        ResponseForRoomBooking response = new ResponseForRoomBooking();
        System.out.println(myName());
        System.out.println(response.getMessage());
        System.out.println(myName().getMessage());

    }

    private static ResponseForRoomBooking myName(){
        ResponseForRoomBooking response = new ResponseForRoomBooking();

        response.setMessage("Odogwu Legends.");

        return response;
    }

}
