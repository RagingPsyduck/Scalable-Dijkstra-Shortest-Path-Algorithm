

import java.util.*;

public class DijkstraAlgorithm {
    Map<Node, Integer> distMap = new HashMap<>();
    int row;
    int col;

    public List<String> generateShortestPath(Node[][] nodes, int startRow, int endRow) {
        List<String> res = new ArrayList<>();
        row = nodes.length;
        col = nodes[0].length;


        return res;
    }

    public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return distMap.get(node1) - distMap.get(node2);
        }
    };

    public void initPathPlanning(Node[][] nodes){

        row = nodes.length;
        col = nodes[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distMap.put(nodes[i][j], Integer.MAX_VALUE);
            }
        }
    }


    public int bfs(Node[][] nodes, int startRow, int endRow) {



        Set<Node> isVisited = new HashSet<>();
        Node startNode = nodes[startRow][0];
        Node endNode = nodes[endRow][col - 1];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        distMap.put(startNode, 0);
        isVisited.add(startNode);

        while (!queue.isEmpty()) {

            Node cur = queue.poll();
            int curCost = distMap.get(cur);


            Node rightNode = cur.right;
            queue.add(rightNode);
            int rightCost = cur.rightCost;
            updateDistanceMap(distMap,rightNode,rightCost+curCost);


            Node downNode = cur.down;
            queue.add(downNode);
            int downCost = cur.downCost;
            updateDistanceMap(distMap,downNode,downCost+curCost);


            Node diagonalNodes = cur.diagonal;
            queue.add(diagonalNodes);
            int diagonalCost = cur.diagonalCost;
            updateDistanceMap(distMap,diagonalNodes,diagonalCost+curCost);
        }
        return distMap.get(endNode);
    }

    public void updateDistanceMap(Map<Node, Integer> distMap, Node node, int cost) {
        if ( node == null ){
            System.out.print("fuck");
        }
        if(distMap.get(node) > cost ){
            distMap.put(node,cost);
        } else {
            return;
        }
    }

}