import java.util.ArrayList;
import java.util.List;

public class Line {
    private String text;
    private List<CharacterState> characters = new ArrayList<>();

    public Line() {}

    public Line(String text) {
        this(text, new ArrayList<>());
    }

    public Line(String text, List<CharacterState> characters) {
        this.text = text;
        this.characters = characters;
    }

    public String getText() { return text; }
    public List<CharacterState> getCharacters() { return characters; }

    public void setText(String text) { this.text = text; }
    public void setCharacters(List<CharacterState> characters) { 
        this.characters = characters; 
    }
}

