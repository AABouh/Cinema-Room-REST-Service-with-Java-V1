class ArrayOperations {
    public static int[][][] createCube() {
        // write your code here
        int[][][] cube = new int[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    if (j == 0) {
                        cube[i][j][k] = k;
                    }
                    else if (j == 1) {
                        cube[i][j][k] = k + 3;
                    }
                    else if (j == 2) {
                        cube[i][j][k] = k + 6;
                    }
                }
            }
        }

        return cube;

    }
}