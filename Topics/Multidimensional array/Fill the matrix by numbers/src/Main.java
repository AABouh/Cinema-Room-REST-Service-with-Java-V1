import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        print2DArray(n);
    }

    public static void print2DArray(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){

                System.out.print(Math.abs(i - j));

                if(j < n-  1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}