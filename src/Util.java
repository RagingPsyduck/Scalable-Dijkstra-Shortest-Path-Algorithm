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

    public void createPorts(Node[][] nodes, int lineNumber) {
        int y = nodes[0].length;
        for (int j = 4; j < y; j++) {
            Node temp = nodes[lineNumber][j];
            int id = temp.id;
            List<Edge> edges = temp.edges;
            for (Edge edge : edges) {
                Node nextNode = edge.nextNode;
                for (int i = 0; i < nextNode.edges.size(); i++) {
                    if (nextNode.edges.get(i).nextNode.id == id) {
                        nextNode.edges.remove(i);
                    }
                }
            }
        }
    }
}
