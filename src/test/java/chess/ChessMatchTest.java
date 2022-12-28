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
    void whenTestCheckIsCallItShouldReturnTrue() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('d', 2, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),

                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('d', 2);
        var t1 = new ChessPosition('e', 2);

        //Act
        chessMatch.performChessMove(s1,t1);

        // Assert
        Assertions.assertTrue(chessMatch::isCheck);
    }

    @Test
    void whenTestCheckIsCallItShouldReturnFalse() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('d', 2, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),

                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('d', 2);
        var t1 = new ChessPosition('f', 2);

        //Act
        chessMatch.performChessMove(s1,t1);

        // Assert
        Assertions.assertFalse(chessMatch::isCheck);
    }

    @Test
    void whenTestCheckMateIsCallItShouldReturnFalse() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('d', 2, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),

                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('d', 2);
        var t1 = new ChessPosition('e', 2);

        //Act
        chessMatch.performChessMove(s1,t1);

        // Assert
        Assertions.assertFalse(chessMatch::isCheckMate);
    }

    @Test
    void whenTestCheckMateIsCallItShouldReturnTrue() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('h', 7, new Rook(board, Color.WHITE)),
                new Pieces('g', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),

                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('g', 1);
        var t1 = new ChessPosition('g', 8);

        //Act
        chessMatch.performChessMove(s1,t1);

        // Assert
        Assertions.assertTrue(chessMatch::isCheckMate);
    }

    @Test
    void whenTestCheckMateAfterPromotingAPawnIsCallItShouldReturnTrue() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('h', 7, new Rook(board, Color.WHITE)),
                new Pieces('a', 7, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),

                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('a', 7);
        var t1 = new ChessPosition('a', 8);


        //Act
        chessMatch.performChessMove(s1,t1);
        chessMatch.replacePromotedPiece("Q");

        // Assert
        Assertions.assertTrue(chessMatch::isCheckMate);
    }

    @Test
    void whenTestCheckMateAfterPromotingAPawnIsCallItShouldReturnFalse() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('h', 7, new Rook(board, Color.WHITE)),
                new Pieces('a', 7, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),

                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('a', 7);
        var t1 = new ChessPosition('a', 8);


        //Act
        chessMatch.performChessMove(s1,t1);
        chessMatch.replacePromotedPiece("N");

        // Assert
        Assertions.assertFalse(chessMatch::isCheckMate);
    }
}
