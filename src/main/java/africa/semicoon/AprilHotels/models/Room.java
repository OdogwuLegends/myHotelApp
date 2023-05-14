package africa.semicoon.AprilHotels.models;

import lombok.Data;

@Data
public class Room {
    private int roomNumber;
    private int price;
    private RoomType roomType;

}
