import java.util.Map;
import java.util.HashMap;

public class GameState {
	private Map<String, Boolean> flags;
	private Map<String, Integer> stats;
	
	public GameState() {
		this.flags = new HashMap<>();
        this.stats = new HashMap<>();
	}

	//flags
    public void setFlag(String key, boolean value) {
        flags.put(key, value);
    }
    public boolean getFlag(String key) {
        return flags.getOrDefault(key, false);
    }
    public boolean hasFlag(String key) {
        return flags.containsKey(key);
    }

	//stats
    public void setStat(String key, int value) {
        stats.put(key, value);
    }
    public int getStat(String key) {
        return stats.getOrDefault(key, 0);
    }
    public void addStat(String key, int delta) {
        stats.put(key, getStat(key) + delta);
    }

    // Optional: debug
    @Override
    public String toString() {
        return "Flags: " + flags.toString() + "\nStats: " + stats.toString();
    }
}
