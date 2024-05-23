package view;

import model.PuzzleBoard;

import javax.swing.*;
import java.util.Scanner;

import static controller.Controller.*;
import static controller.Variables.*;
import static controller.Variables.widthTiles;

public class TerminalView {
    private static TerminalView INSTANCE;

    public static TerminalView getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new TerminalView();
        }
        return INSTANCE;
    }

    private TerminalView() {

        boolean gameFinished = false;
        if (!mode && (widthTiles == 3 && heightTiles == 3 &&
                !solvable3(getMissingPiece(), piecesRandomOrder))
                || (widthTiles == 4 && heightTiles == 4 &&
                !solvable4(getMissingPiece(), piecesRandomOrder))) {
            System.out.println("this puzzle is not solvable, change your config and try again");
            System.exit(0);
            //randomImageOrder();
        }

        for (int i = 0; i < heightTiles; i++) {
            for (int j = 0; j < widthTiles ; j++) {
                if(PuzzleBoard.getINSTANCE().getPuzzleBoard()[i][j] ==
                        piecesRandomOrder.get(getMissingPiece())){
                    System.out.print("# ");
                    continue;
                }
                System.out.print(PuzzleBoard.getINSTANCE().getPuzzleBoard()[i][j]
                        + 1 + " ");
            }
            System.out.println();
        }
        if (gameFinished()) {
            System.out.println("Congrats, You solved puzzle successfully");
            System.exit(0);
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                Thread.sleep(1000 / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("direction :");
            String direction = sc.next();
            setBoard(direction);
            for (int i = 0; i < heightTiles; i++) {
                for (int j = 0; j < widthTiles ; j++) {
                    if(PuzzleBoard.getINSTANCE().getPuzzleBoard()[i][j] ==
                            piecesRandomOrder.get(getMissingPiece())){
                        System.out.print("# ");
                        continue;
                    }
                    System.out.print(PuzzleBoard.getINSTANCE().getPuzzleBoard()[i][j] + 1 +" ");
                }
                System.out.println();
            }

            if (gameFinished()) {
                gameState = "finished";
            }
            if (gameState.equals("finished")) {
                gameFinished = true;
            }
            if (gameFinished) {
                System.out.println("Congrats, You solved puzzle successfully");
                break;
            }

        }
    }
}
