package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        var mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        var p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        

        //below
        p.setValues(position.getRow() + 1, position.getColumn());
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //above-right
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //above-left
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below-right
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below-left
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (isValidMove(p) || (getBoard().positionExists(p) && isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
