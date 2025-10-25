import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.io.token.GoldToken;

class PlacementTest {
    edu.io.Board board;
    
    @BeforeEach
    void setUp() {
        board = new edu.io.Board();
    }
    
    @Test
    void can_set_placement_strategy() {
        edu.io.PlacementStrategy strategy = new edu.io.RandomPlacement();
        board.setPlacementStrategy(strategy);
        
        edu.io.Board.Coords coords = board.getAvailableSquare();
        Assertions.assertTrue(board.isValidPos(coords.col(), coords.row()));
    }
    
    @Test
    void simple_strategy_returns_first_empty() {
        edu.io.Board.Coords coords = board.getAvailableSquare();
        Assertions.assertEquals(0, coords.col());
        Assertions.assertEquals(0, coords.row());
    }
    
    @Test
    void random_strategy_returns_valid_empty() {
        board.setPlacementStrategy(new edu.io.RandomPlacement());
        edu.io.Board.Coords coords = board.getAvailableSquare();
        
        Assertions.assertTrue(board.isValidPos(coords.col(), coords.row()));
        Assertions.assertTrue(board.peekToken(coords.col(), coords.row()) instanceof edu.io.token.EmptyToken);
    }
    
    @Test
    void throws_when_board_full_simple() {
        fillBoard();
        Assertions.assertThrows(IllegalStateException.class, () -> board.getAvailableSquare());
    }
    
    @Test
    void throws_when_board_full_random() {
        board.setPlacementStrategy(new edu.io.RandomPlacement());
        fillBoard();
        Assertions.assertThrows(IllegalStateException.class, () -> board.getAvailableSquare());
    }
    
    private void fillBoard() {
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                board.placeToken(col, row, new GoldToken());
            }
        }
    }
}