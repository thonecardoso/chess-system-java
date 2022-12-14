package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ChessMatch {

    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private final List<Piece> piecesOnTheBoard = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();

    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private boolean checkMate;

    public ChessMatch(Board board) {
        this.board = board;
        turn = 1;
        currentPlayer = Color.WHITE;
    }

    public int getTurn() {
        return turn;
    }

    public ChessPiece getPromoted() {
        return promoted;
    }

    public List<ChessPiece> getCapturedPieces() {
        return capturedPieces.stream().map(x -> (ChessPiece) x).toList();
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

    public void performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
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
        }

        check = testCheck(opponent(currentPlayer));
        checkMate = testCheckMate(opponent(currentPlayer));

        if(promoted != null) return;

        if (!isCheckMate())
            nextTurn();

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
        check = testCheck(opponent(currentPlayer));
        checkMate = testCheckMate(opponent(currentPlayer));

        if (!isCheckMate())
            nextTurn();

    }


    private void nextTurn() {
        if (isCheckMate())
            return;
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        var king = piecesOnTheBoard.stream().filter(n -> n instanceof King && ((King) n).getColor() == color).findFirst();
        return (ChessPiece) king.orElseThrow(() -> new IllegalStateException("There is no " + color + " king on the board"));
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
        if (!testCheck(color)) return false;

        var pieces = getPieces(color);

        for (var p : pieces) {
            var moves = p.possibleMoves();
            var source = ((ChessPiece) p).getChessPosition().toPosition();
            for (var target : moves) {
                var capturedPiece = makeMove(source, target);
                if (!testCheck(color)) {
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
            } else {
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
        return board.piece(target) instanceof King && ((target.getColumn() == source.getColumn() + 2) || (target.getColumn() == source.getColumn() - 2));
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

    public void setupPieces(List<Pieces> pieces) {
        pieces.forEach(piece -> board.placePiece(piece.chessPiece(), new ChessPosition(piece.column(), piece.row()).toPosition()));
        piecesOnTheBoard.addAll(pieces.stream().map(Pieces::chessPiece).toList());
    }

}
