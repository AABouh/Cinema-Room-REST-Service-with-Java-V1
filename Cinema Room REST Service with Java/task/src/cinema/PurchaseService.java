package cinema;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PurchaseService {
    private final Set<Seat> available = ConcurrentHashMap.newKeySet();
    private final Map<Seat, PurchaseSuccess> sold = new ConcurrentHashMap<>();

    private final int maxRow;
    private final int maxColumn;

    private final Stats stats;

    public PurchaseService(@Value("${cinema.rows}") int rows, @Value("${cinema.columns}") int columns) {
        Seats seats = new Seats(rows, columns);

        this.stats = new Stats();
        initializeSeats(seats);
        initializeStats(seats);

        this.maxRow = rows;
        this.maxColumn = columns;

    }

    public Map<Seat, PurchaseSuccess> getSold() {
        return sold;
    }

    public Set<Seat> getAvailable() {
        return available;
    }

    private void initializeSeats(Seats seats) {
        available.addAll(seats.getSeats());

    }

    private void initializeStats(Seats seats){
        stats.setIncome(0);
        stats.setAvailable(seats.getSeats().size());
        stats.setPurchased(0);
    }

    public boolean checkAvailability(Seat seat) {
        return available.contains(seat);
    }

    public PurchaseSuccess getSoldInfo (Seat seat){ return sold.get(seat); }

    public Stats getStats() {
        return stats;
    }

    public PurchaseSuccess successfulPurchase(Seat seat) {
        if(seat.getRow() > maxRow || seat.getColumn() > maxColumn || seat.getRow() < 1 || seat.getColumn() < 1 ){
            throw new OutOfBoundsException("The number of a row or a column is out of bounds!");
        }
        if (checkAvailability(seat)) {

            Ticket ticket = new Ticket(seat.getRow(), seat.getColumn(),seat.getPrice());
            PurchaseSuccess purchaseSuccess = new PurchaseSuccess(ticket);
            sold.put(seat, purchaseSuccess);
            available.remove(seat);
            stats.onSuccessfulPurchase(seat.getPrice());
            return purchaseSuccess;
        } else{
            throw new SeatAlreadyPurchasedException("The ticket has been already purchased!");
        }
    }
}
