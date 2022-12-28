package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;
import chess.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KingTest {
    @Test
    void whenPossibleMovesWasCallItShouldReturnsSevenMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('e',1));

        //Assert
        Assertions.assertEquals(7,moves.size());
    }

    @Test
    void whenPerformChessMoveWasCalledItShouldPerformedRookCastlingMove() {

        //Arrange
        var board = new Board(8,8);
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
        var s1 = new ChessPosition('e', 1);
        var t1 = new ChessPosition('g', 1);
        var s2 = new ChessPosition('e', 8);
        var t2 = new ChessPosition('g', 8);

        //Act
        chessMatch.performChessMove(s1,t1);
        chessMatch.performChessMove(s2,t2);

        var blackRook = board.piece(new Position(0,5));
        var whiteRook = board.piece(new Position(7,5));
        var blackKing = board.piece(new Position(0,6));
        var whiteKing = board.piece(new Position(7,6));

        //Assert
        Assertions.assertInstanceOf(Rook.class, blackRook);
        Assertions.assertInstanceOf(Rook.class, whiteRook);
        Assertions.assertInstanceOf(King.class, blackKing);
        Assertions.assertInstanceOf(King.class, whiteKing);
    }

    @Test
    void whenPerformChessMoveWasCalledItShouldPerformedQueenCastlingMove() {

        //Arrange
        var board = new Board(8,8);
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
        var s1 = new ChessPosition('e', 1);
        var t1 = new ChessPosition('c', 1);
        var s2 = new ChessPosition('e', 8);
        var t2 = new ChessPosition('c', 8);

        //Act
        chessMatch.performChessMove(s1,t1);
        chessMatch.performChessMove(s2,t2);

        var blackRook = board.piece(new Position(0,3));
        var whiteRook = board.piece(new Position(7,3));
        var blackKing = board.piece(new Position(0,2));
        var whiteKing = board.piece(new Position(7,2));

        //Assert
        Assertions.assertInstanceOf(Rook.class, blackRook);
        Assertions.assertInstanceOf(Rook.class, whiteRook);
        Assertions.assertInstanceOf(King.class, blackKing);
        Assertions.assertInstanceOf(King.class, whiteKing);
    }

    @Test
    void whenToStringIsCallItShouldReturnsKeyLetter() {

        //Arrange
        var board = new Board(8, 8);
        var p = new King(board, Color.WHITE, null);

        //Act-Assert
        Assertions.assertEquals("K", p.toString());
    }
}
