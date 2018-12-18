package dictionnaire;

public class LexiWord {
	
	private String word;
	private String defenition;
	
	public LexiWord(String word, String defenition) {
		this.word = word;
		this.defenition = defenition;
	}
	
	public String getWord() {
		return word;
	}
//	public void setWord(String word) {
//		this.word = word;
//	}
	public String getDefenition() {
		return defenition;
	}
	public void setDefenition(String defenition) {
		this.defenition = defenition;
	}
	
	@Override
	public String toString() {
		return getWord();
	}
	

}
