package edu.io;

import java.util.Scanner;

import edu.io.player.Player;
import edu.io.token.AnvilToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.WaterToken;

public class Game {
    private final Board board = new Board();
    private final PlacementStrategy strategy;
    private Player player;

    public Game() {
        this(new SimplePlacement());
    }

    public Game(PlacementStrategy strategy) {
        this.strategy = strategy;
        initializeBoard();
    }

    private void initializeBoard() {
        board.placeToken(2, 2, new GoldToken());
        board.placeToken(5, 3, new GoldToken(2.0));
        board.placeToken(1, 6, new GoldToken());
        board.placeToken(4, 4, new PickaxeToken());
        board.placeToken(6, 1, new AnvilToken());
        board.placeToken(3, 3, new WaterToken());
        board.placeToken(7, 5, new WaterToken(20));
    }

    public void join(Player player) {
        if (player == null) {
            throw new NullPointerException("Gracz nie może być pusty");
        }
        this.player = player;
        
        player.vitals.setOnDeathHandler(() -> {
            System.out.println("To koniec: pełne odwodnienie. Gracz nie może się poruszać.");
        });

        try {
            Board.Coords pos = strategy.place(board);
            PlayerToken token = new PlayerToken(player, board, pos);
            token.initializeOnBoard();
            player.assignToken(token);
        } catch (IllegalStateException e) {
            System.out.println("Nie można dołączyć do gry: " + e.getMessage());
        }
    }

    public void start() {
        if (player == null || player.token() == null) {
            System.out.println("Żaden gracz nie dołączył do gry");
            return;
        }

        System.out.println("Gold Rush — gra turowa.");
        System.out.println("Komendy: up, down, left, right, none, quit");

        try (Scanner scanner = new Scanner(System.in)) {
            board.display();

            while (true) {
                System.out.println("Nawodnienie: " + player.vitals.hydration() + "%");
                System.out.println("Zebrane złoto: " + player.gold.amount());
                System.out.print("Ruch gracza: ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("quit")) break;

                PlayerToken.Move move = parseMove(input);
                if (move == null) {
                    System.out.println("Nieznana komenda.");
                    continue;
                }

                try {
                    player.token().move(move);
                    board.display();
                } catch (IllegalArgumentException e) {
                    System.out.println("Błąd: " + e.getMessage());
                } catch (IllegalStateException e) {
                    System.out.println("Błąd: " + e.getMessage());
                }
            }
        }

        System.out.println("Koniec gry. Zebrane złoto: " + player.gold.amount());
    }

    private PlayerToken.Move parseMove(String input) {
        return switch (input) {
            case "up" -> PlayerToken.Move.UP;
            case "down" -> PlayerToken.Move.DOWN;
            case "left" -> PlayerToken.Move.LEFT;
            case "right" -> PlayerToken.Move.RIGHT;
            case "none" -> PlayerToken.Move.NONE;
            default -> null;
        };
    }
}