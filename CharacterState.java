public class CharacterState {
    private String name;
    private Position position = Position.LEFT;
    private Emotion emotion = Emotion.NEUTRAL;

    public CharacterState() {}

    public CharacterState(String name) {
        this(name, Position.LEFT, Emotion.NEUTRAL);
    }

    public CharacterState(String name, Position position, Emotion emotion) {
        this.name = name;
        this.position = position;
        this.emotion = emotion;
    }

    public String getName() { return name; }
    public Position getPosition() { return position; }
    public Emotion getEmotion() { return emotion; }

    public void setName(String name) { this.name = name; }
    public void setPosition(Position position) { this.position = position; }
    public void setEmotion(Emotion emotion) { this.emotion = emotion; }

    @Override
    public String toString() {
        return name + " [" + position + "] Emotion: " + emotion;
    }
}

