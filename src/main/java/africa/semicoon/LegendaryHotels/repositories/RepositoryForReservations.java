package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.dto.response.ResponseForReservation;
import africa.semicoon.LegendaryHotels.dto.response.ResponseForRoomBooking;
import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static africa.semicoon.LegendaryHotels.models.RoomType.SINGLE;

public class RepositoryForReservations implements IReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();
    private int[] rooms = new int[20];

    @Override
    public ResponseForRoomBooking findARoom(RoomType roomType) {
        if(roomType == SINGLE){ return printAvailableSingleRooms(); }
        else { return printAvailableDoubleRooms(); }
    }

    @Override
    public ResponseForRoomBooking reserveARoom(Reservation customerReservation) {
        if(customerReservation.getRoom().getRoomType() == SINGLE){
            reservations.add(customerReservation);
            return bookSingleRoom(customerReservation.getRoom().getRoomNumber(), customerReservation.getRoom().getPrice());
        }
        else {
            reservations.add(customerReservation);
            return bookDoubleRoom(customerReservation.getRoom().getRoomNumber(), customerReservation.getRoom().getPrice());
        }
    }

    @Override
    public Room getRoom(int roomNumber) {
        for (Reservation reservedRoom : reservations){
            if (Objects.equals(reservedRoom.getRoom().getRoomNumber(),roomNumber)) return reservedRoom.getRoom();
        }
        return null;
    }

    @Override
    public Reservation getCustomerReservation(Customer customer) {
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCustomer(),customer)) return customerReservation;
            //if(customerReservation.getCustomer().getEmail().equals(customer.getEmail())) return customerReservation;
        }
        return null;
    }

    @Override
    public ResponseForRoomBooking checkOut(Date checkInDate, Date checkOutDate) {
        RoomType roomType = null;
        int roomNumberChoice = 0;
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCheckIn(),checkInDate) && Objects.equals(customerReservation.getCheckOut(),checkOutDate)) {
                roomNumberChoice = customerReservation.getRoom().getRoomNumber();
                roomType = customerReservation.getRoom().getRoomType();
                reservations.remove(customerReservation);
            }
            if(roomType == SINGLE){
               return checkOutSingleRoom(roomNumberChoice);
            }
            else {
               return checkOutDoubleRoom(roomNumberChoice);
            }
        }

        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservations;
    }


    private ResponseForRoomBooking bookSingleRoom(int choice, int amount) {
        rooms[choice - 1] = 1;
        Room room = new Room();
        room.setRoomNumber(choice);
        room.setPrice(amount);

        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Room " + choice + " Booked Successfully");

        return responseForRoomBooking;
    }


    private ResponseForRoomBooking bookDoubleRoom(int choice, int amount) {
        rooms[choice - 1] = 1;
        Room room = new Room();
        room.setRoomNumber(choice);
        room.setPrice(amount);

        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Room " + choice + " Booked Successfully");

        return responseForRoomBooking;
    }


    private ResponseForRoomBooking checkOutSingleRoom(int choice) {

        if(rooms[choice -1] == 1){
            rooms[choice -1] = 0;
            Room room = new Room();
            room.setRoomNumber(0);
            room.setPrice(0);
        }

        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Room " + choice + " checkout Successful. Thank you for your patronage.");
        return responseForRoomBooking;
    }


    private ResponseForRoomBooking checkOutDoubleRoom(int choice) {

        if(rooms[choice -1] == 1){
            rooms[choice -1] = 0;
            Room room = new Room();
            room.setRoomNumber(0);
            room.setPrice(0);
        }
        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Room " + choice + " checkout Successful. Thank you for your patronage.");
        return responseForRoomBooking;
    }


    private ResponseForRoomBooking printBookedSingleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Booked Single Rooms are " + bookedRooms);
        return responseForRoomBooking;
    }


    private ResponseForRoomBooking printBookedDoubleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Booked Double Rooms are " + bookedRooms);
        return responseForRoomBooking;
    }


    private ResponseForRoomBooking printAvailableSingleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Available Single Rooms are " + availableRooms);
        return responseForRoomBooking;
    }


    private ResponseForRoomBooking printAvailableDoubleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        ResponseForRoomBooking responseForRoomBooking = new ResponseForRoomBooking();
        responseForRoomBooking.setMessage("Available Double Rooms are " + availableRooms);
        return responseForRoomBooking;
    }
    public List<Integer> listOfBookedSingleRooms(){
        List<Integer> bookedSingleRooms = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 1){
                bookedSingleRooms.add(i);
            }
        }

        return bookedSingleRooms;
    }

    public List<Integer> listOfBookedDoubleRooms(){
        List<Integer> bookedDoubleRooms = new ArrayList<>();

        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 1){
               bookedDoubleRooms.add(i);
            }
        }
        return bookedDoubleRooms;
    }

    public List<Integer> showAllRooms(){
        List<Integer> allRooms = new ArrayList<>();
        for (int i = 1; i <= rooms.length; i++) {
            if(rooms[i - 1] == 1 || rooms[i - 1] == 0){
                allRooms.add(i);
            }
        }
        return allRooms;
    }

    @Override
    public Reservation findReservationByEmail(String email) {
        for(Reservation customerReservation : reservations){
//            if(Objects.equals(customerReservation.getCustomer().getEmail(),email)) return customerReservation;
            if(customerReservation.getCustomer().getEmail().equals(email)) return customerReservation;
        }
        return null;
    }


}
