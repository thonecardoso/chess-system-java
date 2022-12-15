package chess;

import boardgame.Position;

public record ChessPosition(char column, int row) {
    public ChessPosition {
        if (column > 'h' || column < 'a' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
        }
    }

    protected Position toPosition() {
        return new Position(8-row, column - 'a' );
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char) (position.getColumn() + 'a'),8 - position.getRow());
    }

    @Override
    public String toString() {
        return String.format("%c%d",column,row);
    }
}
