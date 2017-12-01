import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(100, 10);

        System.out.println("One process");
        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);
        long startTime = System.nanoTime();
        int minCost = pathPlan.bfs(nodes, 0, 0, nodes[0].length - 1).cost;
        long endTime = System.nanoTime();

        long totalTime = endTime - startTime;
        System.out.println("Time " + totalTime / 1000 + " cost " + minCost);


        System.out.println("Two processes");
        DijkstraAlgorithm threadOne = new DijkstraAlgorithm();
        threadOne.start();


        startTime = System.nanoTime();
        int[] costParser = new int[nodes[0].length];
        Map<Node,Integer> tempMap = new HashMap<>();
        System.out.println("Process one");
        synchronized (threadOne) {
            try {
                threadOne.initPathPlanning(nodes);
                Index index = pathPlan.bfs(nodes, 0, 0, nodes[0].length / 2 - 1);
                minCost = index.cost;
                tempMap =  index.map;
                threadOne.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < nodes[0].length; i++) {
                Node temp = nodes[nodes[0].length / 2 - 1][i];
                costParser[i] = tempMap.get(temp);
            }
        }

        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time " + totalTime / 1000 + " cost " + minCost);

        System.out.println("Process Two");

        DijkstraAlgorithm threadTwo = new DijkstraAlgorithm();
        threadTwo.start();
        startTime = System.nanoTime();
        int res = Integer.MAX_VALUE;
        synchronized (threadTwo) {
            try {
                threadTwo.initPathPlanning(nodes);
                for (int i = 0; i < nodes[0].length; i++) {
                    int temp = pathPlan.bfs(nodes, nodes[0].length / 2 - 1, i, nodes[0].length - 1).cost;
                    //System.out.println("temp and cost parser " + temp + " " + costParser[i]);
                    res = Math.min(res, temp + costParser[i]);
                }
                threadTwo.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time " + totalTime / 1000 + " cost " + res);
    }
}


