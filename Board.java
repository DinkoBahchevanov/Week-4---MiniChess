package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private final String[][] printBoard;
    private final List<Piece> pieces;

    public Board() {
        this.printBoard = new String[6][6];
        this.pieces = new ArrayList<>();
    }

    private void init() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                printBoard[i][j] = "     ";
            }
        }

        printBoard[0][0] = " WDw ";
        printBoard[0][1] = " WD  ";
        printBoard[0][2] = " WQ  ";
        printBoard[0][3] = " WK  ";
        printBoard[0][4] = " WM  ";
        printBoard[0][5] = " WDw ";

        printBoard[5][0] = " BDw ";
        printBoard[5][1] = " BM  ";
        printBoard[5][2] = " BK  ";
        printBoard[5][3] = " BQ  ";
        printBoard[5][4] = " BD  ";
        printBoard[5][5] = " BDw ";

        pieces.add(new Dwarf(new Position(0, 0), "White", "Dwarf"));
        pieces.add(new Donkey(new Position(0, 1), "White", "Donkey"));
        pieces.add(new Queen(new Position(0, 2), "White", "Queen"));
        pieces.add(new King(new Position(0, 3), "White", "King"));
        pieces.add(new Molar(new Position(0, 4), "White", "Molar"));
        pieces.add(new Dwarf(new Position(0, 5), "White", "Dwarf"));

        pieces.add(new Dwarf(new Position(5, 0), "Black", "Dwarf"));
        pieces.add(new Molar(new Position(5, 1), "Black", "Molar"));
        pieces.add(new King(new Position(5, 2), "Black", "King"));
        pieces.add(new Queen(new Position(5, 3), "Black", "Queen"));
        pieces.add(new Donkey(new Position(5, 4), "Black", "Donkey"));
        pieces.add(new Dwarf(new Position(5, 5), "Black", "Dwarf"));
    }

    private void print() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print("+---+");
            }
            System.out.println();
            for (int j = 0; j < 6; j++) {
                System.out.print(printBoard[i][j]);
            }
            System.out.println();
        }
        for (int j = 0; j < 6; j++) {
            System.out.print("+---+");
        }
        System.out.println();
    }

    public void play(Scanner scanner) {
        init();
        boolean wKing = true;
        boolean bKing = true;

        String colorTurn = "White";
        int movesForWhiteDonkey = 1;
        int movesForBlackDonkey = 1;

        print();

        while (wKing && bKing) {
            System.out.println(colorTurn + " turn");
            String[] input = scanner.nextLine().split("\\s");

            int x1 = (int) (input[0].charAt(0)) - 65;
            int y1 = (int) (input[1].charAt(0)) - 65;
            int x2 = (int) (input[2].charAt(0)) - 65;
            int y2 = (int) (input[3].charAt(0)) - 65;

            if (x1 < 0 || x1 > 5 || y1 > 5 || y1 < 0 ||
                    x2 < 0 || x2 > 5 || y2 > 5 || y2 < 0) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            if (printBoard[x1][y1].charAt(1) == printBoard[x2][y2].charAt(0)) {
                System.out.println("Invalid move");
                continue;
            }

            if (printBoard[x1][y1].charAt(1) == printBoard[x2][y2].charAt(1)) {
                System.out.println("Invalid move");
                continue;
            }

            pieces.removeIf(Piece::getIsDead);

            for (Piece piece : pieces) {
                if (piece.getPosition().getX() == x1 && piece.getPosition().getY() == y1) {
                    if (!piece.getColor().equals(colorTurn)) {
                        System.out.println("It is " + colorTurn + " turn");
                        break;
                    }

                    if (piece.getName().equals("Donkey")) {
                        if (piece.getColor().equals("Black")) {
                            if (movesForBlackDonkey % 3 != 0) {
                                System.out.println(3 - movesForBlackDonkey % 3
                                        + " moves remain to be able to move the Donkey");
                                break;
                            }
                        } else {
                            if (movesForWhiteDonkey % 3 != 0) {
                                System.out.println(3 - movesForWhiteDonkey % 3
                                        + " moves remain to be able to move the Donkey");
                                break;
                            }
                        }
                        System.out.println("Otidi konq u rqkata");
                        System.out.println("Ku4eta go qli toq kon");
                    }

                    if (!printBoard[x2][y2].equals("     ")) {
                        printBoard[x2][y2] = "     ";
                        for (Piece piece1 : pieces) {
                            if (piece1.getPosition().getX() == x2 && piece1.getPosition().getY() == y2) {
                                piece1.setIsDead(true);
                                if (piece1.getName().equals("King")) {
                                    if (piece1.getColor().equals("White")) {
                                        wKing = false;
                                    } else bKing = false;
                                    break;
                                }
                            }
                        }
                    }

                    if (piece.move(x2, y2).equals("Successful move")) {
                        System.out.println(piece.getName() + " => " + input[2] + " " + input[3]);
                        printBoard[x1][y1] = "     ";
                        if (piece.getName().equals("Dwarf")) {
                            printBoard[x2][y2] =
                                    " " + piece.getColor().charAt(0) + piece.getName().charAt(0) + piece.getName().charAt(1) + " ";
                        } else {
                            printBoard[x2][y2] =
                                    " " + piece.getColor().charAt(0) + piece.getName().charAt(0) + "  ";
                        }
                        colorTurn = colorTurn.equals("White") ? "Black" : "White";

                        if (piece.getColor().equals("White")) {
                            movesForWhiteDonkey++;
                        } else {
                            movesForBlackDonkey++;
                        }
                        break;
                    } else {
                        System.out.println(piece.getName() + " cannot move to " + input[2] + " " + input[3]);
                    }
                }
            }
            print();
        }
        if (wKing) {
            System.out.println("White won!");
        } else System.out.println("Black won!");
    }
}