import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class NodeDialogue extends Node {
    private Queue<Line> lines;
    private String backgroundPath;
    private String musicPath;
    private String nextNode;

    public NodeDialogue() {}

    public NodeDialogue(String id, Queue<Line> lines, String nextNode) {
		this.id = id;
        this.lines = lines;
        this.nextNode = nextNode;
    }

    public void setBackgroundPath(String backgroundPath) { this.backgroundPath = backgroundPath; }
    public void setMusicPath(String musicPath) { this.musicPath = musicPath; }

    public Queue<Line> getLines() { return lines; }
    public String getBackgroundPath() { return backgroundPath; }
    public String getMusicPath() { return musicPath; }
    public String getNextNode() { return nextNode; }

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
        engine.goTo(nextNode);
    }
}

