import java.util.List;

public class Util {

    public void divideMap(Node[][] nodes, int lineNumber) {

        int col = nodes[0].length;
        int preLineMinId = col * (lineNumber - 1);
        int preLineMaxId = col * lineNumber - 1;


        for (int i = 0; i < col; i++) {
            List<Edge> edges = nodes[lineNumber][i].edges;
            for (int j = 0; j < edges.size(); j++) {
                int id = edges.get(j).nextNode.id;
                if (id >= preLineMinId && id <= preLineMaxId) {
                    edges.remove(j);
                }
            }
        }
    }
}
