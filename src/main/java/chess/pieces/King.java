package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

import java.util.HashSet;
import java.util.Set;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public Set<Position> possibleMoves() {
        var moves = new HashSet<Position>();

        var p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }


        //below
        p.setValues(position.getRow() + 1, position.getColumn());
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        //right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        //left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        //above-right
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        //above-left
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        //below-right
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        //below-left
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            moves.add(new Position(p.getRow(), p.getColumn()));
        }

        // #special move castling
        if (getMoveCount() == 0 && !chessMatch.isCheck()) {
            var positionRook = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(positionRook)) {
                var p1 = new Position(position.getRow(), position.getColumn() + 1);
                var p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (isValidMove(p1) && isValidMove(p2)) {
                    moves.add(new Position(position.getRow(), position.getColumn() + 2));
                }
            }

            positionRook.setValues(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(positionRook)) {
                var p1 = new Position(position.getRow(), position.getColumn() - 1);
                var p2 = new Position(position.getRow(), position.getColumn() - 2);
                var p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (isValidMove(p1) && isValidMove(p2) && isValidMove(p3)) {
                    moves.add(new Position(position.getRow(), position.getColumn() - 2));
                }
            }
        }

        return moves;
    }

    private boolean testRookCastling(Position position) {
        var p = (ChessPiece) getBoard().piece(position);
        return p instanceof Rook && p.getMoveCount() == 0 && p.getColor() == getColor();

    }
}
