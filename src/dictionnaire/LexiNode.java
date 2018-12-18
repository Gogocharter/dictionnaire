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
	 * Constructeur d'un LexiNode: Contient deux paramètres pour ajouter la lettre
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
	 * Constructeur d'un LexiNode: Contient un seul paramètre car il n'existe pas de
	 * mot (LexiWord) a cet instance
	 * 
	 * @param letter Lettre du LexiNode
	 */
	private LexiNode(char letter) {
		this.letter = letter;
	}

	/**
	 * Contructeur initialisant le Node principale de LexiNode
	 */
	public LexiNode() {
		this.letter = ' ';
	}

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

//
//	public void exist(LexiWord word, int position) {
//
//		if (word.getWord().length() > position) {
//			for (LexiNode child : children) {
//				if (child.letter == word.getWord().charAt(position)) {
//					child.addLexiWord(word, position + 1);
//					break;
//				}
//			}
//		}
//	}

	/**
	 * Cette méthode permet de chercher la branche du LexiNode ou le mot (Word) en
	 * paramètre se trouve
	 * 
	 * @param word     Mot à rechercher la branche
	 * @param position Position de la lettre du mot(word) en paramètre
	 * @return Retourne la branche où le mot recherché est retrouvé
	 * 
	 * @requires
	 */
	private LexiNode findBranch(LexiWord word, int position) {

		boolean exist = false;
		LexiNode nextNode = this;
		if (word.getWord().length() > position) {

			for (LexiNode child : this.getChildren()) {
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
			return this;
		}

		return nextNode.findBranch(word, position + 1);
	}

	/**
	 * Cette méthode permet de retourner les mots possible dans le dictionnaire à
	 * partir d'un mot reçus en paramètre.
	 * 
	 * @param word mot à partir pour faire notre recherche
	 * @return Retourne une liste de LexiWord possible
	 */
	public LinkedList<LexiWord> Search(LexiWord word) {

		LexiNode branch = findBranch(word, 0);

		if (branch != null) {
			LinkedList<LexiWord> list = new LinkedList<LexiWord>();
			if (branch.getLexiWord() != null) {
				list.add(branch.getLexiWord());
			}

			list = allWords(branch, list);

			return list;
		}

		return null;

	}

	/**
	 * Méthode permettant de chercher un mot dans le dictionnaire
	 * 
	 * @param word Mot recherhé dans la méthode
	 * @return Retourne l'objet du mot(LexiWord)
	 */
	public LexiWord SearchSingleWord(LexiWord word) {

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
	 * @param branch Branche où la recherche est rendu
	 * @param list   liste de tout les Lexiword trouvé
	 * @return Retourne la liste de tout les Lexiword trouvé dans le node
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
	public void addLexiWord(LexiWord word, int position) {

		if ((word.getWord().length() - 1) == position) {
			children.add(new LexiNode(word.getWord().charAt(position), word));
		} else {
			LexiNode newNode = new LexiNode(word.getWord().charAt(position));
			children.add(newNode);

			children.getLast().addLexiWord(word, position + 1);
		}

	}

	/**
	 * 
	 * @param word
	 */
	public void updateLexiWord(LexiWord word) {
		LexiNode branch = findBranch(word, 0);

		if (branch != null) {
			branch.setLexiWord(word);
			// ou
			// branch.getLexiWord().setDefenition(word.getDefenition());
		}
	}

	/**
	 * 
	 * @return
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * 
	 * @param letter
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}

	/**
	 * 
	 * @return
	 */
	private LexiWord getLexiWord() {
		return lexiWord;
	}

	/**
	 * 
	 * @param word
	 */
	private void setLexiWord(LexiWord word) {
		this.lexiWord = word;
	}

	/**
	 * 
	 * @return
	 */
	public LinkedList<LexiNode> getChildren() {
		return children;
	}

	/**
	 * 
	 * @param children
	 */
	public void setChildren(LinkedList<LexiNode> children) {
		this.children = children;
	}
}