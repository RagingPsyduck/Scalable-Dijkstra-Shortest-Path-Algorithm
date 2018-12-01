import java.util.Random;

public class BuildGraphRandomly {
    public Node[][] buildGraphRandomly(int x, int y) {
        Random rand = new Random();

        Node[][] nodes = new Node[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int id = i * y + j;
                nodes[i][j] = new Node(id);
                if (j != 0) {
                    int randomValue = rand.nextInt(100);
                    nodes[i][j - 1].edges.add(new Edge(randomValue, nodes[i][j]));
                    nodes[i][j].edges.add(new Edge(randomValue, nodes[i][j - 1]));

                }
                if (i != 0) {
                    int randomValue = rand.nextInt(100);
                    nodes[i - 1][j].edges.add(new Edge(randomValue, nodes[i][j]));
                    nodes[i][j].edges.add(new Edge(randomValue, nodes[i - 1][j]));
                }
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (rand.nextInt(10) > 2) continue;
                if (i == x - 1) continue;
                int randomColPos = y - 2 + rand.nextInt(5);
                if (randomColPos >= y) continue;
                while (randomColPos == j) {
                    randomColPos = rand.nextInt(y - 1);
                }
                int randomValue = rand.nextInt(100);

                if (rand.nextInt(10) > 2) nodes[i][j].edges.add(new Edge(randomValue, nodes[i + 1][randomColPos]));
                if (rand.nextInt(10) > 2) nodes[i + 1][randomColPos].edges.add(new Edge(randomValue, nodes[i][j]));

            }
        }
        return nodes;
    }

}
