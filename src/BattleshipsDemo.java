import battleships.BattleshipsController;

public class BattleshipsDemo {
    public static void main(String[] args) {
        BattleshipsController battleshipsController = new BattleshipsController();

        while (0 != battleshipsController.getCurrentPlayerNodesLeft()) {
            /* TODO */
        }

        System.out.println("You sank the last ship. You won. Congratulations!");
    }
}