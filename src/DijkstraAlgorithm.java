

import java.util.*;

public class DijkstraAlgorithm extends Thread {
    Map<Node, Integer> distMap = new HashMap<>();
    Map<Node, Integer> originalDistMap = new HashMap<>();
    int row;
    int col;


    public void initPathPlanning(Node[][] nodes) {
        row = nodes.length;
        col = nodes[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distMap.put(nodes[i][j], Integer.MAX_VALUE);
            }
        }
        originalDistMap = new HashMap<>(distMap);
    }

    public Index bfs(Node[][] nodes, int startX, int startY, int endX, int endY) {
        Node startNode = nodes[startX][startY];
        Node endNode = nodes[endX][endY];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        distMap.put(startNode, 0);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curCost = distMap.get(cur);
            List<Edge> edges = cur.edges;

            int size = edges.size();
            for (int i = 0; i < size; i++) {
                Node tempNode = edges.get(i).nextNode;
                int tempCost = edges.get(i).cost;
                updateDistanceMap(distMap, tempNode, tempCost + curCost, queue);
            }
        }

        return new Index(distMap.get(endNode), distMap);
    }

    public void updateDistanceMap(Map<Node, Integer> distMap, Node node, int cost, Queue<Node> queue) {
        if (distMap.get(node) > cost ) {
            distMap.put(node, cost);
            queue.add(node);
        }
    }

    public void cleanDistanceMap(){
        distMap = new HashMap<>(originalDistMap);
    }
}
