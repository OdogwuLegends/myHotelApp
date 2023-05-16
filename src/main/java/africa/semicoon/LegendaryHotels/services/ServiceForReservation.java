package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.ReservationRequest;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
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
        return repositoryForReservation.findARoom(roomType);
    }

    @Override
    public String reserveARoom(ReservationRequest customerReservation) throws RoomUnavailableException {
        int roomChoiceNumber = customerReservation.getRoomChoice();
        RoomType roomType = customerReservation.getRoomType();
        Reservation newCustomer = Map.map(customerReservation);

        if(roomType == RoomType.SINGLE){
            if(roomChoiceNumber < 1 || roomChoiceNumber > 10){
                throw new InvalidRoomNumberException("Invalid Room Number Selected.");
            }
            if(repositoryForReservation.listOfBookedSingleRooms().contains(roomChoiceNumber)){
                throw new RoomUnavailableException("Room " + roomChoiceNumber + " is already booked.");
            }
        } else if (roomType == RoomType.DOUBLE) {
            if(roomChoiceNumber < 11 || roomChoiceNumber > 20){
                throw new InvalidRoomNumberException("Invalid Room Number Selected.");
            }
            if(repositoryForReservation.listOfBookedDoubleRooms().contains(roomChoiceNumber)){
                throw new RoomUnavailableException("Room " + roomChoiceNumber + " is already booked.");
            }
        }
        return repositoryForReservation.reserveARoom(newCustomer,roomType,roomChoiceNumber);
    }

    @Override
    public Room getRoom(ReservationRequest customerReservation) {
        int roomNumberChoice = customerReservation.getRoomChoice();
        return repositoryForReservation.getRoom(roomNumberChoice);
    }

    @Override
    public String getCustomerReservation(ReservationRequest customerReservation) {
        Customer newCustomer = Map.map(customerReservation).getCustomer();
        return repositoryForReservation.getCustomerReservation(newCustomer).toString();
    }

    @Override
    public Reservation checkOut(ReservationRequest customerReservation) throws InvalidRoomNumberException {
        int roomChoiceNumber = customerReservation.getRoomChoice();
        RoomType roomType = customerReservation.getRoomType();
        Date checkInDate = Map.setDate(customerReservation.getCheckInDate(), customerReservation.getCheckInMonth(), customerReservation.getCheckInYear());
        Date checkOutDate = Map.setDate(customerReservation.getCheckOutDate(), customerReservation.getCheckOutMonth(), customerReservation.getCheckOutYear());

        if(roomType == RoomType.SINGLE){
            if(roomChoiceNumber < 1 || roomChoiceNumber > 10){
                throw new InvalidRoomNumberException("Invalid Room Number Selected.");
            }
            if(!repositoryForReservation.listOfBookedSingleRooms().contains(roomChoiceNumber)){
                throw new InvalidRoomNumberException("Room " + roomChoiceNumber + " not booked previously.");
            }
        } else if (roomType == RoomType.DOUBLE) {
            if(roomChoiceNumber < 11 || roomChoiceNumber > 20){
                throw new InvalidRoomNumberException("Invalid Room Number Selected.");
            }
            if(!repositoryForReservation.listOfBookedDoubleRooms().contains(roomChoiceNumber)){
                throw new InvalidRoomNumberException("Room " + roomChoiceNumber + " not booked previously.");
            }
        }
        return repositoryForReservation.checkOut(checkInDate,checkOutDate);
    }

    @Override
    public List<Reservation> printReservations() {
        return repositoryForReservation.printReservations();
    }

}
