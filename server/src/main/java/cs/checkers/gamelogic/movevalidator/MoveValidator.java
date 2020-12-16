package cs.checkers.gamelogic.movevalidator;

import cs.checkers.gamelogic.board.Board;
import cs.checkers.gamelogic.checker.Checker;
import cs.checkers.gamelogic.checker.ChineseBasicChecker;
import cs.checkers.gamelogic.field.Field;
import cs.checkers.gamelogic.field.UnavailableField;
import cs.checkers.gamelogic.move.Move;
import cs.checkers.gamelogic.move.MoveJump;
import cs.checkers.gamelogic.move.MoveOneSquare;
import cs.checkers.parser.CommandParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which validates moves of checkers.
 */
public class MoveValidator {
   private CommandParser parser = new CommandParser();
    /**
     * List of visited fields.
     */
    private List<Field> visited = new ArrayList<>();

    /**
     * Values to iterate through neighbours for x axis.
     */
    private final Integer[] valx = {-1, 0, 1, 1, 0, -1};

    /**
     * Values to iterate through neighbours for y axis.
     */
    private final Integer[] valy = {1, 2, 1, -1, -2, -1};

    /**
     * Method which validates move specified in given command.
     * @param x1 row of current field
     * @param y1 column of current field
     * @param x2 row of next field
     * @param y2 column of next field
     * @param board up-to-date board
     * @return true if move is valid, false otherwise
     */
    public boolean validateMove(int x1, int y1, int x2, int y2, Board board) { // command in format 'move x_1,y_1 x_2,y_2'
        visited.clear();

        Field currentField = board.getField(x1, y1);
        Field nextField = board.getField(x2, y2);
        Checker checker = currentField.getChecker();

        if (nextField.equals(currentField)) {
            return true;
        } else if (!nextField.isAvailable() || !(nextField.getChecker() == null)) {
            return false;
        } else {
            List<Move> moves = checker.getMoveSet().getMoves();
            for (Move move: moves) {
                if (move.getClass().equals(MoveOneSquare.class)) {
                    if(validateForMoveOneSquare(x1, y1, x2, y2))
                        return true;
                } else if (move.getClass().equals(MoveJump.class)) {
                     if (validateForMoveJump(x1, y1, x2, y2, x1, y1, board)) {
                         return true;
                     }
                }
            }
        }
        return false;
    }

    private String[] splitCommand(String command, String separator) {
        String[] result = command.split(separator);
        return result;
    }

    private boolean validateForMoveOneSquare(int x1, int y1, int x2, int y2) {
        if ((Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) || (Math.abs(y1 - y2) == 2 && x1 - x2 == 0)) {
            return true;
        }
        return false;
    }

    private boolean validateForMoveJump(int x1, int y1, int x2, int y2, int tempx, int tempy, Board board) {
        Field[] fieldsAround = new Field[6]; // it the beginning tempx and tempy equal to x1, y1
        for (int i = 0; i < 6; i++) {
            if (!(tempx + valx[i] == x1 && tempy + valy[i] == y1)) {
                fieldsAround[i] = board.getField(tempx + valx[i], tempy + valy[i]);
            } else {
                fieldsAround[i] = null;
            }
        }
        //System.out.println("ogolnie" + tempx + "'" + tempy);
        visited.add(board.getField(x1, y1));
        for (int i = 0; i < 6; i++) {
            if (fieldsAround[i] != null && fieldsAround[i].getX() == x2 && fieldsAround[i].getY() == y2) {
                return true;
            }
            if (fieldsAround[i] != null && fieldsAround[i].getChecker() != null && !isVisited(fieldsAround[i], visited)
                    && ifNeighboursAreEmpty(fieldsAround[i], board)) { // the closest neighbour has checker, can proceed
                visited.add(fieldsAround[i]);
               // System.out.println("w najblizszych");
                return validateForMoveJump(tempx, tempy, x2, y2, fieldsAround[i].getX(), fieldsAround[i].getY(), board);
            }

        }
        for (int i = 0; i < 6; i++) {
                int idx = checkNeighbourNeighbours(fieldsAround, board);
                if (idx != -1 && !isVisited(fieldsAround[idx], visited)) {
                   // System.out.println("w dalsych");
                    visited.add(fieldsAround[idx]);
                    return validateForMoveJump(tempx, tempy, x2, y2, fieldsAround[idx].getX(), fieldsAround[idx].getY(), board);
                }
        }
        return false;
    }

    private int checkNeighbourNeighbours(Field[] neighbours, Board board) {
        for (int i = 0; i < 6; i++) {
            if (neighbours[i] != null) {
                int row = neighbours[i].getX();
                int col = neighbours[i].getY();
                Field temp;
                // check if neighbours of this neighbour have checkers
                for (int j = 0; j < 6; j++) {
                    temp = board.getField(row + valx[j], col + valy[j]);
                    if (temp != null && temp.getChecker() != null && !isVisited(temp, visited)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private boolean ifNeighboursAreEmpty(Field field, Board board) {
        for (int i = 0; i < 6; i++) {
            // check if there exists neighbour without checkers
            Field temp;
            temp = board.getField(field.getX() + valx[i], field.getY() + valy[i]);
            if (temp != null && temp.getChecker() == null && !isVisited(temp, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVisited(Field field, List<Field> fields) {
        for (Field toCheck : fields) {
            if (toCheck.equals(field)) {
                return true;
            }
        }
        return false;
    }
}
