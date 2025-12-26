import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		NodeLoader loader = new NodeLoader("intro.yaml");
		NodeDialogue sample = (NodeDialogue) loader.loadNode();

		sample.enter(new VNEngine());
	}
}
