package application;

import chess.ChessException;
import chess.ChessMatch;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var sc = new Scanner(System.in);
        var chessMatch = new ChessMatch();


        while (!chessMatch.isCheckMate()) {
            if(chessMatch.isCheck()){
                if(chessMatch.isCheckMate()){
                    UI.printWinner(chessMatch);
                    break;
                }
            }
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch);
                System.out.println();
                System.out.print("Source: ");
                var source = UI.readChessPosition(sc);

                var possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                var target = UI.readChessPosition(sc);

                chessMatch.performChessMove(source, target);

                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    var type = sc.nextLine();
                    String finalType = type;
                    while (Arrays.stream(new String[]{"Q", "N", "R", "B"}).filter(t -> t.equals(finalType)).findFirst().isEmpty()) {
                        type = sc.nextLine();
                    }
                    chessMatch.replacePromotedPiece(type);
                }

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

    }
}