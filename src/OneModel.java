public class OneModel {

    public void generatePath(Node[][] nodes){
        long startTime = System.nanoTime();
        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);
        int minCost = pathPlan.bfs(nodes, 0, 0, nodes.length - 1, nodes[0].length - 1).cost;
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("One process Time " + totalTime / 1000 + " cost " + minCost);
    }
}
