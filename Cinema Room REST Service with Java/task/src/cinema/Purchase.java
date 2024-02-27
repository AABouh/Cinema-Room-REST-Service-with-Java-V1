package cinema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Purchase {
    private int row;
    private int column;



    public Purchase( ){
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }


}
