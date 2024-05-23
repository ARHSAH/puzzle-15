package model;

import static controller.Variables.*;

public class PuzzleBoard {
    private int[][] puzzleBoard;
    private static PuzzleBoard INSTANCE;
    public static PuzzleBoard getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new PuzzleBoard();
        }
        return INSTANCE;
    }

    public int[][] getPuzzleBoard() {
        puzzleBoard = new int[heightTiles][widthTiles];
        for (int i = 0; i < heightTiles; i++) {
            for (int j = 0; j < widthTiles; j++) {
                puzzleBoard[i][j] = piecesRandomOrder.get(i * widthTiles + j);
            }

        }
        return puzzleBoard;
    }
}
