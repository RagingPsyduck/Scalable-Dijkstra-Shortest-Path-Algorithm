import java.util.*;

public class Node {
    int id;
    List<Edge> edges;

    Node(int x) {
        id = x;
        edges = new ArrayList<>();
    }
}