package africa.semicoon.LegendaryHotels.dto.response;

import africa.semicoon.LegendaryHotels.models.Room;
import africa.semicoon.LegendaryHotels.models.RoomType;
import lombok.Data;

@Data
public class RoomResponse {
    private int roomNumber;
    private int roomPrice;
    private RoomType roomType;
    private String message;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("roomNumber = ").append(roomNumber);
        sb.append(", roomPrice = ").append(roomPrice);
        sb.append(", roomType = ").append(roomType);


        return sb.toString();
    }
}
