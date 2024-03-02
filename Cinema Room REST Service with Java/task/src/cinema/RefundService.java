package cinema;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class RefundService {

    private Map<Seat, PurchaseSuccess> sold;
    private Set<Seat> available;

    private Stats stats;


    public RefundService(PurchaseService purchaseService) {
        this.sold = purchaseService.getSold();
        this.available = purchaseService.getAvailable();
        this.stats = purchaseService.getStats();
    }

    public boolean isTokenKnown(UUID tokenToCheck) {
        for (PurchaseSuccess purchase : sold.values()) {
            if (tokenToCheck.equals(purchase.getToken())) {
                return true;
            }
        }
        return false;
    }

    public Ticket successfulReturn(UUID tokenToCheck) {
        if (!isTokenKnown(tokenToCheck)) {
            throw new WrongTokenException("wrong token!");
        }

        Seat seatToRefund = null;
        for (Map.Entry<Seat, PurchaseSuccess> entry : sold.entrySet()) {
            if (tokenToCheck.equals(entry.getValue().getToken())) {
                seatToRefund = entry.getKey();
                break;
            }
        }

        if (seatToRefund != null) {

            PurchaseSuccess purchaseSuccess = sold.remove(seatToRefund);
            available.add(seatToRefund);
            stats.onSuccessfulReturn(seatToRefund.getPrice());
            return purchaseSuccess.getTicket();
        }

        throw new IllegalStateException("Something bad happened in the refund");
    }
}
