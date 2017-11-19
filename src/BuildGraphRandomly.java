import java.util.Random;

public class BuildGraphRandomly {
    public Node[][] buildGraphRandomly(int x, int y) {
        Node[][] nodes = new Node[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int id = i * x + j;
                nodes[i][j] = new Node(id);
                if (j != 0) nodes[i][j - 1].right = nodes[i][j];
                if (i != 0) nodes[i - 1][j].down = nodes[i][j];
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == x - 1) continue;
                Random rand = new Random();
                int randomColPos = rand.nextInt(y-1);
                while( randomColPos == j){
                    randomColPos = rand.nextInt(y-1);
                }
                
                nodes[i][j].diagonal = nodes[i+1][randomColPos];
            }
        }
        return nodes;
    }

}
