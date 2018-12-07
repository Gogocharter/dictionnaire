package dictionnaire;

public class Word {
	
	private String Word;
	private String defenition;
	
	public Word(String word, String defenition) {
		Word = word;
		this.defenition = defenition;
	}
	
	public String getWord() {
		return Word;
	}
	public void setWord(String word) {
		Word = word;
	}
	public String getDefenition() {
		return defenition;
	}
	public void setDefenition(String defenition) {
		this.defenition = defenition;
	}
	
	

}
