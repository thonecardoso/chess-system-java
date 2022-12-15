package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var sc = new Scanner(System.in);
        var chessMatch = new ChessMatch();
        var captured = new ArrayList<ChessPiece>();

        while (!chessMatch.isCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                var source = UI.readChessPosition(sc);

                var possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                var target = UI.readChessPosition(sc);

                var capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null) captured.add(capturedPiece);

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }


        }

        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}