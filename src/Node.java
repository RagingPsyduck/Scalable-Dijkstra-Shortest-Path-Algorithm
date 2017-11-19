import java.util.*;

public class Node {
    int id;

    int rightCost;
    int downCost;
    int downRightCost;
    Node right;
    Node down;
    List<Node> diagonal = new ArrayList<>();

    Random rand = new Random();

    Node(int x) {
        id = x;
        rightCost = rand.nextInt(100);
        downCost = rand.nextInt(100);
        downRightCost = rand.nextInt(100);
    }
}