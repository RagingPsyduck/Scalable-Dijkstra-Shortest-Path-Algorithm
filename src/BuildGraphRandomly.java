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
                for (int k = 0; k < y; k++) {
                    if( k ==  j ) continue;
                    nodes[i][j].diagonal.add(nodes[i + 1][k]);
                }
            }
        }
        return nodes;
    }

}
