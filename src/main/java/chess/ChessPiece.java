package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

//    public ChessPosition getChessPosition(){
//
//    }

    protected boolean isThereOpponentPiece(Position position) {
        var piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }

    private void increaseMoveCount() {
        this.moveCount++;
    }

    private void decreaseMoveCount() {
        this.moveCount--;
    }
}
