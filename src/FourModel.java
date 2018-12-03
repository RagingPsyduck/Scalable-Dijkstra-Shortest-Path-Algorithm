import java.util.Arrays;

public class FourModel {
    public void generatePath(Node[][] nodes) {
        System.out.println("--------------------------");
        Util util = new Util();
        util.divideMap(nodes, nodes.length / 4);
        util.divideMap(nodes, nodes.length / 2);
        util.divideMap(nodes, nodes.length / 4 * 3);
        int[] costParser = new int[4];
        int[] costParser2 = new int[4];
        int[] costParser3 = new int[4];

        DijkstraAlgorithm pathPlan1 = new DijkstraAlgorithm();
        pathPlan1.start();
        long startTime = System.nanoTime();

        synchronized (pathPlan1) {
            try {
                pathPlan1.initPathPlanning(nodes);
                for (int i = 0; i < costParser.length; i++) {
                    costParser[i] = pathPlan1.bfs(nodes, 0, 0, nodes.length / 4, i).cost;
                }
                pathPlan1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("Process 1 Time " + totalTime / 1000);
            DijkstraAlgorithm pathPlan2 = new DijkstraAlgorithm();
            pathPlan2.initPathPlanning(nodes);
            startTime = System.nanoTime();
            long totalCleanUpTime = 0;
            Arrays.fill(costParser2, Integer.MAX_VALUE);
            synchronized (pathPlan1) {
                pathPlan2.cleanDistanceMap();
                pathPlan2.initPathPlanning(nodes);
                costParserHelper(nodes, costParser, costParser2, pathPlan2, nodes.length / 4, nodes.length / 2);
            }
            endTime = System.nanoTime();
            totalTime = endTime - startTime - totalCleanUpTime;
            System.out.println("Process 2 Time " + totalTime / 1000);

            DijkstraAlgorithm pathPlan3 = new DijkstraAlgorithm();
            pathPlan3.initPathPlanning(nodes);
            startTime = System.nanoTime();
            totalCleanUpTime = 0;
            synchronized (pathPlan1) {
                pathPlan3.cleanDistanceMap();
                pathPlan3.initPathPlanning(nodes);
                costParserHelper(nodes, costParser2, costParser3, pathPlan3, nodes.length / 2, nodes.length / 4 * 3);
            }
            endTime = System.nanoTime();
            totalTime = endTime - startTime - totalCleanUpTime;
            System.out.println("Process 3 Time " + totalTime / 1000);

            DijkstraAlgorithm pathPlan4 = new DijkstraAlgorithm();
            pathPlan4.initPathPlanning(nodes);

            startTime = System.nanoTime();
            totalCleanUpTime = 0;
            int res = Integer.MAX_VALUE;


            synchronized (pathPlan1) {
                for (int i = 0; i < costParser.length; i++) {
                    pathPlan4.cleanDistanceMap();
                    pathPlan4.initPathPlanning(nodes);
                    int cost = pathPlan4.bfs(nodes, nodes.length / 4 * 3, i, nodes.length - 1, nodes[0].length - 1).cost;
                    res = Math.min(res, cost + costParser3[i]);
                }
            }

            endTime = System.nanoTime();
            totalTime = endTime - startTime - totalCleanUpTime;
            System.out.println("Process 4 Time " + totalTime / 1000 + " cost " + res);
        }
    }

    private void costParserHelper(Node[][] nodes, int[] preParser, int[] curParser, DijkstraAlgorithm plan, int startX, int endX) {
        Arrays.fill(curParser,Integer.MAX_VALUE);
        for (int i = 0; i < preParser.length; i++) {
            for (int j = 0; j < preParser.length; j++) {
                plan.cleanDistanceMap();
                plan.initPathPlanning(nodes);
                int cost = plan.bfs(nodes, startX, j, endX, i).cost;
                curParser[i] = Math.min(curParser[i], cost + preParser[j]);
            }
        }
    }
}
