package africa.semicoon.LegendaryHotels.dto.response;

import africa.semicoon.LegendaryHotels.models.Customer;
import africa.semicoon.LegendaryHotels.models.Reservation;
import africa.semicoon.LegendaryHotels.models.Room;
import lombok.Data;

@Data
public class ResponseForReservation {
    private String message;
    private Customer customer;
    private Room room;
    private Reservation reservation;


    @Override
    public String toString(){
        final StringBuffer sb = new StringBuffer();
        sb.append("\nPlease find your reservation");
//
//        for (int i = 0; i < 50; i++) { System.out.print("*"); }
//        sb.append("\nName:: ").append(customer.getFirstName()).append(" ").append(customer.getLastName());
//        for (int i = 0; i < 50; i++) { System.out.print("-"); }
//        sb.append("\nRoom Type - ").append(room.getRoomType()).append(".");
//        for (int i = 0; i < 50; i++) { System.out.print("-"); }
//        sb.append("\nRoom Choice - ").append(room.getRoomNumber()).append(".");
//        for (int i = 0; i < 50; i++) { System.out.print("-"); }
//        sb.append("\nAmount paid - ").append(room.getPrice()).append(".");
//        for (int i = 0; i < 50; i++) { System.out.print("-"); }
//        sb.append("\nCheck in date - ").append(reservation.getCheckIn()).append(".");
//        for (int i = 0; i < 50; i++) { System.out.print("-"); }
//        sb.append("\nCheck out date - ").append(reservation.getCheckOut()).append(".");
//        for (int i = 0; i < 50; i++) { System.out.print("*"); }


        for (int i = 0; i < 50; i++) { System.out.print("*"); }
        sb.append("\nName:: ").append(reservation.getCustomer().getLastName()).append(" ").
                append(reservation.getCustomer().getLastName());
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nRoom Type - ").append(reservation.getRoom().getRoomType()).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nRoom Choice - ").append(reservation.getRoom().getRoomNumber()).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nAmount paid - ").append(reservation.getRoom().getPrice()).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nCheck in date - ").append(reservation.getCheckIn()).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nCheck out date - ").append(reservation.getCheckOut()).append(".");
        for (int i = 0; i < 50; i++) { System.out.print("*"); }

        return sb.toString();

    }
}
