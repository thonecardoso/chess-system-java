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
}
