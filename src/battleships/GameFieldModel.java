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

    public Shot takeShot(Coordinate coordinate) {
        if (NODE == gameField[coordinate.getRow()][coordinate.getColumn()]) {
            gameField[coordinate.getRow()][coordinate.getColumn()] = HIT;
            nodesLeft--;

            if (isShipSunk(coordinate.getRow(), coordinate.getColumn())) {
                return Shot.SANK;
            } else {
                return Shot.HIT;
            }
        } else if (FOG_OF_WAR ==
                gameField[coordinate.getRow()][coordinate.getColumn()]) {
            gameField[coordinate.getRow()][coordinate.getColumn()] = MISS;

            return Shot.MISS;
        }

        return Shot.DUPLICATE;
    }

    private char[][]    gameField;
    private int         nodesLeft;

    private boolean isShipSunk(int rowIndex, int columnIndex) {
        int     bottomRowIndex = Math.min(FIELD_ROWS_COLUMNS_COUNT - 1, rowIndex + 1);
        boolean isHorizontally;
        int     leftColumnIndex;
        int     tempIndex;
        int     topRowIndex = Math.max(0, rowIndex - 1);

        if (topRowIndex != rowIndex && (NODE == gameField[topRowIndex][columnIndex]
                || HIT == gameField[topRowIndex][columnIndex])) {
            isHorizontally = false;
        } else if (bottomRowIndex != rowIndex
                && (NODE == gameField[bottomRowIndex][columnIndex]
                || HIT == gameField[bottomRowIndex][columnIndex])) {
            isHorizontally = false;
        } else {
            isHorizontally = true;
        }

        if (isHorizontally) {
            leftColumnIndex = columnIndex;
            tempIndex = columnIndex;
            while (0 != leftColumnIndex && leftColumnIndex == tempIndex) {
                tempIndex = Math.max(0, tempIndex - 1);

                if (NODE == gameField[rowIndex][tempIndex]
                        || HIT == gameField[rowIndex][tempIndex]) {
                    leftColumnIndex = tempIndex;
                }
            }

            while (FIELD_ROWS_COLUMNS_COUNT != leftColumnIndex
                    && FOG_OF_WAR != gameField[rowIndex][leftColumnIndex]
                    && MISS != gameField[rowIndex][leftColumnIndex]) {
                if (NODE == gameField[rowIndex][leftColumnIndex]) {
                    return false;
                }

                leftColumnIndex++;
            }
        } else {
            tempIndex = rowIndex;
            topRowIndex = rowIndex;

            while (0 != topRowIndex && topRowIndex == tempIndex) {
                tempIndex = Math.max(0, tempIndex - 1);

                if (NODE == gameField[tempIndex][columnIndex]
                        || HIT == gameField[tempIndex][columnIndex]) {
                    topRowIndex = tempIndex;
                }
            }

            while (FIELD_ROWS_COLUMNS_COUNT != topRowIndex
                    && FOG_OF_WAR != gameField[topRowIndex][columnIndex]
                    && MISS != gameField[topRowIndex][columnIndex]) {
                if (NODE == gameField[topRowIndex][columnIndex]) {
                    return false;
                }

                topRowIndex++;
            }

        }

        return true;
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
