package battleships;

public enum Battleship {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    CRUISER(3, "Cruiser"),
    SUBMARINE(3, "Submarine"),
    DESTROYER(2, "Destroyer");

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    Battleship(int length, String name) {
        this.length = length;
        this.name = name;
    }

    private final int       length;
    private final String    name;
}
