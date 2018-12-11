public class Main {
    public static void main(String[] args) {
        BuildGraphRandomly buildGraphNodes = new BuildGraphRandomly();
        Node[][] nodes = buildGraphNodes.buildGraphRandomly(20, 10);
        // One process
        OneModel oneModel = new OneModel();
        oneModel.generatePath(nodes);

        // Two processes
        TwoModel twoModel = new TwoModel();
        twoModel.generatePath(nodes);

        // Four processes
        FourModel fourModel = new FourModel();
        fourModel.generatePath(nodes);
    }
}



