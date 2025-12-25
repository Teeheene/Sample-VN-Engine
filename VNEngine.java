import java.util.HashMap;
import java.util.Map;

class VNEngine {
	Map<String, Node> nodes;
	String currentNodeId;

	VNEngine() {
		nodes = new HashMap<>();
	}

	void addNode(Node node) {
		nodes.put(node.id, node);
	}

	void goTo(String id) {
		Node node = nodes.get(id);
		if(node == null) {
			//System.out.println("Node not found: " + id + "\n");	
			System.out.println("Story has ended");	
			return;
		}

		currentNodeId = id;
		node.enter(this);
	}

	String getCurrentNodeId() {
		return currentNodeId;
	}
}
