public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(15, 10);

        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);

        long startTime = System.nanoTime();
        int minSteps = pathPlan.bfs(nodes);
        long endTime   = System.nanoTime();

        long totalTime = endTime - startTime;
        System.out.print("Time " + totalTime/1000000);

    }

}


