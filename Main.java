import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;

void main() {
	VNEngine engine = new VNEngine();

	// --- Room Node ---
	Queue<Line> roomLines = new ArrayDeque<>();
	roomLines.add(new Line("Yawns! Good Morning kudasai uwuwuwu"));
	roomLines.add(new Line("What a beautiful morning! Gahhh"));
	roomLines.add(new Line("I should prepare for school humu", 
		new ArrayList<>(List.of(
			new CharacterState("Alice", Position.LEFT, Emotion.HAPPY),
			new CharacterState("Bob", Position.RIGHT, Emotion.NEUTRAL)
		))
	));

	NodeDialogue roomNode = new NodeDialogue("room", roomLines, "school");
	roomNode.setBackgroundPath("bg_room.png");
	roomNode.setMusicPath("morning_theme.mp3");
	engine.addNode(roomNode);

	// --- School Node ---
	Queue<Line> schoolLines = new ArrayDeque<>();
	schoolLines.add(new Line("Woah I'm in school how did that happen"));
	schoolLines.add(new Line("Teehee: idfk"));
	schoolLines.add(new Line("Teehee: nodes are cool tho look at how that works",
		new ArrayList<>(List.of(new CharacterState("Alice", Position.CENTER, Emotion.BLUSHING)))
	));
	schoolLines.add(new Line("Kill me don't talk about that shi"));

	NodeDialogue schoolNode = new NodeDialogue("school", schoolLines, "room");
	schoolNode.setBackgroundPath("bg_school.png");
	schoolNode.setMusicPath("school_theme.mp3");
	engine.addNode(schoolNode);

	// --- Start engine ---
	engine.goTo("room");
}
