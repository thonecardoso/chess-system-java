package chess.pieces;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;
import chess.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KnightTest {
    @Test
    void whenPossibleMovesWasCallItShouldReturnsThreeMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('b',1));

        //Assert
        Assertions.assertEquals(3,moves.size());
    }

    @Test
    void whenPossibleMovesWasCallItShouldReturnsTwoMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('d', 2, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('b',1));

        //Assert
        Assertions.assertEquals(2,moves.size());
    }

    @Test
    void whenToStringIsCallItShouldReturnsKeyLetter() {

        //Arrange
        var board = new Board(8, 8);
        var p = new Knight(board, Color.WHITE);

        //Act-Assert
        Assertions.assertEquals("N", p.toString());
    }
}
