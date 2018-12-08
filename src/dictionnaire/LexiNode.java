package dictionnaire;

import java.util.LinkedList;

public class LexiNode {

	char letter;
	Word word;
	LinkedList<LexiNode> children = new LinkedList<LexiNode>();

	public LexiNode(char letter, Word word) {
		this.letter = letter;
		this.word = word;
	}

	public LexiNode(char letter) {
		this.letter = letter;
	}

	public void exist(Word word, int position) {

		if (word.getWord().length() > position) {
			for (LexiNode child : children) {
				if (child.letter == word.getWord().charAt(position)) {
					child.addWord(word, position + 1);
					break;
				}
			}
		}
	}

	public void findNewWordBranch(Word word, int position) {

		boolean exist = false;
		if (children != null && !children.isEmpty()) {

			if (word.getWord().length() > position) {

				for (LexiNode child : children) {
					if (child.letter == word.getWord().charAt(position)) {
						child.findNewWordBranch(word, position + 1);
						exist = true;
						break;
					}
				}
			}else {
				exist = true;
			}
		}
		if (!exist) {
			addWord(word, position);
		}

	}

	public void addWord(Word word, int position) {

		
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

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public LinkedList<LexiNode> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<LexiNode> children) {
		this.children = children;
	}
}