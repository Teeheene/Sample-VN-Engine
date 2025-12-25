import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;

void main() {
	VNEngine engine = new VNEngine();

	// --- NODE 2 : MORNING ---
	Queue<Line> morningLines = new ArrayDeque<>();
	morningLines.add(new Line(
			"Alice: What a beautiful morning!",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.LEFT, Emotion.HAPPY)
			))
	));
	morningLines.add(new Line(
			"Alice: How are you Bob!",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.LEFT, Emotion.HAPPY),
					new CharacterState("Bob", Position.RIGHT, Emotion.NEUTRAL)
			))
	));	
	morningLines.add(new Line(
			"Bob: Awful.",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.LEFT, Emotion.HAPPY),
					new CharacterState("Bob", Position.RIGHT, Emotion.SAD)
			))
	));

	engine.addNode(new NodeDialogue("room", morningLines, "choice"));

	// --- NODE 3 : CHOICE ---
	Queue<Line> choiceLines = new ArrayDeque<>();
	choiceLines.add(new Line(
			"Alice: Bobbb I can't decide!",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.SAD),
					new CharacterState("Bob", Position.RIGHT, Emotion.SAD)
			))
	));
	choiceLines.add(new Line(
			"Bob: Decide what?",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.SAD),
					new CharacterState("Bob", Position.RIGHT, Emotion.SAD)
			))
	));
	choiceLines.add(new Line(
			"Alice: idk... Go to school?",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.SAD),
					new CharacterState("Bob", Position.RIGHT, Emotion.SAD)
			))
	));
	choiceLines.add(new Line(
			"Bob: Are you stupid? Girl go to school!",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.SAD),
					new CharacterState("Bob", Position.RIGHT, Emotion.SAD)
			))
	));
	choiceLines.add(new Line(
			"You contemplate whether you go to school or not...",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.SAD)
			))
	));

	List<Choice> choices = new ArrayList<>();
	choices.add(new Choice("Go to school", "school").addStat("responsibility",'+',2));
	choices.add(new Choice("Skip school", "skip").addStat("responsibility",'-',2));

	engine.addNode(new NodeChoice("choice", choiceLines, choices));

	// --- NODE 4 : SCHOOL ---
	Queue<Line> schoolLines = new ArrayDeque<>();
	schoolLines.add(new Line(
			"You're in school. The teacher looks mad.",
			new ArrayList<>(List.of(
					new CharacterState("Teacher", Position.CENTER, Emotion.ANGRY),
					new CharacterState("Alice", Position.LEFT, Emotion.FLUSTERED)
			))
	));

	engine.addNode(new NodeDialogue("school", schoolLines, "ending"));

	// --- NODE 5 : SKIP ---
	Queue<Line> skipLines = new ArrayDeque<>();
	skipLines.add(new Line(
			"You skip school and feel kinda bad.",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.SAD),
					new CharacterState("Bob", Position.RIGHT, Emotion.BLUSHING)
			))
	));

	engine.addNode(new NodeDialogue("skip", skipLines, "ending"));

	// --- NODE 6 : ENDING ---
	Queue<Line> endLines = new ArrayDeque<>();
	endLines.add(new Line(
			"The day comes to an end.",
			new ArrayList<>(List.of(
					new CharacterState("Alice", Position.CENTER, Emotion.NEUTRAL)
			))
	));

	engine.addNode(new NodeDialogue("ending", endLines, null));

	engine.goTo("room");
}
