package africa.semicoon.LegendaryHotels.exceptions;

public class InvalidRoomNumberException extends IllegalArgumentException{

    public InvalidRoomNumberException(String message){
        super(message);
    }
}
