package battleships;

import java.util.Scanner;

public class BattleshipsController {
    public BattleshipsController() {
        gameFieldModel1 = new GameFieldModel();
        gameFieldModel2 = new GameFieldModel();
        isPlayer1Playing = true;

        addShips();
        addShips();
    }

    public void switchPlayer() {
        isPlayer1Playing = !isPlayer1Playing;
    }

    public void takeShot() {
        boolean         isInputCorrect = false;
        Scanner         scanner = new Scanner(System.in);
        GameFieldModel  tempGameFieldModel;

        if (isPlayer1Playing) {
            tempGameFieldModel = gameFieldModel2;
            System.out.print(GameFieldView.getInstance()
                    .getView(gameFieldModel1, gameFieldModel2));
            System.out.println("Player 1, take a shot:");
        } else {
            tempGameFieldModel = gameFieldModel1;
            System.out.print(GameFieldView.getInstance()
                    .getView(gameFieldModel2, gameFieldModel1));
            System.out.println("Player 2, take a shot:");
        }

        while (!isInputCorrect) {
            try {
                isInputCorrect = true;

                switch (tempGameFieldModel.takeShot(
                        new Coordinate(scanner.nextLine()))) {
                    case HIT:
                        System.out.println("You hit a ship!");
                        break;
                    case MISS:
                        System.out.println("You missed!");
                        break;
                    case SANK:
                        if (0 == tempGameFieldModel.getNodesLeft()) {
                            System.out.println("You sank the last ship! Congratulations!");
                        } else {
                            System.out.println("You sank a ship!");
                        }
                        break;
                    case DUPLICATE:
                        isInputCorrect = false;
                        System.out.println("You entered the same coordinate" +
                                " twice! Try again:");
                        break;
                }
            } catch (Exception e) {
                isInputCorrect = false;
                System.out.printf("Error! %s Try again:%n", e.getMessage());
            }
        }

        if (0 != tempGameFieldModel.getNodesLeft()) {
            System.out.println("Press [ENTER] to pass the move to another player.");
            scanner.nextLine();
        }

        switchPlayer();
    }

    public int getCurrentPlayerNodesLeft() {
        return isPlayer1Playing ? gameFieldModel1.getNodesLeft()
                : gameFieldModel2.getNodesLeft();
    }

    private final GameFieldModel    gameFieldModel1;
    private final GameFieldModel    gameFieldModel2;
    private boolean                 isPlayer1Playing;

    private void addShips() {
        boolean         isHorizontally;
        boolean         isInputCorrect;
        Scanner         scanner = new Scanner(System.in);
        Coordinate      tempCoordinate1;
        Coordinate      tempCoordinate2;
        GameFieldModel  tempGameFieldModel;

        if (isPlayer1Playing) {
            tempGameFieldModel = gameFieldModel1;
            System.out.println("Player 1, place your ships on the game field!");
        } else {
            tempGameFieldModel = gameFieldModel2;
            System.out.println("Player 2, place your ships on the game field!");
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

                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                    isInputCorrect = true;
                } catch (Exception e) {
                    isInputCorrect = false;
                    System.out.printf("Error! %s Try again:%n", e.getMessage());
                }
            }
        }

        System.out.print(GameFieldView.getInstance()
                .getView(tempGameFieldModel));
        switchPlayer();
        System.out.println("Press [ENTER] to pass the move to another player.");
        scanner.nextLine();
    }
}
