import java.util.ArrayDeque;
import java.util.Queue;

void main() {
	VNEngine engine = new VNEngine();

	//start node
	Queue<String> sampleLines = new ArrayDeque<>();
	sampleLines.add("Yawns! Good Morning kudasai uwuwuwu");
	sampleLines.add("What a beautiful morning! Gahhh");
	sampleLines.add("I should prepare for school humu");
	engine.addNode(new NodeDialogue("room", sampleLines, "school"));
	sampleLines.clear();

	//first day node
	sampleLines.add("Woah Im in school how did that happen");
	sampleLines.add("Teehee: idfk");
	sampleLines.add("Teehee: nodes are cool tho look at how that works");
	sampleLines.add("kill me dont talk about that shi");
	engine.addNode(new NodeDialogue("school", sampleLines, "room"));

	engine.goTo("room");
}
