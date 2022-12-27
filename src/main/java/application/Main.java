package application;

import boardgame.Board;
import chess.ChessException;
import chess.ChessMatch;
import chess.Color;
import chess.Pieces;
import chess.pieces.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var sc = new Scanner(System.in);
        var board = new Board(8,8);
        var chessMatch = new ChessMatch(board);

        var pieces = List.of(
                new Pieces('a', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('b', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('c', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('d', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('e', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('f', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('g', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('h', 2, new Pawn(board, Color.WHITE, chessMatch)),
                new Pieces('a', 1, new Rook(board, Color.WHITE)),
                new Pieces('h', 1, new Rook(board, Color.WHITE)),
                new Pieces('b', 1, new Knight(board, Color.WHITE)),
                new Pieces('g', 1, new Knight(board, Color.WHITE)),
                new Pieces('c', 1, new Bishop(board, Color.WHITE)),
                new Pieces('f', 1, new Bishop(board, Color.WHITE)),
                new Pieces('d', 1, new Queen(board, Color.WHITE)),
                new Pieces('e', 1, new King(board, Color.WHITE, chessMatch)),


                new Pieces('a', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('b', 7, new Queen(board, Color.BLACK)),
                new Pieces('c', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('d', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('e', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('f', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('g', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('h', 7, new Pawn(board, Color.BLACK, chessMatch)),
                new Pieces('a', 8, new Rook(board, Color.BLACK)),
                new Pieces('h', 8, new Rook(board, Color.BLACK)),
                new Pieces('b', 8, new Knight(board, Color.BLACK)),
                new Pieces('g', 8, new Knight(board, Color.BLACK)),
                new Pieces('c', 8, new Bishop(board, Color.BLACK)),
                new Pieces('f', 8, new Bishop(board, Color.BLACK)),
                new Pieces('d', 8, new Queen(board, Color.BLACK)),
                new Pieces('e', 8, new King(board, Color.BLACK, chessMatch))
        );

        chessMatch.setupPieces(pieces);


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
                    while (!Arrays.asList(new String[]{"Q", "N", "R", "B"}).contains(finalType)) {
                        System.out.print("Enter piece for promotion (B/N/R/Q): ");
                        type = sc.nextLine();
                        finalType = type;
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