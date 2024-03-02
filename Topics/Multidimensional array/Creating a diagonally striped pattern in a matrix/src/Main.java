import java.util.Scanner;

public class Main {

    public static void printPattern(int n) {
        // You should declare a two-dimensional array here.
        char[][] twoDimArray = new char[n][n];

        // You should write a nested for loop to iterate over element in the array.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // In the for loop you can identify the positions that should hold '#' and ' '.
                if (i == j || i + j  == twoDimArray.length -1 ) {
                    twoDimArray[i][j] = '#';
                }else {
                    twoDimArray[i][j] = ' ';
                }
            }
        }


        for (char[] row : twoDimArray) {
            for (char element : row) {
                // You should print out the elements of each row of the array. Use 'System.out.print'
                // to avoid automatic newline after every element and 'System.out.println'
                System.out.print(element);
            }
            // at the end of every row to print a newline.
            System.out.println();
        }


    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        printPattern(n);
    }
}