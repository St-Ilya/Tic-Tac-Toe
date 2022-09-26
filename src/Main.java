import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> player1_Positions = new ArrayList<>();
    static ArrayList<Integer> player2_Positions = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameField = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGamefield(gameField);

        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Player 1, please select your placement (1-9): ");
            int pos_player1 = scanner.nextInt();
            while(player1_Positions.contains(pos_player1) || player2_Positions.contains(pos_player1)) {
                System.out.println("Position taken!");
                pos_player1 = scanner.nextInt();
            }

            selectPosition(gameField, pos_player1, "player1");
            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            printGamefield(gameField);

            System.out.println("Player 2, please select your placement (1-9): ");
            int pos_player2 = scanner.nextInt();
            while(player1_Positions.contains(pos_player2) || player2_Positions.contains(pos_player2)) {
                System.out.println("Position taken!");
                pos_player2 = scanner.nextInt();
            }
            selectPosition(gameField, pos_player2, "player2");

            printGamefield(gameField);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }

    public static void printGamefield(char[][] gameField) {
        for(char[] row : gameField) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void selectPosition(char[][] gameField, int position_player, String user) {

        char symbol = ' ';

        if(user.equals("player1")) {
            symbol = 'X';
            player1_Positions.add(position_player);
        } else if (user.equals("player2")) {
            symbol = 'O';
            player2_Positions.add(position_player);
        }

        switch (position_player) {
            case 1:
                gameField[0][0] = symbol;
                break;
            case 2:
                gameField[0][2] = symbol;
                break;
            case 3:
                gameField[0][4] = symbol;
                break;
            case 4:
                gameField[2][0] = symbol;
                break;
            case 5:
                gameField[2][2] = symbol;
                break;
            case 6:
                gameField[2][4] = symbol;
                break;
            case 7:
                gameField[4][0] = symbol;
                break;
            case 8:
                gameField[4][2] = symbol;
                break;
            case 9:
                gameField[4][4] = symbol;
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol= Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1= Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning) {
            if(player1_Positions.containsAll(l)) {
                return "Player1 win!!!";
            } else if(player2_Positions.containsAll(l)) {
                return "Player2 win!!!";
            } else if(player1_Positions.size() + player2_Positions.size() == 9) {
                return "Dead heat!";
            }
        }

        return "";
    }

}