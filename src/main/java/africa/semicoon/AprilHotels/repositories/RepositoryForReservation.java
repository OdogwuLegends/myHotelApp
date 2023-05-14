package africa.semicoon.AprilHotels.repositories;

import africa.semicoon.AprilHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.AprilHotels.exceptions.RoomUnavailableException;
import africa.semicoon.AprilHotels.models.Customer;
import africa.semicoon.AprilHotels.models.Reservation;
import africa.semicoon.AprilHotels.models.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RepositoryForReservation implements IReservationRep {

    private List<Reservation> reservations = new ArrayList<>();
    private final IRoom repositoryForSingleRooms = new RepositoryForSingleRooms();
    private final IRoom repositoryForDoubleRooms = new RepositoryForDoubleRooms();

    @Override
    public Reservation findRooms(int roomNumber) {
        for (Reservation reservedRoom : reservations){
            if (Objects.equals(reservedRoom.getRoom().getRoomNumber(),roomNumber)) return reservedRoom;
        }
        return null;
    }


    @Override
    public Reservation findAndReserveARoom(Reservation customerReservation, RoomType roomType, int roomNumberChoice) throws RoomUnavailableException {
        if(roomType == RoomType.SINGLE){
            repositoryForSingleRooms.printAvailableRooms();
            repositoryForSingleRooms.bookRoom(roomNumberChoice);
        }
        else{
            repositoryForDoubleRooms.printAvailableRooms();
            repositoryForDoubleRooms.bookRoom(roomNumberChoice);
        }
        reservations.add(customerReservation);
        return customerReservation;
    }

    @Override
    public String getCustomerReservation(Customer customer) {
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCustomer(),customer)) return customerReservation.toString();
        }
        return null;
    }

    @Override
    public Reservation checkOut(Date checkOutDate, int roomType) {
        int roomNumberChoice = 0;
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCheckOut(),checkOutDate)) {
                roomNumberChoice = customerReservation.getRoom().getRoomNumber();
            }
            if(roomType == 1){
                repositoryForSingleRooms.checkOut(roomNumberChoice);
            }
            else {
                repositoryForDoubleRooms.checkOut(roomNumberChoice);
            }
            reservations.remove(customerReservation);
        }
        return null;
    }

    @Override
    public List<Reservation> printReservations() {
        return reservations;
    }
}
