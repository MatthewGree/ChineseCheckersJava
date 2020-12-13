package cs.checkers.logic.Board;

import cs.checkers.logic.Field.PlainField;

public class FourPlayerChineseCheckersBoardBuilder extends ChineseCheckersBoardBuilder {
    protected void addCorners() {
        Corner cornerPlayer1 = new Corner();
        Corner cornerPlayer3 = new Corner();

        for (int i = 4; i < 8; i++) {
            for (int j = 18; j < 25; j++) {
                if (board.getField(i, j) instanceof PlainField) { // plain field
                    cornerPlayer1.addField(board.getField(i, j));
                }
            }
        }

        for (int i = 9; i < 13; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.getField(i, j) instanceof PlainField) { // plain field
                    cornerPlayer3.addField(board.getField(i, j));
                }
            }
        }

        cornerPlayer1.setOppositeCorner(cornerPlayer3);
        cornerPlayer3.setOppositeCorner(cornerPlayer1);

        Corner cornerPlayer2 = new Corner();
        Corner cornerPlayer4 = new Corner();

        for (int i = 9; i < 13; i++) {
            for (int j = 18; j < 25; j++) {
                if (board.getField(i, j) instanceof PlainField) { // plain field
                    cornerPlayer2.addField(board.getField(i, j));
                }
            }
        }

        for (int i = 4; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.getField(i, j) instanceof PlainField) { // plain field
                    cornerPlayer4.addField(board.getField(i, j));
                }
            }
        }
        cornerPlayer2.setOppositeCorner(cornerPlayer4);
        cornerPlayer4.setOppositeCorner(cornerPlayer2);

        board.addCorner(cornerPlayer1);
        board.addCorner(cornerPlayer2);
        board.addCorner(cornerPlayer3);
        board.addCorner(cornerPlayer4);
    }
}
