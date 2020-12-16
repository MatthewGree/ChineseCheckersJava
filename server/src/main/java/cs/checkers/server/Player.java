package cs.checkers.server;

import cs.checkers.gamelogic.board.Corner;
import cs.checkers.gamelogic.checker.Checker;
import cs.checkers.gamelogic.field.Field;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Checker> ownCheckers = new ArrayList<>();
    private Corner corner;

    public Player(Corner corner) {
        this.corner = corner;
        addCheckers();
    }

    private void addCheckers() {
        List<Field> fields = corner.getFields();
        for (Field field: fields) {
            if (field.isAvailable()) {
                ownCheckers.add(field.getChecker()); // not null, in the begging all checkers are in corner
            }
        }
    }

    public boolean checkIfFinished() {
        
    }
}
