import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import javax.json.*;


public class MyDictionary implements Dictionary {

	Node root;
	List<Pair> allPairs;
	MyDictionary() throws Exception{
		root = null;
		input();
	}
	
	//Function to read input from json file
	void input() throws Exception{
		final String JSON_FILE = "data.txt"; //JSON file name
		InputStream file = new FileInputStream(JSON_FILE);  //creating object of that file
		JsonReader jsonReader = Json.createReader(file); //initializing the JSON reader to read the json file
		JsonObject jsonObject = jsonReader.readObject(); //reading the json object
		jsonReader.close();
		file.close();
		
		Iterator<String> keys = jsonObject.keySet().iterator();  //get the set of the keys in JSON file
		Iterator<JsonValue> values = jsonObject.values().iterator();
		
		while(keys.hasNext()){
			add(keys.next(),values.next().toString());
		}
	}
	
	/**
	 * Function to insert a new pair in bst
	 * @param rootNode
	 * @param key
	 * @param value
	 * @return
	 */
	private Node insert(Node rootNode,String key, String value){
		if(rootNode == null){
			rootNode = new Node(null,null,new Pair(key,value));
			return rootNode;
		}
		if(key.compareTo(rootNode.value.key)> 0){
				rootNode.right = insert(rootNode.right,key,value);
			}
			else{
				rootNode.left =  insert(rootNode.left,key,value);
			}
		return rootNode;
		
	}
	

	@Override
	public void add(String key, String value) throws Exception {
		root = insert(root,key,value);
	}
	
	/**
	 * Function to delete a pair from bst
	 * @param root
	 * @param key
	 * @return
	 */
	private Node remove(Node root, String key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)
            return root;
 
        /* Otherwise, recur down the tree */
        if (key.compareTo(root.value.key) < 0)
            root.left = remove(root.left, key);
        else if (key.compareTo(root.value.key) > 0)
            root.right = remove(root.right, key);
 
        // if key is same as root's 
        // key, then This is the
        // node to be deleted
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.value.key = minValue(root.right);
 
            // Delete the inorder successor
            root.right = remove(root.right, root.value.key);
        }
 
        return root;
    
    }
	
	@Override
	public void delete(String key) throws Exception {
		remove(root,key);
	}

	/**
	 * Function to get value of Key
	 * @param key
	 * @return value 
	 */
	@Override
	public String getValue(String key) {
		if(root == null) return "Dictonary is empty";
		Node temp = root;
		while(temp!=null){
			if(temp.value.key.compareTo(key) == 0) return temp.value.value;
			else if(key.compareTo(temp.value.key) < 0) temp = temp.left;
			else temp = temp.right;
		}
		return "Not found";
	}

	/**
	 * Function to inorder traverse the Dictionary
	 * @param root
	 */
	void inorder(Node root){
		if(root != null){
			inorder(root.left);
			allPairs.add(root.value);
			inorder(root.right);
		}
	}
	
	/**
	 * Function to get all Pairs of Dictionary
	 * @return List<Pair>
	 */
	@Override
	public List<Pair> getAll() {
		if(root == null) return null;
		allPairs = new ArrayList<Pair>();
		
		inorder(root);
		return allPairs;
	}

	@Override
	public List<Pair> getBetween(String key1, String key2) {
		List<Pair> tempList = this.getAll();
		int k1Ind = -1, k2Ind = -1;
		for(int i=0;i<tempList.size();i++){
			if(tempList.get(i).key.equals(key1))
				k1Ind = i;
			if(tempList.get(i).key.equals(key2))
				k2Ind = i;
		}
		return tempList.subList(k1Ind, k2Ind+1);
	}

	
    private String minValue(Node root)
    {
        String minv = root.value.key;
        while (root.left != null) 
        {
            minv = root.left.value.key;
            root = root.left;
        }
        return minv;
    }
}
