import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(20, 10);

        // One process
        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);
        long startTime = System.nanoTime();

        int minCost = pathPlan.bfs(nodes, 0, 0, nodes.length - 1, nodes[0].length - 1).cost;
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("One process Time " + totalTime / 1000 + " cost " + minCost);

        // Two processes
        System.out.println("--------------------------");
        DijkstraAlgorithm algo = new DijkstraAlgorithm();
        algo.start();

        startTime = System.nanoTime();
        int[] costParser = new int[nodes[0].length];
        //Map<Node, Integer> tempMap = new HashMap<>();
        synchronized (algo) {
            try {
                algo.initPathPlanning(nodes);
                for (int i = 0; i < nodes[0].length; i++) {
                    costParser[i] = pathPlan.bfs(nodes, 0, 0, nodes.length / 2 - 1, i).cost;
                }
                algo.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            StringBuilder sb = new StringBuilder();
            for (int i : costParser) {
                sb.append(i).append(" ");
            }

            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            System.out.println("Process one Time " + totalTime / 1000 + " cost " + sb.toString());

            startTime = System.nanoTime();
            DijkstraAlgorithm pathPlan2 = new DijkstraAlgorithm();
            pathPlan2.initPathPlanning(nodes);

            int res = Integer.MAX_VALUE;
            synchronized (algo) {
                int startX = nodes.length / 2 - 1;
                for (int i = 0; i < nodes[0].length; i++) {
                    int temp = pathPlan2.bfs(nodes, startX, i, nodes.length - 1, nodes[0].length - 1).cost;
                    res = Math.min(res, temp + costParser[i]);
                }
            }

            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            System.out.println("Process Two Time " + totalTime / 1000 + " cost " + res);

            // Four processes
//        System.out.println("--------------------------");
//        DijkstraAlgorithm algo = new DijkstraAlgorithm();
//        startTime = System.nanoTime();
//        int[] costParser = new int[nodes[0].length];
//        Map<Node, Integer> tempMap;
//
//        synchronized (algo) {
//            algo.initPathPlanning(nodes);
//            Index index = pathPlan.bfs(nodes, 0, 0, nodes.length / 4);
//            tempMap = index.map;
//            for (int i = 0; i < nodes[0].length; i++) {
//                Node temp = nodes[nodes.length / 4 - 1][i];
//                costParser[i] = tempMap.get(temp);
//            }
//        }
//
//        endTime = System.nanoTime();
//        totalTime = endTime - startTime;
//        System.out.println("Process one Time " + totalTime / 1000);
//
//        startTime = System.nanoTime();
//        int res = Integer.MAX_VALUE;
//        synchronized (algo) {
//            algo.initPathPlanning(nodes);
//            for (int i = 0; i < nodes[0].length; i++) {
//                Index index = pathPlan.bfs(nodes, nodes.length / 4, i, nodes.length / 4 * 2);
//                minCost = index.cost;
//                tempMap = index.map;
//                res = Math.min(res, minCost + costParser[i]);
//            }
//            for (int i = 0; i < nodes[0].length; i++) {
//                Node temp = nodes[nodes.length / 4][i];
//                costParser[i] = tempMap.get(temp);
//            }
//        }
//
//        endTime = System.nanoTime();
//        totalTime = endTime - startTime;
//        System.out.println("Process Two Time " + totalTime / 1000);
//
//        // Three process
//        startTime = System.nanoTime();
//        res = Integer.MAX_VALUE;
//        synchronized (algo) {
//            algo.initPathPlanning(nodes);
//
//            for (int i = 0; i < nodes[0].length; i++) {
//                Index index = pathPlan.bfs(nodes, nodes.length / 4 * 2, i, nodes.length / 4 * 3);
//                minCost = index.cost;
//                tempMap = index.map;
//                res = Math.min(res, minCost + costParser[i]);
//            }
//            for (int i = 0; i < nodes[0].length; i++) {
//                Node temp = nodes[nodes.length / 4 * 3][i];
//                costParser[i] = tempMap.get(temp);
//            }
//        }
//
//        endTime = System.nanoTime();
//        totalTime = endTime - startTime;
//        System.out.println("Process Three Time " + totalTime / 1000);
//
//        startTime = System.nanoTime();
//        res = Integer.MAX_VALUE;
//        synchronized (algo) {
//            algo.initPathPlanning(nodes);
//            for (int i = 0; i < nodes[0].length; i++) {
//                Index index = pathPlan.bfs(nodes, nodes.length / 4 * 3, i, nodes.length - 1);
//                minCost = index.cost;
//                tempMap = index.map;
//                res = Math.min(res, minCost + costParser[i]);
//            }
//            for (int i = 0; i < nodes[0].length; i++) {
//                Node temp = nodes[nodes.length - 1][i];
//                costParser[i] = tempMap.get(temp);
//            }
//        }
//
//        endTime = System.nanoTime();
//        totalTime = endTime - startTime;
//        System.out.println("Process Four Time " + totalTime / 1000);
        }
    }
}


