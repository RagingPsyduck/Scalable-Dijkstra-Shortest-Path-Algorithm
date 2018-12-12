public class TwoModel extends Thread {
    public void generatePath(Node[][] nodes) {
        System.out.println("--------------------------");
        Util util = new Util();
        int divideLine = nodes.length / 2;
        util.divideMap(nodes, divideLine);
        DijkstraAlgorithm pathPlan1 = new DijkstraAlgorithm();
        pathPlan1.start();
        long startTime = System.nanoTime();
        int[] costParser = new int[4];
        synchronized (pathPlan1) {
            try {
                pathPlan1.initPathPlanning(nodes);
                for (int i = 0; i < costParser.length; i++) {
                    costParser[i] = pathPlan1.bfs(nodes, 0, 0, nodes.length / 2, i).cost;
                }

                pathPlan1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("Process one Time " + totalTime / 1000);
        }

        DijkstraAlgorithm pathPlan2 = new DijkstraAlgorithm();
        pathPlan2.initPathPlanning(nodes);
        startTime = System.nanoTime();
        long totalCleanUpTime = 0;
        int res = Integer.MAX_VALUE;
        synchronized (pathPlan1) {
            int startX = nodes.length / 2;
            for (int i = 0; i < costParser.length; i++) {
                //long  tempStartTime = System.nanoTime();
                pathPlan2.cleanDistanceMap();
                pathPlan2.initPathPlanning(nodes);
                //long  tempEndTime = System.nanoTime();
                //totalCleanUpTime += tempEndTime - tempStartTime;
                int cost = pathPlan2.bfs(nodes, startX, i, nodes.length - 1, nodes[0].length - 1).cost;
                res = Math.min(res, cost + costParser[i]);
            }
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime - totalCleanUpTime;
        System.out.println("Process Two Time " + totalTime / 1000 + " cost " + res);
    }
}
