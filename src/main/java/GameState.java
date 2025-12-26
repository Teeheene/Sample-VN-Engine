import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GameState {
	private Set<String> flags;
	private Map<String, Integer> stats;
	
	public GameState() {
		this.flags = new HashSet<>();
        this.stats = new HashMap<>();
	}

	//flags
    public void addFlag(String key) {
		flags.add(key);
    }
    public boolean hasFlag(String key) {
        return flags.contains(key);
    }
	public void removeFlag(String key) {
		flags.remove(key);
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
