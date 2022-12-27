package chess.pieces;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;
import chess.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PawnTest {
    @Test
    void whenPossibleMovesWasCallItShouldReturnsOneMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 5, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
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
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('c',5));

        //Assert
        Assertions.assertEquals(1,moves.size());
    }


    @Test
    void whenPossibleMovesWasCallItShouldReturnsTwoMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 5, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
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
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('a',2));

        //Assert
        Assertions.assertEquals(2,moves.size());
    }

    @Test
    void whenPossibleMovesWasCallItShouldReturnsPassantMove() {

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
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('c', 4, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK))
        );
        chessMatch.setupPieces(pieces);

        //Act
        var source = new ChessPosition('b', 2);
        var target = new ChessPosition('b', 4);
        chessMatch.performChessMove(source,target);
        var moves = chessMatch.possibleMoves(new ChessPosition('c',4));

        //Assert
        Assertions.assertEquals(2,moves.size());
    }



}
