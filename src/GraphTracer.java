import java.util.List;

public class GraphTracer {
    public boolean hasPath(Node source, Node destination) {

        if(source.equals(destination)){
            return true;
        }
        else{
            for(Node node: source.getNeighbours()){
                if(hasPath(node,destination)){
                    return true;
                }
            }
        }
        return false;
    }
}
