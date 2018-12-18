package dictionnaire;

/**
 * This class represents a LexiWord which is used in LexiNode.
 * 
 * @author Nicolas Chartier
 *
 */
public class LexiWord {
	
	private String word;
	private String defenition;
	
	/**
	 * This constructor creates a new LexiWord instance given the params.
	 * 
	 * @param word
	 * @param defenition
	 */
	public LexiWord(String word, String defenition) {
		this.word = word;
		this.defenition = defenition;
	}
	
	/**
	 * Get the word from LexiWord.
	 * 
	 * @return word the word
	 */
	public String getWord() {
		return word;
	}
//	public void setWord(String word) {
//		this.word = word;
//	}
	
	/**
	 * Get the definition from LexiWord.
	 * 
	 * @return defenition the defenition
	 */
	public String getDefenition() {
		return defenition;
	}
	
	/**
	 * Set the definition.
	 * 
	 * @param defenition the definition to set
	 */
	public void setDefenition(String defenition) {
		this.defenition = defenition;
	}
	
	@Override
	public String toString() {
		return getWord();
	}
	

}
