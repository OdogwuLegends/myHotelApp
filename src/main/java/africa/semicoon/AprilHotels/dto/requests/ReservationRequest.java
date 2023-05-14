package africa.semicoon.AprilHotels.dto.requests;

import africa.semicoon.AprilHotels.models.RoomType;
import lombok.Data;

@Data
public class ReservationRequest {
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

}
