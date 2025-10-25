package edu.io;

import java.util.Scanner;
import edu.io.token.Token;

public class Main {
    private static Board board;
    private static int playerCol;
    private static int playerRow;
    
    public static void main(String[] args) {
        System.out.println("Gold Rush");
        System.out.println("___________________________");
        
        initGame();
        
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        
        while (gameRunning) {
            showMenu();
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    board.display();
                    break;
                case "2":
                    movePlayer(scanner);
                    break;
                case "3":
                    gameRunning = false;
                    System.out.println("Koniec gry");
                    break;
                default:
                    System.out.println("Niepoprawny wybór, wybierz 1, 2 lub 3");
            }
        }
        
        scanner.close();
    }
    
    private static void initGame() {
        board = new Board();
        playerCol = 3;
        playerRow = 4;
        
        board.placeToken(playerCol, playerRow, Token.createPlayer());
        
        board.placeToken(1, 1, Token.createGold());
        board.placeToken(6, 2, Token.createGold());
        board.placeToken(4, 6, Token.createGold());
        board.placeToken(7, 7, Token.createGold());
        board.placeToken(0, 5, Token.createGold());
        
        System.out.println("Gra gotowa do rozpoczęcia!");
    }
    
    private static void showMenu() {
        System.out.println("\n=== GOLD RUSH MENU ===");
        System.out.println("1 - Pokaż planszę");
        System.out.println("2 - Wykonaj ruch");
        System.out.println("3 - Wyjdź z gry");
        System.out.print("Wybierz opcję: ");
    }
    
    private static void movePlayer(Scanner scanner) {
        System.out.println("\nZasady poruszania się:");
        System.out.println("W - góra, S - dół, A - lewo, D - prawo");
        System.out.print("Wybierz kierunek: ");
        String direction = scanner.nextLine().toUpperCase();
        
        int newCol = playerCol;
        int newRow = playerRow;
        
        switch (direction) {
            case "W":
                newRow--;
                break;
            case "S":
                newRow++;
                break;
            case "A":
                newCol--;
                break;
            case "D":
                newCol++;
                break;
            default:
                System.out.println("Nie ma takiej opcji, wprowadź W lub S lub A lub D");
                return;
        }
        
        if (!board.isValidPos(newCol, newRow)) {
            System.out.println("Nie można wyjść poza planszę");
            return;
        }
        
        Token target = board.getSquare(newCol, newRow);
        
        if (target.isGold()) {
            System.out.println("Znalazłeś złoto");
        }
        
        board.placeToken(playerCol, playerRow, Token.createEmpty());
        board.placeToken(newCol, newRow, Token.createPlayer());
        
        playerCol = newCol;
        playerRow = newRow;
        
        System.out.println("Jesteś na pozycji (" + playerCol + ", " + playerRow + ")");
        
        if (target.isGold()) {
            System.out.println("Zdobyłeś punkt złota. Kontynuuj poszukiwania");
        }
    }
}