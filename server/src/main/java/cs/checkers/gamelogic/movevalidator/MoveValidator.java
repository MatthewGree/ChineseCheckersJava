package cs.checkers.gamelogic.movevalidator;

import cs.checkers.common.CommandParser;
import cs.checkers.gamelogic.board.Board;
import cs.checkers.gamelogic.checker.Checker;
import cs.checkers.gamelogic.checker.ChineseBasicChecker;
import cs.checkers.gamelogic.field.Field;
import cs.checkers.gamelogic.field.UnavailableField;
import cs.checkers.gamelogic.move.Move;
import cs.checkers.gamelogic.move.MoveJump;
import cs.checkers.gamelogic.move.MoveOneSquare;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which validates moves of checkers.
 */
public class MoveValidator {
  /**
   * List of visited fields.
   */
  private List<Field> visited = new ArrayList<>();

  /**
   * Values to iterate through neighbours for x axis.
   */
  private final Integer[] valx = { -1, 0, 1, 1, 0, -1 };

  /**
   * Values to iterate through neighbours for y axis.
   */
  private final Integer[] valy = { 1, 2, 1, -1, -2, -1 };

  /**
   * Method which validates move specified in given command.
   * 
   * @param x1    row of current field
   * @param y1    column of current field
   * @param x2    row of next field
   * @param y2    column of next field
   * @param board up-to-date board
   * @return true if move is valid, false otherwise
   */
  public boolean validateMove(int x1, int y1, int x2, int y2, Board board) { // command in format 'move x_1,y_1 x_2,y_2'
    Field currentField = board.getField(x1, y1);
    Field nextField = board.getField(x2, y2);
    Checker checker = currentField.getChecker();

    if (nextField.equals(currentField)) {
      return true;
    } else if (!nextField.isAvailable() || !(nextField.getChecker() == null)) {
      return false;
    } else {
      List<Move> moves = checker.getMoveSet().getMoves();
      for (Move move : moves) {
        if (move.validateMove(x1, y1, x2, y2, board)) {
          return true;
        }
      }
    }
    return false;
  }
}
