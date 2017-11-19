import java.util.Random;

public class BuildGraphRandomly {
    public Node[][] buildGraphRandomly(int x, int y) {
        Random rand = new Random();


        Node[][] nodes = new Node[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int id = i * x + j;
                nodes[i][j] = new Node(id);
                if (j != 0) {
                    nodes[i][j - 1].connectedNodes.add(nodes[i][j]);
                    nodes[i][j - 1].costs.add(rand.nextInt(100));
                }
                if (i != 0) {
                    nodes[i - 1][j].connectedNodes.add(nodes[i][j]);
                    nodes[i - 1][j].costs.add(rand.nextInt(100));
                }
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == x - 1) continue;
                int randomColPos = rand.nextInt(y-1);
                while( randomColPos == j){
                    randomColPos = rand.nextInt(y-1);
                }

                nodes[i][j].connectedNodes.add(nodes[i+1][randomColPos]);
                nodes[i][j].costs.add(rand.nextInt(100));
            }
        }
        return nodes;
    }

}
