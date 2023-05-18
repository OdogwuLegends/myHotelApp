package africa.semicoon.LegendaryHotels.dto.response;

import africa.semicoon.LegendaryHotels.models.RoomType;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseForReservation {
    private String firstName;
    private String lastName;
    private RoomType roomType;
    private int roomChoice;
    private int amount;
    private Date checkInDate;
    private Date checkOutDate;


    @Override
    public String toString(){
        final StringBuffer sb = new StringBuffer();
        sb.append("\nPlease find your reservation");

        for (int i = 0; i < 50; i++) { System.out.print("*"); }
        sb.append("\nName:: ").append(firstName).append(" ").append(lastName);
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nRoom Type - ").append(roomType).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nRoom Choice - ").append(roomChoice).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nAmount paid - ").append(amount).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nCheck in date - ").append(checkInDate).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nCheck out date - ").append(checkOutDate).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("*"); }

        return sb.toString();

    }
}
