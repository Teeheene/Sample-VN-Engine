import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Choice {
	private String text;
	private String nextNode;
	private Set<String> flags;
	private Map<String, Integer> stats;

	public Choice() {
		this.flags = new HashSet<>();
		this.stats = new HashMap<>();
	}

	public Choice(String text, String nextNode) {
		this();
		this.text = text;
		this.nextNode = nextNode;
	}

	//getters
	public String getText() {
		return text;
	}
	public String getNextNode() {
		return nextNode;
	}

	//setters or adders lulz
	public Choice addFlag(String key) {
		flags.add(key);
        return this;
    }

	//set base stats 
    public Choice addStat(String key, char operator, int value) {
		switch(operator) {
			case '+': stats.put(key, value); break;
			case '-': stats.put(key, 0 - value); break;
			default: stats.put(key, value); break;
		}
        return this;
    }

	public void updateGameState(GameState gameState) {
		// Apply flags
		for(String flag : flags) {
			gameState.addFlag(flag);
		}

		// Apply stats
		for (Map.Entry<String, Integer> entry : stats.entrySet()) {
			gameState.addStat(entry.getKey(), entry.getValue());
		}
	} 

	@Override 
	public String toString() {
		return "Choice{text=" + text + "\n"+
			"       nextNode=" + nextNode + "\n"+
			"       flags=" + flags + "\n"+
			"       stats=" + stats + "\n"+
			"}";
	}
}
