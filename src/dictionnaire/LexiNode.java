package dictionnaire;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Cette classe permet de faire la gestion du dictionnaire de mots
 * 
 * @author Nicholas Chartier
 *
 */
public class LexiNode {

	/**
	 * Lettre du node
	 */
	private char letter;
	/**
	 * mot relié au node
	 */
	private LexiWord lexiWord;
	/**
	 * Enfant du node
	 */
	private LinkedList<LexiNode> children = new LinkedList<LexiNode>();

	/**
	 * Constructeur d'un LexiNode: Contient deux paramï¿½tres pour ajouter la lettre
	 * et le mot (LexiWord) dans le LexiNode
	 * 
	 * @param letter Lettre du LexiNode
	 * @param word   Mot de la feuille
	 */
	private LexiNode(char letter, LexiWord word) {
		this.letter = letter;
		this.lexiWord = word;
	}

	/**
	 * Constructeur d'un LexiNode: Contient un seul paramï¿½tre car il n'existe pas
	 * de mot (LexiWord) a cet instance
	 * 
	 * @param letter Lettre du LexiNode
	 */
	private LexiNode(char letter) {
		this.letter = letter;
	}

	/**
	 * Contructeur initialisant le Node principale de LexiNode.
	 */
	public LexiNode() {
		this.letter = ' ';
	}

