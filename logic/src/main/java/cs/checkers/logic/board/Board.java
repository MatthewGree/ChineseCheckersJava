package cs.checkers.logic.board;

import cs.checkers.logic.field.Field;
import cs.checkers.logic.field.PlainField;
import cs.checkers.logic.field.UnavailableField;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents board.
 */
public class Board {
    /**
     * Single instance of class object.
     */
    private static Board instance = new Board();

    /**
     * Array of objects of class {@link Field boardGame.Field} which belong to this board.
     */
    private Field[][] fields;

    /**
     * ArrayList of objects of class {@link Corner boardGame.Board.Corner}.
     */
    private ArrayList<Corner> corners = new ArrayList<>();

    /**
     * Private constructor.
     */
    private Board() {}

    /**
     * Method to get single instance of this class.
     * @return instance of boardGame.Board.boardGame.Board
     */
    public static Board getInstance() {
        return instance;
    }

    /**
     * Method which creates ArrayList of fields.
     * @param rows number of board's rows
     * @param cols number of board's columns
     */
    public void initializeFields(int rows, int cols) {
        fields = new Field[rows][cols];
        corners.clear();
    }

    public void setFieldType(int row, int col, String type) {
        if (type.equals("plain")) {
            fields[row][col] = new PlainField();
        } else if (type.equals("unavailable")) {
            fields[row][col] = new UnavailableField();
        }
        fields[row][col].setXY(row, col);
    }

    public void addCorner(Corner corner) {
        corners.add(corner);
    }

    public Field getField(int row, int col) {
        return fields[row][col];
    }

    public List<Corner> getCorners() {
        return corners;
    }
}
