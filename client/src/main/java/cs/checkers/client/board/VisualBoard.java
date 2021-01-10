package cs.checkers.client.board;


/**
 * VisualBoard -- code representation of a Chinese Checkers board, consists of multiples
   of {@link VisualField}, which determine the look of the board.
 */
public class VisualBoard implements AbstractVisualBoard {
  private VisualField[][] fields;

	@Override
	public void move(Integer fromX, Integer fromY, Integer toX, Integer toY) {
    try {
      if (fields[fromX][fromY].getChecker() != null) {
        fields[toX][toY].setChecker(fields[fromX][fromY].getChecker());
        fields[fromX][fromY].setChecker(null);
      }
    } catch (Exception e) {
    }
	}

	public VisualField[][] getFields() {
		return fields;
	}

	public void setFields(VisualField[][] fields) {
		this.fields = fields;
	}

}
