package battleships;

public class GameFieldView {
    public static GameFieldView getInstance() {
        if (null == view) {
            view = new GameFieldView();
        }

        return view;
    }

    public String getView(GameFieldModel player) {
        StringBuilder stringBuilder = new StringBuilder(" ");

        for (int i = 0; GameFieldModel.FIELD_ROWS_COLUMNS_COUNT > i; ++i) {
            stringBuilder.append(' ').append(i + 1);
        }
        
        stringBuilder.append('\n');

        for (int i = 0; GameFieldModel.FIELD_ROWS_COLUMNS_COUNT > i; ++i) {
            stringBuilder.append((char) (i + 'A'));

            for (int j = 0; GameFieldModel.FIELD_ROWS_COLUMNS_COUNT > j; ++j) {
                stringBuilder.append(' ').append(player.getGameField()[i][j]);
            }

            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public String getView(GameFieldModel opponent, GameFieldModel player) {
        StringBuilder stringBuilder = new StringBuilder(" ");

        /* Append an opponent's game field */
        for (int i = 0; GameFieldModel.FIELD_ROWS_COLUMNS_COUNT > i; ++i) {
            stringBuilder.append(' ').append(i + 1);
        }

        stringBuilder.append('\n');

        for (int i = 0; GameFieldModel.FIELD_ROWS_COLUMNS_COUNT > i; ++i) {
            stringBuilder.append((char) (i + 'A'));

            for (int j = 0; GameFieldModel.FIELD_ROWS_COLUMNS_COUNT > j; ++j) {
                char tempField = opponent.getGameField()[i][j];

                stringBuilder.append(' ');
                if (GameFieldModel.NODE == tempField) {
                    stringBuilder.append(GameFieldModel.FOG_OF_WAR);
                } else {
                    stringBuilder.append(tempField);
                }
            }

            stringBuilder.append('\n');
        }

        /* Append a footer */
        stringBuilder.append("----------------------\n");

        /* Append a player's game field */
        stringBuilder.append(getView(player));

        return stringBuilder.toString();
    }

    private static GameFieldView view;

    private GameFieldView() {}
}
