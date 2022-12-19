package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends ChessPiece {

    private final ChessMatch chessMatch;
    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public Set<Position> possibleMoves() {
        var moves = new HashSet<Position>();

        var p = new Position(0, 0);

        if (getColor() == Color.WHITE) {

            //above1
            p.setValues(position.getRow() - 1, position.getColumn());
            if (isValidMove(p)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            //above2
            p.setValues(position.getRow() - 2, position.getColumn());
            var p2 = new Position(position.getRow() - 1, position.getColumn());
            if (isValidMove(p) && getMoveCount() == 0 && isValidMove(p2)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            //capture-left
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            //capture-right
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            var enPas = chessMatch.getEnPassantVulnerable();
            var p1 = new Position(position.getRow(), position.getColumn() + 1);
            if(getBoard().positionExists(p1) && isThereOpponentPiece(p1) && board.piece(p1).equals(enPas)){
                moves.add(new Position(p1.getRow() - 1, p1.getColumn()));

            }

            p1.setValues(position.getRow(), position.getColumn() - 1);
            if(getBoard().positionExists(p1) && isThereOpponentPiece(p1) && board.piece(p1).equals(enPas)){
                moves.add(new Position(p1.getRow() - 1, p1.getColumn()));
            }



        } else {
            //above1
            p.setValues(position.getRow() + 1, position.getColumn());
            if (isValidMove(p)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            //above2
            p.setValues(position.getRow() + 2, position.getColumn());
            var p2 = new Position(position.getRow() + 1, position.getColumn());
            if (isValidMove(p) && getMoveCount() == 0 && isValidMove(p2)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            //capture-left
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            //capture-right
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves.add(new Position(p.getRow(), p.getColumn()));
            }

            var enPas = chessMatch.getEnPassantVulnerable();
            var p1 = new Position(position.getRow(), position.getColumn() + 1);
            if(getBoard().positionExists(p1) && isThereOpponentPiece(p1) && board.piece(p1).equals(enPas)){
                moves.add(new Position(p1.getRow() + 1, p1.getColumn()));

            }

            p1.setValues(position.getRow(), position.getColumn() - 1);
            if(getBoard().positionExists(p1) && isThereOpponentPiece(p1) && board.piece(p1).equals(enPas)){
                moves.add(new Position(p1.getRow() + 1, p1.getColumn()));
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return "P";
    }
}
