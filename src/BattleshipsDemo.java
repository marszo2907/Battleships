import battleships.BattleshipsController;

public class BattleshipsDemo {
    public static void main(String[] args) {
        BattleshipsController battleshipsController = new BattleshipsController();

        while (0 != battleshipsController.getCurrentPlayerNodesLeft()) {
            battleshipsController.takeShot();
        }
    }
}