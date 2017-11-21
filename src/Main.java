public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(10, 10);

        DijkstraAlgorithm pathPlan = new DijkstraAlgorithm();
        pathPlan.initPathPlanning(nodes);

        long startTime = System.nanoTime();
        int minSteps = pathPlan.bfs(nodes);
        long endTime   = System.nanoTime();

        long totalTime = endTime - startTime;
        System.out.println("Time " + totalTime/1000  + " cost " + minSteps );




        
        Fork threadOne = new Fork();
        threadOne.start();

        synchronized (threadOne){
            try{
                System.out.println("Waiting for b to complete...");
                threadOne.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Total is: " + threadOne.total);
        }

    }
}


