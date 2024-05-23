package view;

import model.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static controller.Controller.*;
import static controller.Variables.*;

public class MyPanel extends JPanel {
    private static MyPanel panelInstance;
    public ArrayList<PuzzlePiece> puzzlePieces1 = new ArrayList<>();
    public int missingPiece = 0;
    JFrame frame;

    public MyPanel() {
        frame = new JFrame();
        screenHeight += (widthTiles - 3 ) * 280;
        screenWidth += (heightTiles - 3) * 280;
        screenXLocation -= (widthTiles - 3) * 50;
        screenYLocation += (heightTiles - 3) * 50;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        this.setSize(maxSize, maxSize);
        this.setLocation(screenXLocation, screenYLocation);
        frame.setSize(this.getSize());
        frame.setLocation(this.getLocation());
        frame.add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMissingPiece(getMissingPiece());
        setImagesWithList(this);
        this.setPuzzlePieces(puzzlePieces);
        frame.addKeyListener(new MyKeyListener());
        frame.setVisible(true);
    }

    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
        }
        return panelInstance;
    }

    public void swapPieces(int i, int j) {
        PuzzlePiece copy = this.puzzlePieces1.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).img);
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).getPieceNumber());
        puzzlePieces.get(j).setImage(copy.img);
        puzzlePieces.get(j).setPieceNumber(copy.getPieceNumber());
        int helper = piecesRandomOrder.get(i);
        piecesRandomOrder.set(i, piecesRandomOrder.get(j));
        piecesRandomOrder.set(j, helper);
        if (gameFinished()) {
            gameState = "finished";
        }
    }


    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces1 = puzzlePieces;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces1) {
            g.drawImage(piece.img, piece.getLocation().x, piece.getLocation().y,

                    (int) this.getSize().getWidth() / widthTiles, (int) this.getSize().getHeight() / heightTiles, null);
        }
    }
}