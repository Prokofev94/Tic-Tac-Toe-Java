package tictactoe;

import java.util.*;

public class Main {
    static boolean turnX = true;
    static int round = 0;
    public static void main(String[] args) {
        char[][] field = createField();
        showField(field);
        makeTurn(field);
    }

    public static char[][] createField() {
        char[][] field = new char[3][3];
        for (char[] c : field) {
            Arrays.fill(c, ' ');
        }
        return field;
    }

    public static void showField(char[][] field) {
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", field[0][0], field[0][1], field[0][2]);
        System.out.printf("| %s %s %s |\n", field[1][0], field[1][1], field[1][2]);
        System.out.printf("| %s %s %s |\n", field[2][0], field[2][1], field[2][2]);
        System.out.println("---------");
    }

    public static void makeTurn(char[][] field) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates:");
        String[] turn = scanner.nextLine().split(" ");
        if (turn[0].replace("[^\\d]", "").length() != turn[0].length() ||
            turn[turn.length - 1].replace("[^\\d]", "").length() != turn[turn.length - 1].length()) {
            System.out.println("You should enter numbers!");
            makeTurn(field);
        } else if (Integer.parseInt(turn[0]) < 1 || Integer.parseInt(turn[0]) > 3 ||
            Integer.parseInt(turn[turn.length - 1]) < 1 || Integer.parseInt(turn[turn.length - 1]) > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            makeTurn(field);
        } else if (field[Integer.parseInt(turn[0]) - 1][Integer.parseInt(turn[turn.length - 1]) - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            makeTurn(field);
        } else {
            int i = Integer.parseInt(turn[0]) - 1;
            int j = Integer.parseInt(turn[turn.length - 1]) - 1;
            field[i][j] = turnX ? 'X' : 'O';
            showField(field);
            checkResult(field);
        }
    }

    public static void checkResult(char[][] field) {
        char p = turnX ? 'X' : 'O';
        boolean win = field[0][0] == p && field[0][1] == p && field[0][2] == p ||
            field[1][0] == p && field[1][1] == p && field[1][2] == p ||
            field[2][0] == p && field[2][1] == p && field[2][2] == p ||
            field[0][0] == p && field[1][0] == p && field[2][0] == p ||
            field[0][1] == p && field[1][1] == p && field[2][1] == p ||
            field[0][2] == p && field[1][2] == p && field[2][2] == p ||
            field[0][0] == p && field[1][1] == p && field[2][2] == p ||
            field[0][2] == p && field[1][1] == p && field[2][0] == p;
        if (win) {
            System.out.println(p + " wins");
        } else {
            round++;
            if (round < 9) {
                turnX = !turnX;
                makeTurn(field);
            } else {
                System.out.println("Draw");
            }
        }
    }
}
