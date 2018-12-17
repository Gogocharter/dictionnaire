package dictionnaire;

import java.util.LinkedList;

public class LexiNode {

	char letter;
	private LexiWord word;
	private LinkedList<LexiNode> children = new LinkedList<LexiNode>();

	public LexiNode(char letter, LexiWord word) {
		this.letter = letter;
		this.word = word;
	}

	public LexiNode(char letter) {
		this.letter = letter;
	}

	public void exist(LexiWord word, int position) {

		if (word.getWord().length() > position) {
			for (LexiNode child : children) {
				if (child.letter == word.getWord().charAt(position)) {
					child.addWord(word, position + 1);
					break;
				}
			}
		}
	}

	public LexiNode findWord(String mot) {
		LexiWord word = new LexiWord(mot, "");
		findNewWordBranch(word, 0);

		return this;
	}

	private LexiNode findBranch(LexiWord word, int position) {

		boolean exist = false;
		LexiNode nextNode = this;
		if (word.getWord().length() > position) {

			for (LexiNode child : children) {
				if (child.letter == word.getWord().charAt(position)) {
					nextNode = child;
					exist = true;
					break;
				}
			}
		} else {
			return nextNode;
		}

		if (!exist) {
			return nextNode;
		}

		return nextNode.findBranch(word, position + 1);
	}

	public LinkedList<LexiWord> Search(LexiWord word) {

		LexiNode branch = findBranch(word, 0);
		LinkedList<LexiWord> list = new LinkedList<LexiWord>();
		if (branch.getWord() != null) {
			list.add(branch.getWord());
		}
		
		list = allWords(branch, list);
		
		return list;
	}
	
	public LexiWord SearchSingleWord(LexiWord word){
		
		LexiNode branch = findBranch(word, 0);
		
		if (branch.word == null) {
			return null;
		}
				
		return branch.getWord();
		
	}
	
	public LinkedList<LexiWord> allWords(LexiNode branch, LinkedList<LexiWord> list){
		
		for (LexiNode child : branch.getChildren()) {
			if (child.getWord() != null) {
				list.add(child.getWord());
			}
			
			if (child.getChildren() != null) {
				list = allWords(child, list);
			}
		}
		
		return list;
	}

	public void findNewWordBranch(LexiWord word, int position) {

		boolean exist = false;

		if (word.getWord().length() > position) {

			for (LexiNode child : children) {
				if (child.letter == word.getWord().charAt(position)) {
					child.findNewWordBranch(word, position + 1);
					exist = true;
					break;
				}
			}
		} else {
			exist = true;
		}

		if (!exist) {
			addWord(word, position);
		}

	}

	public void addWord(LexiWord word, int position) {

		if ((word.getWord().length() - 1) == position) {
			children.add(new LexiNode(word.getWord().charAt(position), word));
		} else {
			LexiNode newNode = new LexiNode(word.getWord().charAt(position));
			children.add(newNode);

			children.getLast().addWord(word, position + 1);
		}

	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	private LexiWord getWord() {
		return word;
	}

	private void setWord(LexiWord word) {
		this.word = word;
	}

	public LinkedList<LexiNode> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<LexiNode> children) {
		this.children = children;
	}
}