package africa.semicoon.LegendaryHotels.services;

import africa.semicoon.LegendaryHotels.dto.requests.RequestsForReservations;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.exceptions.AmountIncorrectException;
import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;
import africa.semicoon.LegendaryHotels.repositories.IReservationRepository;
import africa.semicoon.LegendaryHotels.repositories.RepositoryForReservations;
import africa.semicoon.LegendaryHotels.utils.Map;

import java.util.Date;
import java.util.List;

import static africa.semicoon.LegendaryHotels.utils.AppUtils.FIFTY_DOLLARS;
import static africa.semicoon.LegendaryHotels.utils.AppUtils.TWENTY_DOLLARS;

public class ServiceForReservations implements IReservationService {

    IReservationRepository repositoryForReservation = new RepositoryForReservations();
    @Override
    public String findARoom(int roomType) {
        RoomType type;
        if(roomType == 1){
            type = RoomType.SINGLE;
        } else {
            type = RoomType.DOUBLE;
        }
        return repositoryForReservation.findARoom(type);
    }

    @Override
    public String reserveARoom(RequestsForReservations customerReservation) throws RoomUnavailableException, AmountIncorrectException {
        int roomChoiceNumber = customerReservation.getRoom().getRoomNumber();
        int amount = customerReservation.getRoom().getPrice();
        RoomType roomType = customerReservation.getRoom().getRoomType();
        Reservation newCustomer = Map.requestToReservation(customerReservation);

        if(roomType == RoomType.SINGLE){
            if(roomChoiceNumber < 1 || roomChoiceNumber > 10){
                throw new InvalidRoomNumberException("Invalid Room Number Selected.");
            }
            if(repositoryForReservation.listOfBookedSingleRooms().contains(roomChoiceNumber)){
                throw new RoomUnavailableException("Room " + roomChoiceNumber + " is already booked.");
            }
            if(amount < TWENTY_DOLLARS){
                throw new AmountIncorrectException("Wrong amount entered. Please pay $20.");
            }
        } else if (roomType == RoomType.DOUBLE) {
            if(roomChoiceNumber < 11 || roomChoiceNumber > 20){
                throw new InvalidRoomNumberException("Invalid Room Number Selected.");
            }
            if(repositoryForReservation.listOfBookedDoubleRooms().contains(roomChoiceNumber)){
                throw new RoomUnavailableException("Room " + roomChoiceNumber + " is already booked.");
            }
            if(amount < FIFTY_DOLLARS){
                throw new AmountIncorrectException("Wrong amount entered. Please pay $50.");
            }
        }
        return repositoryForReservation.reserveARoom(newCustomer);
    }

    @Override
    public Room getRoom(RequestsForReservations customerReservation) {
        int roomNumberChoice = customerReservation.getRoom().getRoomNumber();
        return repositoryForReservation.getRoom(roomNumberChoice);
    }

    @Override
    public ResponseForReservation getCustomerReservation(RequestsForReservations customerReservation) {
        String email = customerReservation.getCustomer().getEmail();

        //Customer newCustomer = Map.requestToReservation(customerReservation).getCustomer();
        //Reservation foundReservation = repositoryForReservation.getCustomerReservation(newCustomer);
       return Map.olaMap(email);
    }

    @Override
    public String checkOut(RequestsForReservations customerReservation) throws InvalidRoomNumberException {
        int roomChoiceNumber = customerReservation.getRoom().getRoomNumber();
        RoomType roomType = customerReservation.getRoom().getRoomType();
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
    public List<Reservation> findAllReservations() {
        return repositoryForReservation.getAllReservations();
    }

    public List<Integer> showAllRooms() { return repositoryForReservation.showAllRooms(); }

}
