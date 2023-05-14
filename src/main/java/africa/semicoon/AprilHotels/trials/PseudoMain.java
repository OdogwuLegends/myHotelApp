package africa.semicoon.AprilHotels.trials;

import africa.semicoon.AprilHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.AprilHotels.exceptions.RoomUnavailableException;
import africa.semicoon.AprilHotels.repositories.IRoom;
import africa.semicoon.AprilHotels.repositories.RepositoryForSingleRooms;

public class PseudoMain {
    public static void main(String[] args) {
        IRoom reservations = new RepositoryForSingleRooms();

        try{
            System.out.println(reservations.bookRoom(3));
            System.out.println(reservations.bookRoom(6));
            System.out.println(reservations.bookRoom(8));
            System.out.println(reservations.bookRoom(19));
        }catch (RoomUnavailableException | InvalidRoomNumberException ex){
            System.out.println(ex.getMessage());
        }



        System.out.println(reservations.printBookedRooms());
        System.out.println(reservations.printAvailableRooms());
        System.out.println(reservations.checkOut(6));
        System.out.println(reservations.printBookedRooms());
        System.out.println(reservations.printAvailableRooms());



    }
}
