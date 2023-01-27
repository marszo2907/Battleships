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

    public void placeBattleship(/* TODO */) {
        /* TODO */
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
}
