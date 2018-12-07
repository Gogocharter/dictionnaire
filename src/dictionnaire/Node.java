package dictionnaire;

import java.util.LinkedList;

class Node {

	char letter;
	Word word;
	LinkedList<Node> children;
	
	public Node(char letter, Word word) {
		this.letter = letter;
		this.word = word;
	}
	

	public void exist(Word word, int position) {		
		
		for (Node child : children) {
			if (child.letter == word.getWord().charAt(position)) {
				child.addWord(word, position+1);
				break;
			}
		}

	
	}
	
	
	public void findNewWordBranch(Word word, int position) {

		boolean exist = false;
		for (Node child : children) {
			if (child.letter == word.getWord().charAt(position)) {
				child.findNewWordBranch(word, position+1);
				exist = true;
				break;
			}
		}
		
		if (!exist) {
			addWord(word, position);
		}	

	}
	
	public void addWord(Word word, int position) {
		
		if (word.getWord().length() < position) {
			children.add(new Node(word.getWord().charAt(position), word));
		}else {
			Node newNode = new Node(word.getWord().charAt(position));
			children.add(newNode);
			
			newNode.addWord(word, position+1);	
		}			
		
	}
	
	
	
	public Node(char letter) {
		this.letter = letter;
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
	public LinkedList<Node> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<Node> children) {
		this.children = children;
	}
	
	
}
