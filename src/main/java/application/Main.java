package application;

import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var sc = new Scanner(System.in);
        var chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source: ");
                var source = UI.readChessPosition(sc);

                System.out.println();
                System.out.print("Target: ");
                var target = UI.readChessPosition(sc);

                var capturedPiece = chessMatch.performChessMove(source, target);

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }


        }
    }
}