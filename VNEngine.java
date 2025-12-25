import java.util.HashMap;
import java.util.Map;

public class VNEngine {
	private Map<String, Node> nodes;
	private String currentNodeId;

	public VNEngine() {
		nodes = new HashMap<>();
	}

	public void addNode(Node node) {
		nodes.put(node.id, node);
	}

	public void goTo(String id) {
		Node node = nodes.get(id);
		if(node == null) {
			//System.out.println("Node not found: " + id + "\n");	
			System.out.println("Story has ended");	
			return;
		}

		currentNodeId = id;
		node.enter(this);
	}

	public String getCurrentNodeId() {
		return currentNodeId;
	}
}
