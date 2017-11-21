import java.util.*;

public class Node {
    int id;
    List<Integer> costs;
    List<Node> connectedNodes;


    Node(int x) {
        id = x;
        costs = new ArrayList<>();
        connectedNodes = new ArrayList<>();
    }
}