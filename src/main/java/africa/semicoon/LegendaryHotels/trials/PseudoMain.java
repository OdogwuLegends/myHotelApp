package africa.semicoon.LegendaryHotels.trials;

import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;

public class PseudoMain {
    public static void main(String[] args) {
//        IRoomRep reservations = new RepositoryForSingleRooms();
//
//        try{
//            System.out.println(reservations.bookRoom(3));
//            System.out.println(reservations.bookRoom(6));
//            System.out.println(reservations.bookRoom(8));
//            System.out.println(reservations.bookRoom(19));
//        }catch (RoomUnavailableException | InvalidRoomNumberException ex){
//            System.out.println(ex.getMessage());
//    }
//        System.out.println(reservations.printBookedRooms());
//        System.out.println(reservations.printAvailableRooms());
//        System.out.println(reservations.checkOut(6));
//        System.out.println(reservations.printBookedRooms());
//        System.out.println(reservations.printAvailableRooms());

        Trial4 tryTo = new Trial4();

        System.out.println(tryTo.printAvailableSingleRooms());
        System.out.println(tryTo.printAvailableDoubleRooms());
        System.out.println();

        try {
            System.out.println(tryTo.bookSingleRoom(5));
            System.out.println(tryTo.bookSingleRoom(6));
            System.out.println(tryTo.bookDoubleRoom(12));
            System.out.println(tryTo.bookDoubleRoom(19));
        } catch (RoomUnavailableException | InvalidRoomNumberException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println();
        System.out.println(tryTo.printAvailableSingleRooms());
        System.out.println(tryTo.printAvailableDoubleRooms());
        System.out.println();


        try {
//            System.out.println(tryTo.checkOutSingleRoom(6));
//            System.out.println(tryTo.checkOutSingleRoom(3));
//            System.out.println(tryTo.checkOutSingleRoom(12));
//            System.out.println(tryTo.checkOutDouble(19));
//            System.out.println(tryTo.checkOutDouble(3));
           System.out.println(tryTo.checkOutSingleRoom(20));
            //System.out.println(tryTo.checkOutDouble(15));
        } catch (InvalidRoomNumberException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
