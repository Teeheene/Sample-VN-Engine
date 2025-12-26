import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class NodeChoice extends Node {
    private Queue<Line> lines;
	private List<Choice> choices;
    private String backgroundPath;
    private String musicPath;

    public NodeChoice() {}

    public NodeChoice(String id, Queue<Line> lines, List<Choice> choices) {
		this.id = id;
        this.lines = new ArrayDeque<>(lines);
		this.choices = new ArrayList<>(choices);
    }

    public void setBackgroundPath(String backgroundPath) { this.backgroundPath = backgroundPath; }
    public void setMusicPath(String musicPath) { this.musicPath = musicPath; }

    public Queue<Line> getLines() { return lines; }
    public String getBackgroundPath() { return backgroundPath; }
    public String getMusicPath() { return musicPath; }

    @Override
    public void enter(VNEngine engine) {
        Queue<Line> dialogue = new ArrayDeque<>(lines);
        Scanner scanner = new Scanner(System.in);

        while (!dialogue.isEmpty()) {
            Line line = dialogue.poll();
            System.out.println("\n" + line.getText());

            for (CharacterState c : line.getCharacters()) {
                System.out.println(c.toString());
            }

			if(scanner.hasNextLine())
            	scanner.nextLine();
        }
		
		System.out.println("Make a choice: ");
		for(int i = 0; i < choices.size(); i++) {
			System.out.println(i + 1 + ". " + choices.get(i).getText());
		}

		int selection = -1;
		while(selection < 1 || selection > choices.size()) {
			System.out.println("Enter choice number: ");
			if(scanner.hasNextInt()) {
				selection = scanner.nextInt();
			} else {
				scanner.next();
			}
		}

		Choice selected = choices.get(selection - 1);
		System.out.println(selected.toString());
		selected.updateGameState(engine.getGameState());
		System.out.println(engine.getGameState().toString());
		engine.goTo(selected.getNextNode());
    }
}

