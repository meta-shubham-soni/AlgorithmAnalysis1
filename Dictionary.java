import java.util.*;

public interface Dictionary {
	public void add(String key, String value) throws Exception;

	public void delete(String key) throws Exception;

	public String getValue(String key);

	public List<Pair> getAll();

	public List<Pair> getBetween(String key1, String key2);
}
