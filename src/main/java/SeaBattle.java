import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class SeaBattle {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

//        int tableHeight = 4;
//        int tableWidth = 4;

        int[][] userMatrix = new int[4][4];
        int[][] compMatrix = new int[4][4];

        System.out.println("Please choose 4 cells for game in format [x,y]. Possible values 0 - 3");

        int userCount = 0;
        while (userCount < 4) {
            String coordinate;
            coordinate = scan.next();
            int x = coordinate.toCharArray()[1] - '0';
            int y = coordinate.toCharArray()[3] - '0';

            if (userMatrix[x][y] == 1) {
                System.out.println("This cell is already choosen. Please, choose another one");
            } else if ((x >= 0) && (x <= 3) && (y >= 0) && (y <= 3)) {
                userMatrix[x][y] = 1;
                userCount++;
            } else {
                System.out.println("Incorrect coordinate. Try again. Possible values 0 - 3");
            }
        }


        int count = 0;
        while (count < 4) {
            int x = random.nextInt(4);
            int y = random.nextInt(4);

            if (compMatrix[x][y] == 0) {
                compMatrix[x][y] = 1;
                count++;
            }
        }

/*        for (int i = 0; i < tableHeight; i++) {
            for (int j = 0; j < tableWidth; j++) {
                System.out.print(compMatrix[i][j] + " ");
            }
            System.out.println();
        } */

        int resultUser = 0;
        int resultComp = 0;

        while (!(resultUser == 4) && !(resultComp == 4)) {
            int flag = 0;
            int userX;
            int userY;

            do {
                flag = 0;
                System.out.println("Your shoot. In format [x,y]. Possible values 0 - 3");
                String coordinate;
                coordinate = scan.next();
                userX = coordinate.toCharArray()[1] - '0';
                userY = coordinate.toCharArray()[3] - '0';

                if (!((userX >= 0) && (userX <= 3) && (userY >= 0) && (userY <= 3))) {
                    System.out.println("Incorrect coordinate. Try again. Possible values 0 - 3");
                    flag = 1;
                }
            } while (flag == 1);

            switch (compMatrix[userX][userY]) {
                case 0: {
                    System.out.println("No, sorry :(");
                    compMatrix[userX][userY] = -1;

                    int nearX_ = max(userX - 1, 0);
                    int nearY_ = max(userY - 1, 0);
                    int nearX = min(userX + 2, 4);
                    int nearY = min(userY + 2, 4);

                    int near = 0;

                    for (int j = nearX_; j < nearX; j++) {
//                        System.out.println(j + " ");
                        for (int k = nearY_; k < nearY; k++) {
//                            System.out.print(k + " ");
                            if (compMatrix[j][k] == 1) {
                                near++;
                            }
                        }
//                        System.out.println();
                    }

                    if (near > 0) {
                        System.out.println("Ooo! The goal is near!");
                    }
                    break;
                }
                case 1: {
                    System.out.println("Yes!");
                    compMatrix[userX][userY] = 10;
                    resultUser++;
                    break;
                }
                case 10: {
                    System.out.println("Oops, this cell have already shooted");
                    break;
                }
                case -1: {
                    System.out.println("Oops, this cell have already shooted");
                    break;
                }
            }

            int compX = random.nextInt(4);
            int compY = random.nextInt(4);

            System.out.println("Comp shoot - " + "[" + compX + "," + compY + "] = " + userMatrix[compX][compY]);

            switch (userMatrix[compX][compY]) {
                case 0: {
                    System.out.println("No, sorry :(");
                    userMatrix[compX][compY] = -1;
                    break;
                }
                case 1: {
                    System.out.println("Yes!");
                    userMatrix[compX][compY] = 10;
                    resultComp++;
                    break;
                }
                case 10: {
                    System.out.println("Oops, this cell have already shooted");
                    break;
                }
                case -1: {
                    System.out.println("Oops, this cell have already shooted");
                    break;
                }
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        if (resultUser == 4) {
            System.out.println("You win!!!");
        } else if (resultComp == 4) {
            System.out.println("Computer win");
        }
    }
}