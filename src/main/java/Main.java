import controller.Constants;
import controller.Variables;
import model.Location;
import model.PuzzlePiece;
import view.MyKeyListener;
import view.MyPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static controller.Variables.*;
import static controller.controller.solvable3;

public class Main {
    public static void main(String[] args) throws IOException {
        Constants.getINSTANCE();


        JFrame frame = new JFrame();
        MyPanel panel = MyPanel.getInstance();
        int maxSize = Math.max(Variables.screenWidth, Variables.screenHeight) / 3;
        panel.setSize(maxSize, maxSize);
        panel.setLocation(screenXLocation, screenYLocation);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        ArrayList<Integer> piecesRandomOrder =
//                new ArrayList<Integer>(Arrays.asList(4, 1, 7, 2, 6, 0, 8, 3, 5));
        ArrayList<Integer> piecesRandomOrder =
                new ArrayList<Integer>(Arrays.asList(4, 1, 7, 2, 6, 0, 8, 3, 5, 9, 10, 11,
                        12, 13, 14 ,15));

        for (int i = 0; i < piecesRandomOrder.size(); i++)
            if (piecesRandomOrder.get(i) == widthTiles * heightTiles - 1)
                panel.setMissingPiece(i);

        boolean gameFinished = false;
        if (widthTiles == 3 && heightTiles == 3 &&
                !solvable3(panel.missingPiece, piecesRandomOrder)) {
            JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            gameFinished = true;
        }
        for (int i = 0; i < widthTiles * heightTiles; i++) {
            System.out.println(i + " " + piecesRandomOrder.get(i));
            if (panel.missingPiece != i) {
                puzzlePieces.add(new
                        PuzzlePiece(
                                piecesRandomOrder.get(i) + 1 + ".png",
                        new Location(panel.getHeight() / heightTiles * (i % heightTiles),
                                panel.getWidth() / widthTiles * (i / widthTiles))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg",
                        new Location(panel.getHeight() / heightTiles * (i % heightTiles),
                                panel.getWidth() / widthTiles * (i / widthTiles))));
            }
        }
        panel.setPuzzlePieces(puzzlePieces);
        frame.addKeyListener(new MyKeyListener());
        frame.setVisible(true);
        while (true) {
            try {
                // TODO: CHANGE THIS FOR IMPROVING YOUR FRAME RATE... (OPTIONAL)
                Thread.sleep(1000 / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            panel.repaint();
            frame.repaint();

            if (gameFinished) {
                break;
            }

            if (panel.gameState.equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                gameFinished = true;
            }
        }
    }


}
