package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public class ChessPiece extends Piece {

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

    private boolean isThereOpponentPiece(Position position){
        return false;
    }

    private void increaseMoveCount(){
        this.moveCount++;
    }

    private void decreaseMoveCount(){
        this.moveCount--;
    }
}
