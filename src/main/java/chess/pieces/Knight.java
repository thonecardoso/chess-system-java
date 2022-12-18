package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

import java.util.HashSet;
import java.util.Set;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public Set<Position> possibleMoves() {
        var moves = new HashSet<Position>();

        var p = new Position(0, 0);

        p.setValues(position.getRow() + 2, position.getColumn() - 1);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() + 2, position.getColumn() + 1);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() - 2, position.getColumn() + 1);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() + 1, position.getColumn() - 2);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() + 1, position.getColumn() + 2);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        p.setValues(position.getRow() - 1, position.getColumn() + 2);
        if(isValidMove(p) || (board.positionExists(p) && isThereOpponentPiece(p))){
            moves.add(new Position(p.getRow(), p.getColumn()));
        }


        return moves;
    }

    @Override
    public String toString() {
        return "N";
    }
}
