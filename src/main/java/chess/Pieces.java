package chess;

public record Pieces(char column, int row, ChessPiece chessPiece){

    public Pieces {
    }


    public char column() {
        return column;
    }


    public int row() {
        return row;
    }


    public ChessPiece chessPiece() {
        return chessPiece;
    }
}
