package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

import java.util.HashSet;
import java.util.Set;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public Set<Position> possibleMoves() {

        var moves = new HashSet<Position>();
        var p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (isValidMove(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
        }

        //below
        p.setValues(position.getRow() + 1, position.getColumn());
        while (isValidMove(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
        }

        //left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (isValidMove(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
        }

        //right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (isValidMove(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(new Position(p.getRow(),p.getColumn()));
        }

        return moves;
    }


}
