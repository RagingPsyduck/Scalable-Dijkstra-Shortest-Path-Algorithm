import java.util.*;

public class Node {
    int id;
    List<Edge> edges;
//    List<Integer> costs;
//    List<Node> connectedNodes;


    Node(int x) {
        id = x;
        edges = new ArrayList<>();
//        costs = new ArrayList<>();
//        connectedNodes = new ArrayList<>();
    }
}