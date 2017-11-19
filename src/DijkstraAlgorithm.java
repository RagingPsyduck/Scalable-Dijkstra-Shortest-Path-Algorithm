

import java.util.*;

public class DijkstraAlgorithm {
    Map<Node, Integer> distMap = new HashMap<>();
    int row;
    int col;

    public List<String> generateShortestPath(Node[][] nodes, int startRow, int endRow) {
        List<String> res = new ArrayList<>();
        row = nodes.length;
        col = nodes[0].length;

        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < col; j++) {
                distMap.put(nodes[i][j],Integer.MAX_VALUE);
            }
        }
        return res;
    }

    public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return distMap.get(node1) - distMap.get(node2);
        }
    };


    public void bfs(List<String> path, Node[][] nodes, int startRow, int endRow) {
        Set<Node> isVisited = new HashSet<>();
        Node startNode = nodes[startRow][0];
        Node endNode = nodes[endRow][col - 1];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        distMap.put(startNode,0);
        isVisited.add(startNode);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                int cost = distMap.get(cur);
                Node rightNode = cur.right;
                int rightCost = cur.rightCost;



                Node downNode = cur.down;
                List<Node> diagonalNodes = cur.diagonal;
            }
        }


    }

}