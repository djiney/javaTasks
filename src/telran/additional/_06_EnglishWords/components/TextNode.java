package telran.additional._06_EnglishWords.components;

import java.util.HashMap;
import java.awt.*;

public class TextNode
{
	private List words;
	private HashMap<Character, TextNode> nodes;

	public TextNode()
	{
		this.nodes = new HashMap<>();
		this.words = new List();
	}

	public void addWord(String word)
	{
		this.addWord(word, 0);
	}

	public List search(String prefix)
	{
		return this.search(prefix, 0);
	}

	private void addWord(String word, int depth)
	{
		if (word == null) {
			return;
		}

		this.words.add(word);

		if (depth == word.length()) {
			return;
		}

		char key = word.charAt(depth);

		TextNode node;

		if (this.nodes.containsKey(key)) {
			node = this.nodes.get(key);
		} else {
			node = new TextNode();
			this.nodes.put(key, node);
		}

		node.addWord(word, depth + 1);
	}

	private List search(String prefix, int depth)
	{
		if (depth == prefix.length()) {
			return this.words;
		}

		char key = prefix.charAt(depth);

		if (this.nodes.containsKey(key)) {
			return this.nodes.get(key).search(prefix, depth + 1);
		}

		return new List();
	}
}
