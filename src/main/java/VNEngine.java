import java.util.HashMap;
import java.util.Map;

public class VNEngine {
	private Map<String, Node> nodes;
	private String currentNodeId;
	private GameState gameState;

	public VNEngine() {
		gameState = new GameState();
		nodes = new HashMap<>();
	}

	public GameState getGameState() {
		return gameState;
	}

	public void addNode(Node node) {
		nodes.put(node.id, node);
	}

	public void goTo(String id) {
		if(id == null) {
			System.out.println("Story has ended");
			return;
		}

		Node node = nodes.get(id);
		if(node == null) {
			String path = id + ".yaml";
			NodeLoader loader = new NodeLoader(path);
			node = loader.loadNode();

			if(node == null) {
				System.out.println("Node not found: " + id);	
				return;
			}
		}

		currentNodeId = id;
		node.enter(this);
	}

	public String getCurrentNodeId() {
		return currentNodeId;
	}
}
