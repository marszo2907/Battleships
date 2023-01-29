package battleships;

public class GameFieldModel {
    public static final int     FIELD_ROWS_COLUMNS_COUNT = 10;
    public static final char    FOG_OF_WAR = '~';
    public static final char    HIT = 'X';
    public static final char    MISS = 'M';
    public static final char    NODE = 'O';


    public GameFieldModel() {
        gameField = new char[FIELD_ROWS_COLUMNS_COUNT][FIELD_ROWS_COLUMNS_COUNT];
        nodesLeft = 0;

        for (int i = 0; gameField.length > i; ++i) {
            for (int j = 0; gameField[i].length > j; ++j) {
                gameField[i][j] = FOG_OF_WAR;
            }
        }
    }

    public char[][] getGameField() {
        return gameField;
    }

    public int getNodesLeft() {
        return nodesLeft;
    }

    public void placeBattleship(Battleship battleship, int startingRowIndex,
                                int startingColumnIndex, boolean isHorizontally) {
        for (int i = 0; battleship.getLength() > i; ++i) {
            try {
                if (isHorizontally) {
                    checkPlacement(startingRowIndex, startingColumnIndex + i);
                } else {
                    checkPlacement(startingRowIndex + i, startingColumnIndex);
                }
            } catch (Exception e) {
                throw e;
            }
        }

        for (int i = 0; battleship.getLength() > i; ++i) {
            if (isHorizontally) {
                gameField[startingRowIndex][startingColumnIndex + i] = NODE;
            } else {
                gameField[startingRowIndex + i][startingColumnIndex] = NODE;
            }

            nodesLeft++;
        }
    }

    public Shot takeShot(/* TODO */) {
        /* TODO */
        return null;
    }

    private char[][]    gameField;
    private int         nodesLeft;

    private boolean isShipSunk(/* TODO */) {
        /* TODO */
        return false;
    }

    private void checkPlacement(int rowIndex, int columnIndex) {
        if (0 > rowIndex || 0 > columnIndex
                || FIELD_ROWS_COLUMNS_COUNT <= rowIndex
                || FIELD_ROWS_COLUMNS_COUNT <= columnIndex) {
            throw new ArrayIndexOutOfBoundsException("Wrong ship location!");
        }

        int endingColumnIndex = Math.min(FIELD_ROWS_COLUMNS_COUNT - 1, columnIndex + 1);
        int endingRowIndex = Math.min(FIELD_ROWS_COLUMNS_COUNT - 1, rowIndex + 1);
        int startingColumnIndex = Math.max(0, columnIndex - 1);
        int startingRowIndex = Math.max(0, rowIndex - 1);

        for (int i = startingRowIndex; endingRowIndex >= i; ++i) {
            for (int j = startingColumnIndex; endingColumnIndex >= j; ++j) {
                if (FOG_OF_WAR != gameField[i][j]) {
                    throw new IllegalArgumentException("You placed it too" +
                            " close to another one!");
                }
            }
        }
    }
}
