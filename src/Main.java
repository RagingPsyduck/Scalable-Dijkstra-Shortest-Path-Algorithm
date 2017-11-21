public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(100, 10);

        System.out.println("One process");
        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);
        long startTime = System.nanoTime();
        int minCost = pathPlan.bfs(nodes, 0, 0, nodes[0].length - 1);
        long endTime = System.nanoTime();

        long totalTime = endTime - startTime;
        System.out.println("Time " + totalTime / 1000 + " cost " + minCost);


        System.out.println("Two processes");
        DijkstraAlgorithm threadOne = new DijkstraAlgorithm();
        threadOne.start();


        startTime = System.nanoTime();
        int[] costParser = new int[nodes[0].length];
        System.out.println("Process one");
        synchronized (threadOne) {
            try {

                threadOne.initPathPlanning(nodes);
                pathPlan.bfs(nodes, 0, 0, nodes[0].length / 2 - 1);
                threadOne.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < nodes[0].length; i++) {
                Node temp = nodes[nodes[0].length / 2 - 1][i];
                costParser[i] = threadOne.distMap.get(temp);
            }
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time " + totalTime / 1000 + " cost " + minCost);

        System.out.println("Process Two");

        DijkstraAlgorithm threadTwo = new DijkstraAlgorithm();
        threadTwo.start();
        startTime = System.nanoTime();
        int min = Integer.MAX_VALUE;
        synchronized (threadTwo) {
            try {
                threadTwo.initPathPlanning(nodes);
                for (int i = 0; i < nodes[0].length; i++) {
                    int res = pathPlan.bfs(nodes, nodes[0].length / 2 - 1, i, nodes[0].length - 1);
                    min = Math.min(min, res + costParser[i]);
                }
                threadTwo.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time " + totalTime / 1000 + " cost " + min);


    }
}


