package cinema;

public class Ticket {
    private int row;
    private int column;
    private int price;

    public Ticket(int row, int column, int price){
        this.row = row;
        this.column = column;
        this.price = price;

    }

    public int getPrice() {
        return price;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
