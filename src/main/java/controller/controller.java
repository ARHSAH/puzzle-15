package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Location;
import model.PuzzleBoard;
import model.PuzzlePiece;
import view.MyPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import static controller.Variables.*;

public class controller {
    public static boolean solvable3(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;
        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }
    public static boolean gameFinished() {
        for (int i = 0; i < widthTiles * heightTiles; i++) {
            int pieceIdentifier = puzzlePieces.get(i).getPieceNumber();
            System.out.println(puzzlePieces.get(i).getPieceNumber());
            if (pieceIdentifier == widthTiles * heightTiles - 1) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }
    public static void setImagesWithList(MyPanel panel){
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
    }

    public static void setImagesWithArray(MyPanel panel){
        for (int i = 0; i < widthTiles; i++) {
            for (int j = 0; j < heightTiles; j++) {
                if (panel.missingPiece != (i + 1) * (j + 1) - 1) {
                    puzzlePieces.add(new
                            PuzzlePiece(
                            PuzzleBoard.getINSTANCE().getPuzzleBoard()[i][j]
                                    + 1 + ".png",
                            new Location(panel.getWidth() / widthTiles *
                                    i,
                                    panel.getHeight() /
                                            heightTiles * j)));
                } else {;
                    puzzlePieces.add(new PuzzlePiece("missing.jpg",
                            new Location(panel.getWidth() / widthTiles *
                                    i,
                                    panel.getHeight() /
                                            heightTiles * j)));
                }
            }
        }
    }
}
