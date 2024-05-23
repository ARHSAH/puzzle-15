package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import model.Location;
import model.PuzzleBoard;
import model.PuzzlePiece;
import view.MyPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static controller.Variables.*;

public class Controller {
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

    public static boolean solvable4(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;

        for (int i = 0; i < 16; i++) {
            for (int j = i + 1; j < 16; j++) {
                if (piecesOrder.get(i) != 0 && piecesOrder.get(j) != 0 && piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }
        int row = missingPiece % 4;
        if (row % 2 == 0) {
            return inversionCount % 2 != 0;
        } else {
            return inversionCount % 2 == 0;
        }
    }

    public static boolean gameFinished() {
        for (int i = 0; i < widthTiles * heightTiles; i++) {
            int pieceIdentifier = piecesRandomOrder.get(i);
            if (pieceIdentifier == widthTiles * heightTiles - 1) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

    public static void setImagesWithList(MyPanel panel) {
        for (int i = 0; i < widthTiles * heightTiles; i++) {
            if (getMissingPiece() != i) {
                puzzlePieces.add(new
                        PuzzlePiece(
                        imagesRandomOrder.get(i),
                        new Location(panel.getWidth() / widthTiles *
                                (i % widthTiles),
                                panel.getHeight() /
                                        heightTiles * (i / widthTiles))));
            } else {
                puzzlePieces.add(new PuzzlePiece(imagesRandomOrder.get(i),
                        new Location(panel.getHeight() /
                                widthTiles * (i % widthTiles),
                                panel.getWidth() /
                                        heightTiles * (i / widthTiles))));
            }
        }
    }

    public static void setImagesWithArray(MyPanel panel) {
        for (int i = 0; i < heightTiles; i++) {
            for (int j = 0; j < widthTiles; j++) {
                if (panel.missingPiece != (i + 1) * (j + 1) - 1) {
                    puzzlePieces.add(new
                            PuzzlePiece(
                            PuzzleBoard.getINSTANCE().
                                    getPuzzleBoard()[i][j]
                                    + 1 + ".png",
                            new Location(panel.getWidth() / widthTiles *
                                    j,
                                    panel.getHeight() /
                                            heightTiles * i)));
                } else {
                    ;
                    puzzlePieces.add(new PuzzlePiece("missing.jpg",
                            new Location(panel.getWidth() / widthTiles *
                                    j,
                                    panel.getHeight() /
                                            heightTiles * i)));
                }
            }
        }
    }

    public static void randomImageOrder() throws IOException {
        // if it was cli only collections.shuffle
        Collections.shuffle(piecesRandomOrder);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);
        ArrayNode initialOrder = (ArrayNode) rootNode.get("initial-ordering");
        ArrayNode images = (ArrayNode) rootNode.get("images");
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            initialOrder.set(i, piecesRandomOrder.get(i));
            if (piecesRandomOrder.get(i) == getMissingPiece()) {
                images.set(i, "missing.jpg");
            } else {
                images.set(i, (piecesRandomOrder.get(i) + 1) + ".png");
            }
        }
        objectMapper.writeValue(file, rootNode);
    }

    public static int getMissingPiece() {
        for (int i = 0; i < piecesRandomOrder.size(); i++)
            if (piecesRandomOrder.get(i) == widthTiles * heightTiles - 1)
                return i;
        return 0;
    }

    public static void swap(int i, int j) {
        int helper = piecesRandomOrder.get(i);
        piecesRandomOrder.set(i, piecesRandomOrder.get(j));
        piecesRandomOrder.set(j, helper);
    }

    public static void setBoard(String direction) {
        if (direction.equals("r")) {
            if (getMissingPiece() % widthTiles == widthTiles - 1) return;
            swap(getMissingPiece(), getMissingPiece() + 1);
        } else if (direction.equals("l")) {
            if (getMissingPiece() % widthTiles == 0) return;
            swap(getMissingPiece() - 1, getMissingPiece());
        } else if (direction.equals("u")) {
            if (getMissingPiece() <= widthTiles - 1) return;
            swap(getMissingPiece() - widthTiles, getMissingPiece());
        } else if (direction.equals("d")) {
            if (getMissingPiece() >= widthTiles * (heightTiles - 1)) return;
            swap(getMissingPiece() + widthTiles, getMissingPiece());
        }
        if(mode){
            if (direction.equals("ur")) {
                if ((getMissingPiece() % widthTiles == widthTiles - 1) ||
                        (getMissingPiece() <= widthTiles - 1)) return;
                swap(getMissingPiece(), getMissingPiece() - widthTiles + 1);
            } else if (direction.equals("ul")) {
                if (getMissingPiece() % widthTiles == 0 ||
                        getMissingPiece() <= widthTiles - 1 ) return;
                swap(getMissingPiece() - widthTiles - 1, getMissingPiece());
            } else if (direction.equals("dr")) {
                if (getMissingPiece() % widthTiles == widthTiles - 1 ||
                        getMissingPiece() >= widthTiles * (heightTiles - 1) ) return;
                swap(getMissingPiece() + widthTiles + 1, getMissingPiece());
            } else if (direction.equals("dl")) {
                if (getMissingPiece() % widthTiles == 0 ||
                        getMissingPiece() >= widthTiles * (heightTiles - 1) ) return;
                swap(getMissingPiece() + widthTiles - 1, getMissingPiece());
            }
        }
    }
}