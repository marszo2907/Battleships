package battleships;

import java.util.InputMismatchException;

public class Coordinate {
    public static int calculateLength(Coordinate coordinate1,
                                      Coordinate coordinate2) {
        if (coordinate1.getColumn() != coordinate2.getColumn()
                && coordinate1.getRow() != coordinate2.getRow()) {
            throw new IllegalArgumentException("Wrong ship location!");
        }

        if (coordinate1.getRow() == coordinate2.getRow()) {
            return Math.abs(coordinate1.getColumn() - coordinate2.getColumn()) + 1;
        }

        return Math.abs(coordinate1.getRow() - coordinate2.getRow()) + 1;
    }

    public static Coordinate calculateStartingIndex(Coordinate coordinate1,
                                                    Coordinate coordinate2) {
        if (coordinate1.getColumn() != coordinate2.getColumn()
                && coordinate1.getRow() != coordinate2.getRow()) {
            throw new IllegalArgumentException("Wrong ship location!");
        }

        if (coordinate1.getRow() == coordinate2.getRow()) {
            return coordinate1.getColumn() < coordinate2.getColumn() ?
                    coordinate1 : coordinate2;
        }

        return coordinate1.getRow() < coordinate2.getRow() ?
                coordinate1 : coordinate2;
    }

    public static boolean isHorizontally(Coordinate coordinate1,
                                         Coordinate coordinate2) {
        if (coordinate1.getColumn() != coordinate2.getColumn()
                && coordinate1.getRow() != coordinate2.getRow()) {
            throw new IllegalArgumentException("Wrong ship location!");
        }

        if (coordinate1.getRow() == coordinate2.getRow()) {
            return true;
        }

        return false;
    }

    public Coordinate(String coordinate) {
        if (!coordinate.matches(COORDINATES_REGEX)) {
            throw new IllegalArgumentException("You entered wrong coordinates!");
        }

        try {
            column = coordinateToArrayIndex(Integer.parseInt(coordinate.trim()
                    .substring(1)));
            row = coordinateToArrayIndex(coordinate.trim().charAt(0));
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("You entered wrong coordinates!", e);
        }
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    private static final String COORDINATES_REGEX = "[A-J]\\d+";
    private static final char   FIRST_ROW = 'A';
    private static final char   LAST_ROW = 'J';

    private final int column;
    private final int row;

    private int coordinateToArrayIndex(char coordinate) {
        if (FIRST_ROW > coordinate || LAST_ROW < coordinate) {
            throw new IllegalArgumentException("You entered wrong coordinates!");
        }

        return coordinate - FIRST_ROW;
    }

    private int coordinateToArrayIndex(int coordinate) {
        if (1 > coordinate || GameFieldModel.FIELD_ROWS_COLUMNS_COUNT < coordinate) {
            throw new IllegalArgumentException("You entered wrong coordinates!");
        }

        return coordinate - 1;
    }
}
