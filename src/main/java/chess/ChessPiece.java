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

    public int getMoveCount() {
        return moveCount;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        var piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }

    protected void increaseMoveCount() {
        this.moveCount++;
    }

    protected void decreaseMoveCount() {
        this.moveCount--;
    }
}
