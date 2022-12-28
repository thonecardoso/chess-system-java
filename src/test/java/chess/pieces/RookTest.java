package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;
import chess.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RookTest {
    @Test
    void whenPossibleMovesWasCallItShouldReturnsFourteenMoves() {

        //Arrange
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);
        var pieces = List.of(
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('e', 2, new King(board, Color.WHITE, chessMatch)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );
        chessMatch.setupPieces(pieces);
        var chessPositions = IntStream.rangeClosed(0, 6).boxed().map(x -> new Position(x, 0)).collect(Collectors.toSet());
        chessPositions.addAll(IntStream.rangeClosed(1,7).boxed().map(x -> new Position(7, x)).collect(Collectors.toSet()));

        //Act
        var moves = chessMatch.possibleMoves(new ChessPosition('a',1));

        //Assert
        Assertions.assertEquals(14,moves.size());
        Assertions.assertTrue(chessPositions.containsAll(moves));
    }

    @Test
    void whenToStringIsCallItShouldReturnsKeyLetter() {

        //Arrange
        var board = new Board(8, 8);
        var p = new Rook(board, Color.WHITE);

        //Act-Assert
        Assertions.assertEquals("R", p.toString());
    }
}
