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
        sb.append("\nPlease find your reservation; ");

        //for (int i = 0; i < 50; i++) { System.out.print("*"); }
        sb.append("\nNAME - ").append(reservation.getCustomer().getFirstName()).append(" ").
                append(reservation.getCustomer().getLastName());
        //for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nROOM TYPE - ").append(reservation.getRoom().getRoomType());
        //for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nROOM CHOICE - ").append(reservation.getRoom().getRoomNumber());
        //for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nAMOUNT PAID - $").append(reservation.getRoom().getPrice());
        //for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nCHECK IN DATE - ").append(reservation.getCheckIn());
        //for (int i = 0; i < 50; i++) { System.out.print("-"); }
        sb.append("\nCHECK OUT DATE - ").append(reservation.getCheckOut()).append("\n");
        //for (int i = 0; i < 50; i++) { System.out.print("*"); }

        return sb.toString();

    }
}
