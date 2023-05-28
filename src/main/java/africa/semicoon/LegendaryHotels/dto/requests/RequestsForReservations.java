package africa.semicoon.LegendaryHotels.dto.requests;

import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;
import lombok.Data;

@Data
public class RequestsForReservations {
//    private String firstName;
//    private String lastName;
//    private String email;
//    private RoomType roomType;
//    private int roomChoice;
//    private int checkInDate;
//    private int checkInMonth;
//    private int checkInYear;
//    private int checkOutDate;
//    private int checkOutMonth;
//    private int checkOutYear;
//    private int amount;

    private Customer customer;
    private Room room;
//    private int checkInDate;
//    private int checkInMonth;
//    private int checkInYear;
//    private int checkOutDate;
//    private int checkOutMonth;
//    private int checkOutYear;
    private String checkIn;
    private String checkOut;
    private Reservation reservation;

}
