//package africa.semicoon.AprilHotels.repositories;
//
//import africa.semicoon.AprilHotels.models.Room;
//import africa.semicoon.AprilHotels.models.RoomType;
//import africa.semicoon.AprilHotels.trials.RepositoryForRooms;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static africa.semicoon.AprilHotels.utils.AppUtils.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class RepositoryForRoomsTest {
//    private final IRoom iRoom = new RepositoryForRooms();
//    private Room firstRoom;
//    private Room secondRoom;
//    private Room thirdRoom;
//
//    @BeforeEach
//    void setUp(){
//        firstRoom = buildFirstRoom();
//        secondRoom = buildSecondRoom();
//        thirdRoom = buildThirdRoom();
//
//        iRoom.saveRoom(firstRoom);
//        iRoom.saveRoom(secondRoom);
//        iRoom.saveRoom(thirdRoom);
//    }
//
//    @Test
//    void testToSaveRoom(){
//        List<Room> allRooms = iRoom.findAllRooms();
//        int actual = allRooms.size();
//        assertEquals(THREE, actual);
//    }
//    @Test
//    void testToFindRoomByRoomNumber(){
//        Room foundRoom = iRoom.findByRoomNumber(secondRoom.getRoomNumber());
//        assertSame(foundRoom,secondRoom);
//        assertEquals(foundRoom.getRoomNumber(),secondRoom.getRoomNumber());
//    }
//    @Test
//    void testToFindAllRooms(){
//        List<Room> allRooms = iRoom.findAllRooms();
//        int actual = allRooms.size();
//        assertEquals(THREE, actual);
//
//        assertNotNull(thirdRoom);
//
//        int foundRoom = firstRoom.getPrice();
//        assertEquals(FIFTY_DOLLARS,foundRoom);
//    }
//    @Test
//    void testToRemoveRoomByRoomNumber(){
//        List<Room> allRooms = iRoom.findAllRooms();
//        int actual = allRooms.size();
//        assertEquals(THREE, actual);
//
//        iRoom.removeRoomByRoomNumber(thirdRoom.getRoomNumber());
//        actual = allRooms.size();
//        assertEquals(TWO, actual);
//    }
//
//    @Test
//    void testToAddRoom(){
//        Room roomToAdd = new Room();
//        Room savedRoom = iRoom.saveRoom(roomToAdd);
//        assertNotNull(savedRoom);
//
//        List<Room> allRooms = iRoom.findAllRooms();
//        int actual = allRooms.size();
//        assertEquals(FOUR,actual);
//    }
//
//
//
//    private Room buildFirstRoom(){
//        Room room = new Room();
//        room.setRoomNumber(ONE);
//        room.setRoomType(RoomType.DOUBLE);
//        room.setPrice(FIFTY_DOLLARS);
//
//        return room;
//    }
//    private Room buildSecondRoom(){
//        Room room = new Room();
//        room.setRoomNumber(TWO);
//        room.setRoomType(RoomType.SINGLE);
//        room.setPrice(TWENTY_DOLLARS);
//
//        return room;
//    }
//    private Room buildThirdRoom(){
//        Room room = new Room();
//        room.setRoomNumber(THREE);
//        room.setRoomType(RoomType.SINGLE);
//        room.setPrice(TWENTY_DOLLARS);
//
//        return room;
//    }
//
//}