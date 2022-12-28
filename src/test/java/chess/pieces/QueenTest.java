package chess.pieces;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;
import chess.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QueenTest {
    @Test
    void whenPossibleMovesWasCallItShouldReturnsSevenMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('d',1));

        //Assert
        Assertions.assertEquals(14,moves.size());
    }

    @Test
    void whenPossibleMovesWasCallItShouldReturnsThirteenMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('d', 7, new Queen(board, Color.BLACK)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('d',1));

        //Assert
        Assertions.assertEquals(13,moves.size());
    }

    @Test
    void whenPossibleMovesWasCallItShouldReturnsTwelveMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('d', 7, new Queen(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('d',1));

        //Assert
        Assertions.assertEquals(12,moves.size());
    }

    @Test
    void whenToStringIsCallItShouldReturnsKeyLetter() {

        //Arrange
        var board = new Board(8, 8);
        var p = new Queen(board, Color.WHITE);

        //Act-Assert
        Assertions.assertEquals("Q", p.toString());
    }

}
