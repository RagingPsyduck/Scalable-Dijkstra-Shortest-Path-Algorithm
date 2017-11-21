

import java.util.*;

public class DijkstraAlgorithm {
    Map<Node, Integer> distMap = new HashMap<>();
    int row;
    int col;

//    public List<String> generateShortestPath(Node[][] nodes, int startRow, int endRow) {
//        List<String> res = new ArrayList<>();
//        row = nodes.length;
//        col = nodes[0].length;
//        return res;
//    }
//
//    public class NodeComparator implements Comparator<Node> {
//        @Override
//        public int compare(Node node1, Node node2) {
//            return distMap.get(node1) - distMap.get(node2);
//        }
//    };

    public void initPathPlanning(Node[][] nodes) {
        row = nodes.length;
        col = nodes[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distMap.put(nodes[i][j], Integer.MAX_VALUE);
            }
        }
    }

    public int bfs(Node[][] nodes) {
        Set<Node> isVisited = new HashSet<>();
        Node startNode = nodes[0][0];
        Node endNode = nodes[row - 1][col - 1];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        distMap.put(startNode, 0);
        isVisited.add(startNode);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curCost = distMap.get(cur);
            List<Node> connectedNodes = cur.connectedNodes;
            List<Integer> costs = cur.costs;
            int size  = connectedNodes.size();
            for (int i = 0; i < size; i++) {
                Node tempNode = connectedNodes.get(i);
                int tempCost = costs.get(i);
                updateDistanceMap(distMap, tempNode, tempCost + curCost,queue);
            }
        }
        return distMap.get(endNode);
    }

    public void updateDistanceMap(Map<Node, Integer> distMap, Node node, int cost,Queue<Node> queue) {
        if (distMap.get(node) > cost) {
            distMap.put(node, cost);
            queue.add(node);
        }
    }

}