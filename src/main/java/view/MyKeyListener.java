package view;

import view.MyPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static controller.Variables.heightTiles;
import static controller.Variables.widthTiles;

public class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int missingPieceIndex = MyPanel.getInstance().missingPiece;
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (missingPieceIndex % widthTiles == widthTiles - 1) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex,
                    missingPieceIndex + 1);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex + 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if (missingPieceIndex % widthTiles == 0) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex,
                    missingPieceIndex - 1);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex - 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            if (missingPieceIndex <= widthTiles - 1) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex,
                    missingPieceIndex - widthTiles);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex - widthTiles);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            if (missingPieceIndex >= widthTiles * (heightTiles - 1)) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex,
                    missingPieceIndex + widthTiles);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex + widthTiles);
        }

        if (MyPanel.getInstance().gameState.equals("finished")) {
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
