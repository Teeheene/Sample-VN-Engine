import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Scanner;

class NodeDialogue extends Node {
	Queue<String> lines;
	String nextNodeId;

	public NodeDialogue(String id, Queue<String> lines, String nextNodeId) {
		this.id = id;
		this.lines = new ArrayDeque<>(lines); 
		this.nextNodeId = nextNodeId;
	}	

	@Override
	void enter(VNEngine engine) {
		Scanner scanner = new Scanner(System.in);

		//copies lines for node reusability
		Queue<String> dialogue = new ArrayDeque<>(lines);
		while(!dialogue.isEmpty()) {
			System.out.println("\n" + dialogue.poll() + " ");
			scanner.nextLine();
		}
		engine.goTo(nextNodeId);
	}
}

