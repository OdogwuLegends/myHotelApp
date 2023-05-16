//package africa.semicoon.LegendaryHotels.trials;
//
//import africa.semicoon.LegendaryHotels.exceptions.InvalidRoomNumberException;
//import africa.semicoon.LegendaryHotels.exceptions.RoomUnavailableException;
//import africa.semicoon.LegendaryHotels.models.Room;
//
//import static africa.semicoon.LegendaryHotels.utils.AppUtils.FIFTY_DOLLARS;
//import static africa.semicoon.LegendaryHotels.utils.AppUtils.TWENTY_DOLLARS;
//
//public class Methods {
//
//    private String bookSingleRoom(int choice) throws RoomUnavailableException {
//        if(choice < 1 || choice > 10){
//            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
//        }
//        if(rooms[choice -1] == 1){
//            throw new RoomUnavailableException("Room " + choice + " is already booked.");
//        }
//        rooms[choice - 1] = 1;
//        roomNumber = choice;
//        Room room = new Room();
//        room.setRoomNumber(roomNumber);
//        room.setPrice(TWENTY_DOLLARS);
//        return "Room " + choice + " Booked Successfully";
//    }
//
//
//    private String bookDoubleRoom(int choice) throws RoomUnavailableException {
//        if(choice < 11 || choice > rooms.length){
//            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
//        }
//        if(rooms[choice -1] == 1){
//            throw new RoomUnavailableException("Room " + choice + " is already booked.");
//        }
//        rooms[choice - 1] = 1;
//        roomNumber = choice;
//        Room room = new Room();
//        room.setRoomNumber(roomNumber);
//        room.setPrice(FIFTY_DOLLARS);
//        return "Room " + choice + " Booked Successfully";
//    }
//
//
//    private String checkOutSingleRoom(int choice) throws InvalidRoomNumberException{
//        if(choice < 1 || choice > 10){
//            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
//        }
//        if(rooms[choice -1] == 1){
//            rooms[choice -1] = 0;
//            roomNumber = 0;
//            Room room = new Room();
//            room.setRoomNumber(roomNumber);
//            room.setPrice(0);
//            return "Room " + choice + " checkout Successful. Thank you for your patronage.";
//        }
//        return "Room " + choice + " not booked previously.";
//    }
//
//
//    private String checkOutDoubleRoom(int choice) {
//        if(choice < 11 || choice > rooms.length){
//            throw new InvalidRoomNumberException("Invalid Room Number Selected.");
//        }
//        if(rooms[choice -1] == 1){
//            rooms[choice -1] = 0;
//            roomNumber = 0;
//            Room room = new Room();
//            room.setRoomNumber(roomNumber);
//            room.setPrice(0);
//            return "Room " + choice + " checkout Successful. Thank you for your patronage.";
//        }
//        return "Room " + choice + " not booked previously.";
//    }
//
//
//    private String printBookedSingleRooms() {
//        StringBuilder bookedRooms = new StringBuilder();
//        for (int i = 1; i <= 10; i++) {
//            if(rooms[i - 1] == 1){
//                bookedRooms.append(i);
//                bookedRooms.append(", ");
//            }
//        }
//        return "Booked Single Rooms are " + bookedRooms;
//    }
//
//
//    private String printBookedDoubleRooms() {
//        StringBuilder bookedRooms = new StringBuilder();
//        for (int i = 11; i <= rooms.length; i++) {
//            if(rooms[i - 1] == 1){
//                bookedRooms.append(i);
//                bookedRooms.append(", ");
//            }
//        }
//        return "Booked Double Rooms are " + bookedRooms;
//    }
//
//
//    private String printAvailableSingleRooms() {
//        StringBuilder availableRooms = new StringBuilder();
//        for (int i = 1; i <= 10; i++) {
//            if(rooms[i - 1] == 0){
//                availableRooms.append(i);
//                availableRooms.append(", ");
//            }
//        }
//        return "Available Single Rooms are " + availableRooms;
//    }
//
//
//    private String printAvailableDoubleRooms() {
//        StringBuilder availableRooms = new StringBuilder();
//        for (int i = 11; i <= rooms.length; i++) {
//            if(rooms[i - 1] == 0){
//                availableRooms.append(i);
//                availableRooms.append(", ");
//            }
//        }
//        return "Available Double Rooms are " + availableRooms;
//    }
//}
