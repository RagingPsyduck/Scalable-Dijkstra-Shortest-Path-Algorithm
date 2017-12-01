import java.util.Map;

public class Index {
    int cost;
    Map<Node,Integer> map;


    Index(int x, Map<Node,Integer> distMap) {
        cost = x;
        map = distMap;
    }
}

