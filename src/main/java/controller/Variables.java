package controller;

import model.PuzzlePiece;

import java.io.File;
import java.util.ArrayList;

public class Variables {
    public static int screenWidth = 1500;
    public static int screenHeight = 1200;
    public static int screenXLocation = 400;
    public static int screenYLocation = 100;
    public static int widthTiles = 0;
    public static int heightTiles = 0;
    public static boolean mode = false;
    public static String gameState = "";
    public static ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    public static ArrayList<Integer> piecesRandomOrder = new ArrayList<>();
    public static ArrayList<String> imagesRandomOrder = new ArrayList<>();
    public static File file = new File("puzzle.json");
}