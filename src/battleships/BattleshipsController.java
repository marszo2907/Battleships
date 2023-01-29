package battleships;

import java.util.Scanner;

public class BattleshipsController {
    public BattleshipsController() {
        gameFieldModel1 = new GameFieldModel();
        gameFieldModel2 = new GameFieldModel();
        isPlayer1Playing = true;

        Scanner scanner = new Scanner(System.in);

        addShips();
        switchPlayer();
        System.out.println("Press [ENTER] to pass the move to another player.");
        scanner.nextLine();
        addShips();
    }

    public void switchPlayer() {
        isPlayer1Playing = !isPlayer1Playing;
    }

    public void takeShot() {
        /* TODO */
    }

    public int getCurrentPlayerNodesLeft() {
        return isPlayer1Playing ? gameFieldModel1.getNodesLeft()
                : gameFieldModel2.getNodesLeft();
    }

    private GameFieldModel  gameFieldModel1;
    private GameFieldModel  gameFieldModel2;
    private boolean         isPlayer1Playing;

    private void addShips() {
        boolean         isHorizontally;
        boolean         isInputCorrect;
        Scanner         scanner = new Scanner(System.in);
        Coordinate      tempCoordinate1;
        Coordinate      tempCoordinate2;
        GameFieldModel  tempGameFieldModel;

        if (isPlayer1Playing) {
            tempGameFieldModel = gameFieldModel1;
            System.out.println("Player 1, place your ships on the game field:");
        } else {
            tempGameFieldModel = gameFieldModel2;
            System.out.println("Player 2, place your ships on the game field:");
        }

        for (var battleship : Battleship.values()) {
            isInputCorrect = false;
            System.out.print(GameFieldView.getInstance()
                    .getView(tempGameFieldModel));
            System.out.printf("Enter the coordinates of the %s (%d cells):%n",
                    battleship.getName(), battleship.getLength());

            while (!isInputCorrect) {
                try {
                    tempCoordinate1 = new Coordinate(scanner.next());
                    tempCoordinate2 = new Coordinate(scanner.next());

                    if (battleship.getLength()
                            != Coordinate.calculateLength(tempCoordinate1, tempCoordinate2)) {
                        throw new IllegalArgumentException("Wrong length of the %s!"
                                .formatted(battleship.getName()));
                    }

                    isHorizontally = Coordinate
                            .isHorizontally(tempCoordinate1, tempCoordinate2);
                    tempCoordinate1 = Coordinate
                            .calculateStartingIndex(tempCoordinate1,
                                    tempCoordinate2);

                    tempGameFieldModel.placeBattleship(battleship,
                            tempCoordinate1.getRow(),
                            tempCoordinate1.getColumn(), isHorizontally);

                    isInputCorrect = true;
                } catch (Exception e) {
                    isInputCorrect = false;
                    System.out.printf("Error! %s Try again:%n", e.getMessage());
                }
            }
        }
    }
}
