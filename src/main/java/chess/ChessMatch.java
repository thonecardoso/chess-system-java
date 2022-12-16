package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChessMatch {

    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();


    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch() {
        this.board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public boolean isCheckMate() {
        return !checkMate;
    }

    public boolean isCheck() {
        return check;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        var mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (var i = 0; i < board.getRows(); i++) {
            for (var j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public Set<Position> possibleMoves(ChessPosition sourcePosition) {
        var position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        var source = sourcePosition.toPosition();
        var target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        var capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = testCheck(opponent(currentPlayer));
        checkMate = testCheckMate(opponent(currentPlayer));

        if(!checkMate)
            nextTurn();

        return (ChessPiece) capturedPiece;
    }

    private void nextTurn() {
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        var king = piecesOnTheBoard.stream().filter(n -> n instanceof King && ((King) n).getColor() == color).findFirst();
        return (ChessPiece) king.orElseThrow(() -> new IllegalStateException("There is no " + color + "king on the board"));
    }

    private boolean testCheck(Color color) {
        var kingPosition = king(color).getChessPosition().toPosition();
        var opponentPieces = getPieces(opponent(color));
        for (var p : opponentPieces) {
            var kingMoves = p.possibleMoves();
            if (kingMoves.contains(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) return false;

        var pieces = getPieces(color);

        for(var p : pieces){
            var moves = p.possibleMoves();
            for(var move : moves){
                if(testIsPosibleToAvoidCheck(((ChessPiece)p).getChessPosition().toPosition(), move))
                    return false;
            }
        }

        return true;
    }

    private List<Piece> getPieces(Color color) {
        return piecesOnTheBoard.stream().filter(n -> ((ChessPiece) n).getColor() == color).toList();
    }

    private boolean testIsPosibleToAvoidCheck(Position source, Position target) {
        var piece = board.removePiece(source);
        var capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        if(isCheck()){
            undoMove(source,target,capturedPiece);
            return false;
        }
        undoMove(source,target,capturedPiece);
        return true;
    }

    private Piece makeMove(Position source, Position target) {
        var piece = (ChessPiece) board.removePiece(source);
        piece.increaseMoveCount();
        var capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        var piece = (ChessPiece)board.removePiece(target);
        piece.decreaseMoveCount();
        board.placePiece(piece, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position.");
        }
        if (((ChessPiece) board.piece(position)).getColor() != currentPlayer) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece.");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    public void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
    }
}
