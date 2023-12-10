public class Move {
    record Coord(int x, int y) { }
    public Coord current;
    public Coord previous;
    public int direction;
}
