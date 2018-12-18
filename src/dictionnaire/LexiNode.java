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

	private char letter;
	private LexiWord lexiWord;
	private LinkedList<LexiNode> children = new LinkedList<LexiNode>();

	/**
	 * Constructeur d'un LexiNode: Contient deux param�tres pour ajouter la lettre
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
	 * Constructeur d'un LexiNode: Contient un seul param�tre car il n'existe pas de
	 * mot (LexiWord) a cet instance
	 * 
	 * @param letter Lettre du LexiNode
	 */
	private LexiNode(char letter) {
		this.letter = letter;
	}

	/**
	 * Contructeur initialisant le Node principale de LexiNode.
	 * 
	 */
	public LexiNode() {
		this.letter = ' ';
	}

	/**
	 * This method opens a file when given a String path. It then
	 * loads the words and their definitions into the dictionary.
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
					findNewWordBranch(newWord, 0);
				}
				
			}
			buffer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	};

	/**
	 * This method saves a dictionary to a file when given
	 * the path to save the file to.
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
	 * Cette m�thode permet de chercher la branche du LexiNode ou le mot (Word) en
	 * param�tre se trouve
	 * 
	 * @param word     Mot � rechercher la branche
	 * @param position Position de la lettre du mot(word) en param�tre
	 * @return Retourne la branche o� le mot recherch� est retrouv�
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
	 * Cette m�thode permet de retourner les mots possible dans le dictionnaire �
	 * partir d'un mot re�us en param�tre.
	 * 
	 * @param word mot � partir pour faire notre recherche
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
	 * M�thode permettant de chercher un mot dans le dictionnaire
	 * 
	 * @param word Mot recherh� dans la m�thode
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
	 * dictionnaire � partir d'une branche dans le node
	 * 
	 * @param branch Branche o� la recherche est rendu
	 * @param list   liste de tout les Lexiword trouv�
	 * @return Retourne la liste de tout les Lexiword trouv� dans le node
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
	 * @param word
	 * @param position
	 */
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
			updateLexiWord(word);
			exist = true;
		}

		// si mot n'existe pas
		if (!exist) {
			addLexiWord(word, position);
		}

	}

	/**
	 * 
	 * @param word
	 * @param position
	 */
	private void addLexiWord(LexiWord word, int position) {

		if ((word.getWord().length() - 1) == position) {
			children.add(new LexiNode(word.getWord().charAt(position), word));
		} else {
			LexiNode newNode = new LexiNode(word.getWord().charAt(position));
			children.add(newNode);

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