import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class NodeLoader {
	//path to yaml file
	private final String path;

	public NodeLoader(String path) {
		this.path = path;
	}

	public Node loadNode() {
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
			Yaml yaml = new Yaml();	
			Map<String, Object> rawNode = yaml.load(inputStream);
			return YamlParser.createNode(rawNode);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
