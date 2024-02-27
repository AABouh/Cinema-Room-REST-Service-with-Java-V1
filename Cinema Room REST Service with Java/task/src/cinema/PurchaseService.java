package cinema;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PurchaseService {
    private final Set<Seat> available = ConcurrentHashMap.newKeySet();
    private final Set<Seat> sold = ConcurrentHashMap.newKeySet();

    private int maxRow;
    private int maxColumn;

    public PurchaseService(@Value("${cinema.rows}") int rows, @Value("${cinema.columns}") int columns) {
        initializeSeats(new Seats(rows, columns));
        this.maxRow = rows;
        this.maxColumn = columns;
    }

    private void initializeSeats(Seats seats) {
        available.addAll(seats.getSeats());
    }

    public boolean checkAvailability(Seat seat) {
        return available.contains(seat);
    }

    public int successfulPurchase(Seat seat) {
        if(seat.getRow() > maxRow || seat.getColumn() > maxColumn || seat.getRow() < 1 || seat.getColumn() > 1 ){
            throw new OutOfBoundsException("The number of a row or a column is out of bounds!");
        }
        if (checkAvailability(seat)) {
            sold.add(seat);
            available.remove(seat);
            return seat.getPrice();
        } else{
            throw new SeatAlreadyPurchasedException("The ticket has been already purchased!");
        }
    }
}
