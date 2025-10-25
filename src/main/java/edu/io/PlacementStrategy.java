package edu.io;

public interface PlacementStrategy {
    Board.Coords findEmptySpot(Board board);
}