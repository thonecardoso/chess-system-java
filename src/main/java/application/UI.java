package application;

import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.*;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            var s = sc.nextLine();
            var column = s.charAt(0);
            var row = s.charAt(1) - '0';
            return new ChessPosition(column, row);
        } catch (Exception e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }

    }

    public static void printMatch(ChessMatch chessMatch) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(chessMatch.getCapturedPieces());
        System.out.println();
        System.out.println("Turn : " + chessMatch.getTurn());
        if (!chessMatch.isCheckMate()) {
            System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
            if (chessMatch.isCheck()) {
                System.out.println("CHECK!");
            }
        } else {
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }

    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (var i = 0; i < pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for (var j = 0; j < pieces[0].length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.print("  ");
        for (var j = 0; j < pieces[0].length; j++) {
            System.out.printf("%c ", 'a' + j);
        }
        System.out.println();
    }

    public static void printBoard(ChessPiece[][] pieces, Set<Position> possibleMoves) {
        for (var i = 0; i < pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for (var j = 0; j < pieces[0].length; j++) {
                printPiece(pieces[i][j], possibleMoves.contains(new Position(i, j)));
            }
            System.out.println();
        }
        System.out.print("  ");
        for (var j = 0; j < pieces[0].length; j++) {
            System.out.printf("%c ", 'a' + j);
        }
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        var white = captured.stream().filter(n -> n.getColor() == Color.WHITE).toList();
        var black = captured.stream().filter(n -> n.getColor() == Color.BLACK).toList();
        System.out.println("Captured pieces:");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);

    }

    public static boolean printWinner(ChessMatch chessMatch) {
        if (chessMatch.isCheckMate()) {
            var winner = chessMatch.getCurrentPlayer() == Color.WHITE ? Color.BLACK : Color.WHITE;
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
            return true;
        }
        return false;
    }
}
