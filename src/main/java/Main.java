import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import controller.Constants;
import model.PuzzleBoard;
import view.MyPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;

import static controller.Controller.*;
import static controller.Variables.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Constants.getINSTANCE();
        MyPanel panel = MyPanel.getInstance();
        JFrame frame = MyPanel.getInstance().getFrame();
        boolean gameFinished = false;
        if (!mode && (widthTiles == 3 && heightTiles == 3 &&
                !solvable3(panel.missingPiece, piecesRandomOrder))
        || (widthTiles == 4 && heightTiles == 4 &&
                !solvable4(panel.missingPiece, piecesRandomOrder))) {
            JOptionPane.showMessageDialog(frame, "this puzzle is " +
                    "not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            gameFinished = true;
            //randomImageOrder();
        }

        while (true) {
            try {
                Thread.sleep(1000 / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameFinished()) {
                gameState = "finished";
            }
            panel.repaint();
            frame.repaint();

            if (gameFinished) {
                break;
            }

            if (gameState.equals("finished")) {
                //randomImageOrder();
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                gameFinished = true;
            }
        }
    }


}
