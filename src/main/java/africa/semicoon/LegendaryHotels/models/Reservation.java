package africa.semicoon.LegendaryHotels.models;

import lombok.Data;
import java.util.Date;

@Data
public class Reservation {
    private Customer customer;
    private Room room;
    private RoomType roomType;
    private Date checkIn;
    private Date checkOut;

}
