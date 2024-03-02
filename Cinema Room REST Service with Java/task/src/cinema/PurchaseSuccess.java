package cinema;

import java.util.UUID;

public class PurchaseSuccess {
    private UUID token;
    private Ticket ticket;

    public PurchaseSuccess(Ticket ticket){
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public UUID getToken() {
        return token;
    }
}
