package view;

import model.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static controller.Variables.heightTiles;
import static controller.Variables.widthTiles;
import static controller.controller.gameFinished;

public class MyPanel extends JPanel {
    public static MyPanel panelInstance;
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    public int missingPiece = 0;
    public String gameState = "#";

    public MyPanel() {

    }

    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
            return panelInstance;
        }
        return panelInstance;
    }

    public void swapPieces(int i, int j) {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).img);
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).getPieceNumber());
        puzzlePieces.get(j).setImage(copy.img);
        puzzlePieces.get(j).setPieceNumber(copy.getPieceNumber());

        if (gameFinished()) {
            gameState = "finished";
        }
    }


    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            g.drawImage(piece.img, piece.getLocation().x, piece.getLocation().y,

                    (int) this.getSize().getWidth() / widthTiles, (int) this.getSize().getHeight() / heightTiles, null);
        }
    }
}