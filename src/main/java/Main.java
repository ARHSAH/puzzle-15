import controller.Constants;
import controller.Variables;
import model.Location;
import model.PuzzleBoard;
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
        screenHeight += (widthTiles - 3 ) * 280;
        screenWidth += (heightTiles - 3) * 280;
        screenXLocation -= (widthTiles - 3) * 50;
        screenYLocation += (heightTiles - 3) * 50;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        panel.setSize(maxSize, maxSize);
        panel.setLocation(screenXLocation, screenYLocation);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
                        new Location(panel.getWidth() / widthTiles *
                                (i % widthTiles),
                                panel.getHeight() /
                                        heightTiles * (i / widthTiles))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg",
                        new Location(panel.getHeight() /
                                widthTiles * (i % widthTiles),
                                panel.getWidth() /
                                        heightTiles * (i / widthTiles))));
            }
        }
//        for (int i = 0; i < widthTiles; i++) {
//            for (int j = 0; j < heightTiles; j++) {
//                if (panel.missingPiece != (i + 1) * (j + 1) - 1) {
//                    puzzlePieces.add(new
//                            PuzzlePiece(
//                            PuzzleBoard.getINSTANCE().getPuzzleBoard()[i][j]
//                                    + 1 + ".png",
//                            new Location(panel.getWidth() / widthTiles *
//                                    i,
//                                    panel.getHeight() /
//                                            heightTiles * j)));
//                } else {;
//                    puzzlePieces.add(new PuzzlePiece("missing.jpg",
//                            new Location(panel.getWidth() / widthTiles *
//                                    i,
//                                    panel.getHeight() /
//                                            heightTiles * j)));
//                }
//            }
//        }
        panel.setPuzzlePieces(puzzlePieces);
        frame.addKeyListener(new MyKeyListener());
        frame.setVisible(true);
        while (true) {
            try {
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
