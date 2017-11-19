public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(10, 10);

        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);
        int res = pathPlan.bfs(nodes);
        System.out.print(res);

    }

}


