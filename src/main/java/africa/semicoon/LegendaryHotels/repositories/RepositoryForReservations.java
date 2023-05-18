package africa.semicoon.LegendaryHotels.repositories;

import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RepositoryForReservations implements IReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();
    private int[] rooms = new int[20];


    @Override
    public String findARoom(RoomType roomType) {
        if(roomType == RoomType.SINGLE){ return printAvailableSingleRooms(); }
        else { return printAvailableDoubleRooms(); }
    }

    @Override
    public String reserveARoom(Reservation customerReservation, RoomType roomType, int roomNumberChoice,int amount) {
        if(roomType == RoomType.SINGLE){
            reservations.add(customerReservation);
            return bookSingleRoom(roomNumberChoice, amount);
        }
        else {
            reservations.add(customerReservation);
            return bookDoubleRoom(roomNumberChoice, amount);
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
        }
        return null;
    }

    @Override
    public Reservation checkOut(Date checkInDate, Date checkOutDate) {
        RoomType roomType = null;
        int roomNumberChoice = 0;
        for (Reservation customerReservation : reservations){
            if(Objects.equals(customerReservation.getCheckIn(),checkInDate) && Objects.equals(customerReservation.getCheckOut(),checkOutDate)) {
                roomNumberChoice = customerReservation.getRoom().getRoomNumber();
                roomType = customerReservation.getRoomType();

            }
            if(roomType == RoomType.SINGLE){
                checkOutSingleRoom(roomNumberChoice);
            }
            else {
                checkOutDoubleRoom(roomNumberChoice);
            }
            reservations.remove(customerReservation);
        }
        return null;
    }

    @Override
    public List<Reservation> printReservations() {
        return reservations;
    }


    private String bookSingleRoom(int choice, int amount) {
        rooms[choice - 1] = 1;
        Room room = new Room();
        room.setRoomNumber(choice);
        room.setPrice(amount);
        return "Room " + choice + " Booked Successfully\n";
    }


    private String bookDoubleRoom(int choice, int amount) {
        rooms[choice - 1] = 1;
        Room room = new Room();
        room.setRoomNumber(choice);
        room.setPrice(amount);
        return "Room " + choice + " Booked Successfully\n";
    }


    private String checkOutSingleRoom(int choice) {

        if(rooms[choice -1] == 1){
            rooms[choice -1] = 0;
            Room room = new Room();
            room.setRoomNumber(0);
            room.setPrice(0);
        }
        return "Room " + choice + " checkout Successful. Thank you for your patronage.\n";
    }


    private String checkOutDoubleRoom(int choice) {

        if(rooms[choice -1] == 1){
            rooms[choice -1] = 0;
            Room room = new Room();
            room.setRoomNumber(0);
            room.setPrice(0);
        }
        return "Room " + choice + " checkout Successful. Thank you for your patronage.\n";
    }


    private String printBookedSingleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Single Rooms are " + bookedRooms + " \n";
    }


    private String printBookedDoubleRooms() {
        StringBuilder bookedRooms = new StringBuilder();
        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 1){
                bookedRooms.append(i);
                bookedRooms.append(", ");
            }
        }
        return "Booked Double Rooms are " + bookedRooms + " \n";
    }


    private String printAvailableSingleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if(rooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Single Rooms are " + availableRooms + " \n";
    }


    private String printAvailableDoubleRooms() {
        StringBuilder availableRooms = new StringBuilder();
        for (int i = 11; i <= rooms.length; i++) {
            if(rooms[i - 1] == 0){
                availableRooms.append(i);
                availableRooms.append(", ");
            }
        }
        return "Available Double Rooms are " + availableRooms + " \n";
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


}
