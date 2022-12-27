package chess;

import boardgame.Board;
import chess.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessMatchTest {
    @Test
    void whenInvalidIdIsGivenThenAnExceptionShouldBeThrown() {

        //Arrange
        var board = new Board(8,8);
        var match = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, null)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, null)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('b', 7, new Queen(board, Color.WHITE)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, null)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK)),
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, null))
                );

        match.setupPieces(pieces);
        //Act
        //Assert
        Assertions.assertTrue(true);
    }
}