	/**
	 * This method opens a file when given a String path. It then loads the words
	 * and their definitions into the dictionary.
	 * 
	 * @param path the path of the file we are loading
	 */
	public void LoadFile(String path) {
		try {
			InputStream inputStream = new FileInputStream(path); // "D:\\Workspace\\git\\dictionnaire\\Dictio.txt"
			InputStreamReader inputReader = new InputStreamReader(inputStream);
			BufferedReader buffer = new BufferedReader(inputReader);
			String ligne;
			while ((ligne = buffer.readLine()) != null) {

				String[] wordDef = ligne.trim().split(" & ");
				if (wordDef.length == 2) {
					LexiWord newWord = new LexiWord(wordDef[0].toLowerCase(), wordDef[1]);
					findAddorUpdateBranch(newWord, 0);
				}

			}
			buffer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	};

	/**
	 * This method saves a dictionary to a file when given the path to save the file
	 * to.
	 * 
	 * @param path the path where the file will be saved
	 */
	public void saveFile(String path) {

		LinkedList<LexiWord> words = new LinkedList<LexiWord>();
		words = allWords(this, words);

		try {
			FileWriter file = new FileWriter(path);
			for (LexiWord lexiWord : words) {
				file.write(lexiWord.getWord() + " & " + lexiWord.getDefenition() + "\n");
			}
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Cette mï¿½thode permet de chercher la branche du LexiNode ou le mot (Word) en
	 * paramï¿½tre se trouve
	 * 
	 * @param word     Mot ï¿½ rechercher la branche
	 * @param position Position de la lettre du mot(word) en paramï¿½tre
	 * @return Retourne la branche oï¿½ le mot recherchï¿½ est retrouvï¿½
	 * 
	 * @requires
	 */
	private LexiNode findBranch(String word, int position) {

		boolean exist = false;
		LexiNode nextNode = this;
		if (word.length() > position) {

			for (LexiNode child : this.getChildren()) {
				if (child.letter == word.charAt(position)) {
					nextNode = child;
					exist = true;
					break;
				}
			}
		} else {
			return nextNode;
		}

		if (!exist) {
			return null;
		}

		return nextNode.findBranch(word, position + 1);
	}

	/**
	 * Cette mï¿½thode permet de retourner les mots possible dans le dictionnaire
	 * ï¿½ partir d'un mot reï¿½us en paramï¿½tre.
	 * 
	 * @param word mot ï¿½ partir pour faire notre recherche
	 * @return Retourne une liste de LexiWord possible
	 */
	public LinkedList<LexiWord> Search(String word) {

		LexiNode branch = findBranch(word, 0);
		LinkedList<LexiWord> list = new LinkedList<LexiWord>();

		if (branch != null) {
			list = new LinkedList<LexiWord>();
			if (branch.getLexiWord() != null) {
				list.add(branch.getLexiWord());
			}

			list = allWords(branch, list);

			return list;
		}

		return list;

	}

	/**
	 * Mï¿½thode permettant de chercher un mot dans le dictionnaire
	 * 
	 * @param word Mot recherhï¿½ dans la mï¿½thode
	 * @return Retourne l'objet du mot(LexiWord)
	 */
	public LexiWord SearchSingleWord(String word) {

		LexiNode branch = findBranch(word, 0);

		if (branch.lexiWord == null) {
			return null;
		}

		return branch.getLexiWord();

	}

	/**
	 * Methode permettant de retourner tout les LexiWord possible dans le
	 * dictionnaire à partir d'une branche dans le node
	 * 
	 * @param branch Branche oï¿½ la recherche est rendu
	 * @param list   liste de tout les Lexiword trouvï¿½
	 * @return Retourne la liste de tout les Lexiword trouvï¿½ dans le node
	 */
	public LinkedList<LexiWord> allWords(LexiNode branch, LinkedList<LexiWord> list) {

		for (LexiNode child : branch.getChildren()) {
			if (child.getLexiWord() != null) {
				list.add(child.getLexiWord());
			}

			if (child.getChildren() != null) {
				list = allWords(child, list);
			}
		}
		return list;
	}

	/**
	 * This method either adds a word if it is not already in the dictionary or
	 * modifies an existing word's definition.
	 * 
	 * @param word word to add or update
	 * @param position position of the word in the recursive
	 */
	public void findAddorUpdateBranch(LexiWord word, int position) {

		boolean exist = false;

		if (word.getWord().length() > position) {

			for (LexiNode child : children) {
				if (child.letter == word.getWord().charAt(position)) {
					child.findAddorUpdateBranch(word, position + 1);
					exist = true;
					break;
				}
			}
		} else {
			updateLexiWord(word);
			exist = true;
		}

		// si mot n'existe pas
		if (!exist) {
			addLexiWord(word, position);
		}

	}

	/**
	 * This method create the nodes necessary to create the word to finally add the
	 * word at the end of the node tree
	 * 
	 * @param word     LexiWord(Word) to add to the dictionary
	 * @param position Position of the letter of the word the recursive method is at
	 */
	private void addLexiWord(LexiWord word, int position) {

		LexiNode newNode;

		if ((word.getWord().length() - 1) == position) {

			newNode = new LexiNode(word.getWord().charAt(position), word);

			if (children.isEmpty() || children.getLast().getLetter() < newNode.getLetter()) {
				children.add(newNode);
			} else {
				for (int i = 0; i < children.size(); i++) {
					if (newNode.getLetter() > children.get(i).getLetter()) {
						children.add(i, newNode);
						break;
					}
				}
			}

		} else {
			newNode = new LexiNode(word.getWord().charAt(position));

			if (children.isEmpty() || children.getLast().getLetter() < newNode.getLetter()) {
				children.add(newNode);
			} else {
				for (int i = 0; i < children.size(); i++) {
					if (newNode.getLetter() > children.get(i).getLetter()) {
						children.add(i, newNode);
						break;
					}
				}
			}
			children.getLast().addLexiWord(word, position + 1);
		}
	}

	/**
	 * This method updates the LexiWord.
	 * 
	 * @param word the word to update
	 */
	private void updateLexiWord(LexiWord word) {

		this.setLexiWord(word);
	}

	/**
	 * Get letter.
	 * 
	 * @return letter the letter
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * Set letter
	 * 
	 * @param letter the letter to set
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}

	/**
	 * Get LexiWord
	 * 
	 * @return LexiWord the LexiWord
	 */
	private LexiWord getLexiWord() {
		return lexiWord;
	}

	/**
	 * Set LexiWord
	 * 
	 * @param word the LexiWord to set
	 */
	private void setLexiWord(LexiWord word) {
		this.lexiWord = word;
	}

	/**
	 * Get LexiNode's children.
	 * 
	 * @return children the children of the LexiNode
	 */
	public LinkedList<LexiNode> getChildren() {
		return children;
	}

	/**
	 * Set LexiNode's children.
	 * 
	 * @param children the children to set
	 */
	public void setChildren(LinkedList<LexiNode> children) {
		this.children = children;
	}
}