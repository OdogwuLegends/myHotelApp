package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.RoomType;
import africa.semicoon.LegendaryHotels.repositories.IReservationRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForReservation;
import africa.semicoon.LegendaryHotels.utils.Map;

import java.util.Date;
import java.util.List;

public class ServiceForReservation implements IReservationService {

    IReservationRepository repositoryForReservation = new RepositoryForReservation();
    @Override
    public String findARoom(ReservationRequest customerReservation) {
        RoomType roomType = customerReservation.getRoomType();
        try {
            return repositoryForReservation.findARoom(roomType);
        } catch (InvalidRoomNumberException ex){
            System.err.println(ex.getMessage());
        }
        return "";
    }

    @Override
    public String reserveARoom(ReservationRequest customerReservation) {
        int roomNumberChoice = customerReservation.getRoomChoice();
        RoomType roomType = customerReservation.getRoomType();
        Reservation newCustomer = Map.map(customerReservation);

        try {
            return repositoryForReservation.reserveARoom(newCustomer,roomType,roomNumberChoice);
        } catch (InvalidRoomNumberException | RoomUnavailableException ex){
            System.err.println(ex.getMessage());
        }
        return "";
    }

    @Override
    public Reservation getRoom(ReservationRequest customerReservation) {
        int roomNumberChoice = customerReservation.getRoomChoice();
        return repositoryForReservation.getRoom(roomNumberChoice);
    }

    @Override
    public Reservation getCustomerReservation(ReservationRequest customerReservation) {
        //Customer newCustomer = customerReservation.getC
        return null;
    }

    @Override
    public Reservation checkOut(ReservationRequest customerReservation) {
        Date checkInDate = Map.setDate(customerReservation.getCheckInDate(), customerReservation.getCheckInMonth(), customerReservation.getCheckInYear());
        Date checkOutDate = Map.setDate(customerReservation.getCheckOutDate(), customerReservation.getCheckOutMonth(), customerReservation.getCheckOutYear());
        return repositoryForReservation.checkOut(checkInDate,checkOutDate);
    }

    @Override
    public List<Reservation> printReservations() {
        return repositoryForReservation.printReservations();
    }
}
