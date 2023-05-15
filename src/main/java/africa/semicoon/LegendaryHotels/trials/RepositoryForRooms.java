//package africa.semicoon.AprilHotels.trials;
//
//import africa.semicoon.AprilHotels.models.Room;
//import africa.semicoon.AprilHotels.repositories.IRoom;
//
//import java.util.*;
//
//import static africa.semicoon.AprilHotels.utils.AppUtils.ONE;
//
//public class RepositoryForRooms  {
//    private List<Room> roomList = new ArrayList<>();
//    private int id;
//
//    public Room saveRoom(Room room) {
//        room.setRoomNumber(generateRoomNumber());
//        roomList.add(room);
//        id++;
//        return room;
//    }
//
//    @Override
//    public Room findByRoomNumber(int roomNumber) {
//        for (Room eachRoom : roomList){
//            if(Objects.equals(eachRoom.getRoomNumber(),roomNumber))
//                return eachRoom;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Room> findAllRooms() {
//        return roomList;
//    }
//
//    @Override
//    public void removeRoomByRoomNumber(int roomNumber) {
//        Room foundRoom = findByRoomNumber(roomNumber);
//        if(foundRoom != null) roomList.remove(foundRoom);
//    }
//
//    @Override
//    public Room addRoom(Room newRoom) {
//        roomList.add(newRoom);
//        return newRoom;
//    }
//    private int generateRoomNumber(){ return id + ONE; }
//
//
//}
