package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChessMatch {

    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private final List<Piece> piecesOnTheBoard = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();


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

    public ChessPiece getPromoted() {
        return promoted;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public boolean isCheckMate() {
        return checkMate;
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

        promoted = null;
        if (isPromoting(source, target)) {
            promoted = (ChessPiece) board.piece(target);
            replacePromotedPiece("Q");
        }

        check = testCheck(opponent(currentPlayer));


        checkMate = testCheckMate(opponent(currentPlayer));


        if (!checkMate) nextTurn();

        return (ChessPiece) capturedPiece;
    }

    public void replacePromotedPiece(String type) {
        ChessPiece p = null;
        switch (type) {
            case "Q" -> p = new Queen(board, promoted.getColor());
            case "N" -> p = new Knight(board, promoted.getColor());
            case "R" -> p = new Rook(board, promoted.getColor());
            case "B" -> p = new Bishop(board, promoted.getColor());
        }
        for (int i = 0; i < promoted.getMoveCount(); i++) p.increaseMoveCount();
        var target = promoted.getChessPosition().toPosition();

        piecesOnTheBoard.remove(board.removePiece(target));

        board.placePiece(p, target);
        piecesOnTheBoard.add(p);

        promoted = p;

    }


    private void nextTurn() {
        if(isCheckMate())
            return;
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
            var pMoves = p.possibleMoves();
            if (pMoves.contains(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!isCheck()) return false;

        var pieces = getPieces(color);

        for (var p : pieces) {
            var moves = p.possibleMoves();
            var source = ((ChessPiece) p).getChessPosition().toPosition();
            for (var target : moves) {
                var capturedPiece = makeMove(source, target);
                if (testCheck(color)) {
                    undoMove(source, target, capturedPiece);
                    return false;
                }
                undoMove(source, target, capturedPiece);
            }
        }

        return true;
    }

    private List<Piece> getPieces(Color color) {
        return piecesOnTheBoard.stream().filter(n -> ((ChessPiece) n).getColor() == color).toList();
    }

    private Piece makeMove(Position source, Position target) {
        boolean enPassant = isEnPassant(source, target);
        var piece = (ChessPiece) board.removePiece(source);
        piece.increaseMoveCount();
        var capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);

        if (isCastling(source, target)) {
            if (target.getColumn() == source.getColumn() + 2) {
                var rook = (ChessPiece) board.removePiece(new Position(source.getRow(), source.getColumn() + 3));
                rook.increaseMoveCount();
                board.placePiece(rook, new Position(source.getRow(), source.getColumn() + 1));
            } else {
                var rook = (ChessPiece) board.removePiece(new Position(source.getRow(), source.getColumn() - 4));
                rook.increaseMoveCount();
                board.placePiece(rook, new Position(source.getRow(), source.getColumn() - 1));
            }

        }

        if (enPassant) {
            capturedPiece = board.removePiece(enPassantVulnerable.getChessPosition().toPosition());
        }

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        checkVulnerableToEnPassant(source, target);

        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        var piece = (ChessPiece) board.removePiece(target);
        piece.decreaseMoveCount();
        board.placePiece(piece, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }

        if (isCastling(source, target)) {
            if (target.getColumn() == source.getColumn() + 2) {
                var rook = (ChessPiece) board.removePiece(new Position(source.getRow(), source.getColumn() + 1));
                rook.decreaseMoveCount();
                board.placePiece(rook, new Position(source.getRow(), source.getColumn() + 3));
            }else {
                var rook = (ChessPiece) board.removePiece(new Position(source.getRow(), source.getColumn() - 1));
                rook.decreaseMoveCount();
                board.placePiece(rook, new Position(source.getRow(), source.getColumn() - 4));
            }
        }

        if (enPassantVulnerable != null && capturedPiece == enPassantVulnerable) {
            if (enPassantVulnerable.getColor() == Color.WHITE) {
                target.setRow(target.getRow() - 1);
                board.placePiece(enPassantVulnerable, target);
            } else {
                target.setRow(target.getRow() + 1);
                board.placePiece(enPassantVulnerable, target);
            }
        }
    }

    private boolean isCastling(Position source, Position target) {
        return board.piece(source) instanceof King && ((target.getColumn() == source.getColumn() + 2) || (target.getColumn() == source.getColumn() - 2));
    }

    private boolean isEnPassant(Position source, Position target) {
        return board.piece(source) instanceof Pawn &&
                board.piece(source.getRow(), target.getColumn()) instanceof Pawn &&
                !board.thereIsAPiece(target) &&
                source.getColumn() != target.getColumn();
    }

    private boolean isPromoting(Position source, Position target) {
        var piece = (ChessPiece) board.piece(target);
        return piece instanceof Pawn
                &&
                (
                        (piece.getColor() == Color.WHITE && target.getRow() == 0)
                                ||
                        (piece.getColor() == Color.BLACK && target.getRow() == 7)
                );
    }

    private void checkVulnerableToEnPassant(Position source, Position target) {
        var pawn = (ChessPiece) board.piece(target);
        if (pawn instanceof Pawn) {

            var diff = Math.abs(target.getRow() - source.getRow());
            var p1 = new Position(target.getRow(), target.getColumn() + 1);
            var p2 = new Position(target.getRow(), target.getColumn() - 1);

            if (diff == 2) {
                if (board.positionExists(p1) && board.piece(p1) != null) {
                    if ((board.positionExists(p1) && ((ChessPiece) board.piece(p1)).getColor() != pawn.getColor())) {
                        enPassantVulnerable = pawn;
                    }
                }
                if (board.positionExists(p2) && board.piece(p2) != null) {
                    if ((board.positionExists(p2) && ((ChessPiece) board.piece(p2)).getColor() != pawn.getColor())) {
                        enPassantVulnerable = pawn;
                    }
                }
            }
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

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));


        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        //placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        //placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        //placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        //placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        //placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
    }
}
