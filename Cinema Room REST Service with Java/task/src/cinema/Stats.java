package cinema;

public class Stats {
   private int income;
    private int available;

   private int purchased;

   public Stats(){}

    public int getAvailable() {
        return available;
    }

    public int getIncome() {
        return income;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void onSuccessfulPurchase(int ticketPrice) {
        this.available -= 1;
        this.income += ticketPrice;
        this.purchased += 1;
    }

    public void onSuccessfulReturn(int ticketPrice) {
        this.available += 1;
        this.income -= ticketPrice;
        this.purchased -= 1;
    }
}
