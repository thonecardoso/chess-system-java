package application;

import chess.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] pieces) {
        for (var i = 0; i < pieces.length; i++) {
            System.out.print((pieces.length - i) + " ");
            for (var j = 0; j < pieces[0].length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.print("  ");
        for (var j = 0; j < pieces[0].length; j++) {
            System.out.printf("%c ", 'a' + j);
        }
    }

    private static void printPiece(ChessPiece piece){
        if(piece == null){
            System.out.print("- ");
            return;
        }

        System.out.print(piece + " ");
    }
}
