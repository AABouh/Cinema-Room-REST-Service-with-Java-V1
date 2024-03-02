
class NumbersThread extends Thread {
    public int from;
    public int to;

    public NumbersThread(int from, int to) {
        // implement the constructor
        super();
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            System.out.println(i);
        }
    }
    // you should override some method here                                                   
}