package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class Seat {
    private final int row;
    private final int column;
    private final int price;

    public Seat(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }


    public int getColumn() {
        return column;
    }


    public int getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Seat seat = (Seat) object;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
