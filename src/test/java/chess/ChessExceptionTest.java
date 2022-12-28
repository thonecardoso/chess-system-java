package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChessExceptionTest {
    @Test
    void whenAnEmptySourceIsInformedThenAnExceptionShouldBeThrown() {

        //Arrange
        var board = new Board(8, 8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch)),
                new Pieces('h', 8, new Rook(board, Color.BLACK))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('a', 3);
        var t1 = new ChessPosition('a', 4);


        //Act-Assert
        Assertions.assertThrowsExactly(ChessException.class, () -> chessMatch.performChessMove(s1, t1), "There is no piece on source position.");
    }

    @Test
    void whenNotYourPieceIsInformedThenAnExceptionShouldBeThrown() {

        //Arrange
        var board = new Board(8, 8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch)),
                new Pieces('h', 8, new Rook(board, Color.BLACK))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('a', 7);
        var t1 = new ChessPosition('a', 5);


        //Act-Assert
        Assertions.assertThrowsExactly(ChessException.class, () -> chessMatch.performChessMove(s1, t1), "The chosen piece is not yours");
    }

    @Test
    void whenNotExistAMoveForSourcePositionThenAnExceptionShouldBeThrown() {

        //Arrange
        var board = new Board(8, 8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Queen(board, Color.BLACK)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK)),
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('e', 1);
        var t1 = new ChessPosition('d', 2);


        //Act-Assert
        Assertions.assertThrowsExactly(ChessException.class, () -> chessMatch.performChessMove(s1, t1), "There is no possible moves for the chosen piece.");
    }

    @Test
    void whenAnNotPossibleMoveToTargetPositionThenAnExceptionShouldBeThrown() {

        //Arrange
        var board = new Board(8, 8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Queen(board, Color.BLACK)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK)),
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('a', 2);
        var t1 = new ChessPosition('b', 3);


        //Act-Assert
        Assertions.assertThrowsExactly(ChessException.class, () -> chessMatch.performChessMove(s1, t1), "The chosen piece can't move to target position");
    }

    @Test
    void whenAMovePutYourOwnKingInCheckThenAnExceptionShouldBeThrown() {

        //Arrange
        var board = new Board(8, 8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Queen(board, Color.BLACK)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK)),
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var s1 = new ChessPosition('e', 1);
        var t1 = new ChessPosition('d', 2);


        //Act-Assert
        Assertions.assertThrowsExactly(ChessException.class, () -> chessMatch.performChessMove(s1, t1), "You can't put yourself in check");
    }
}
