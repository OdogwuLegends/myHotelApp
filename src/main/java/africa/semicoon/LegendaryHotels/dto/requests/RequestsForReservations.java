package africa.semicoon.LegendaryHotels.dto.requests;

import africa.semicoon.LegendaryHotels.models.RoomType;
import lombok.Data;

@Data
public class RequestsForReservations {
    private String firstName;
    private String lastName;
    private String email;
    private RoomType roomType;
    private int roomChoice;
    private String checkInDate;
    private String checkInMonth;
    private String checkInYear;
    private String checkOutDate;
    private String checkOutMonth;
    private String checkOutYear;
    private int amount;

}
