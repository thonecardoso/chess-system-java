package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        var mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        var p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (isValidMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValues(position.getRow() + 1, position.getColumn());
        while (isValidMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (isValidMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (isValidMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;
    }

    @Override
    public List<String> possibleMovesStr() {

        var moves = new ArrayList<String>();
        var p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (isValidMove(p)) {
            moves.add(""+p.getRow()+p.getColumn());
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(""+p.getRow()+p.getColumn());
        }

        //below
        p.setValues(position.getRow() + 1, position.getColumn());
        while (isValidMove(p)) {
            moves.add(""+p.getRow()+p.getColumn());
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(""+p.getRow()+p.getColumn());
        }

        //left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (isValidMove(p)) {
            moves.add(""+p.getRow()+p.getColumn());
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(""+p.getRow()+p.getColumn());
        }

        //right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (isValidMove(p)) {
            moves.add(""+p.getRow()+p.getColumn());
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            moves.add(""+p.getRow()+p.getColumn());
        }


        return moves;
    }


}
