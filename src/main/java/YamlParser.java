import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.List;

public class YamlParser {
	public static Node createNode(Map<String, Object> data) {
		String type = (String) data.get("type");

		if("dialogue".equalsIgnoreCase(type)) {
			return createDialogueNode(data);
		} else if("choice".equalsIgnoreCase(type)) {
			return createChoiceNode(data);
		} else if("conditional".equalsIgnoreCase(type)) {
//			return createConditionalNode(data);
		}

		throw new IllegalArgumentException("Unknown node type: " + type);
	}

	private static NodeDialogue createDialogueNode(Map<String, Object> data) {
		//required
        String id = (String) data.get("id");
        String next = (String) data.get("next");
        Queue<Line> lines = parseLines(data); 

		//build
		NodeDialogue node = new NodeDialogue(id, lines, next);
			
		//optional
		node.setBackgroundPath((String) data.get("background"));
        node.setMusicPath((String) data.get("music"));

        return node;
    }

	private static NodeChoice createChoiceNode(Map<String, Object> data) {
		//required
        String id = (String) data.get("id");
        Queue<Line> lines = parseLines(data);
        List<Choice> choices = new ArrayList<>();

        List<Map<String, Object>> rawChoices = (List<Map<String, Object>>) data.get("choices");
        if (rawChoices != null) {
            for (Map<String, Object> rc : rawChoices) {
                String text = (String) rc.get("text");
                String next = (String) rc.get("next");

				Choice choice = new Choice(text, next);

				//optional
				List<String> rawFlags = (List<String>) rc.get("flags");
				if (rawFlags != null) {
					for (String flag : rawFlags) {
						choice.addFlag(flag);
					}
				}
				Map<String, Object> rawStats =
					(Map<String, Object>) rc.get("stats");

				if (rawStats != null) {
					for (Map.Entry<String, Object> e : rawStats.entrySet()) {
						if (!(e.getValue() instanceof Number)) {
							throw new IllegalArgumentException(
								"Stat value must be numeric: " + e.getKey()
							);
						}

						int value = ((Number) e.getValue()).intValue();
						char op = value >= 0 ? '+' : '-';

						choice.addStat(e.getKey(), op, Math.abs(value));
					}
				}

				choices.add(choice);
            }
        }

        NodeChoice node = new NodeChoice(id, lines, choices);
		
		//optional
        node.setBackgroundPath((String) data.get("background"));
        node.setMusicPath((String) data.get("music"));

        return node;
	}

	private static Queue<Line> parseLines(Map<String, Object> data) {
        Queue<Line> lines = new ArrayDeque<>();
        List<Object> rawLines = (List<Object>) data.get("lines");
        if (rawLines != null) {
            for (Object rawLine : rawLines) {
                String text;
                List<CharacterState> characters = new ArrayList<>();

                if (rawLine instanceof String) {
                    text = (String) rawLine;
                } else if (rawLine instanceof Map) {
                    Map<String, Object> lineMap = (Map<String, Object>) rawLine;
                    text = (String) lineMap.get("text");

                    List<Map<String, String>> rawChars = (List<Map<String, String>>) lineMap.get("characters");
                    if (rawChars != null) {
                        for (Map<String, String> rc : rawChars) {
                            String name = rc.get("name");
                            String posStr = rc.get("position");
                            String emoStr = rc.get("expression");

                            Position position = Position.fromString(posStr);
                            Emotion emotion = Emotion.fromString(emoStr);

                            characters.add(new CharacterState(name, position, emotion));
                        }
                    }
                } else continue;

                lines.add(new Line(text, characters));
            }
        }
        return lines;
    }
}
