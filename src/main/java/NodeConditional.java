import java.util.Map;
import java.util.Set;

public class NodeConditional extends Node {
	private String defaultNext;
	private Map<Set<String>, String> flagRoutes; //FLAGS-> nodeId
	
	public NodeConditional(String id, String defaultNext, Map<Set<String>, String> flagRoutes) {
		this.id = id;
		this.defaultNext = defaultNext;
		this.flagRoutes = flagRoutes;
	}

	@Override
	void enter(VNEngine engine) {
		String next = defaultNext;

    	for (Map.Entry<Set<String>, String> entry : flagRoutes.entrySet()) { 
			Set<String> requiredFlags = entry.getKey();
            boolean allPresent = true;

            for (String flag : requiredFlags) {
                if (!engine.getGameState().hasFlag(flag)) {
                    allPresent = false;
                    break;
                }
            }

            if (allPresent) {
                next = entry.getValue();
                break;
            }
        }

        engine.goTo(next);
	}
}
