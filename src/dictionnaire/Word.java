package dictionnaire;

public class Word {
	
	private String word;
	private String defenition;
	
	public Word(String word, String defenition) {
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
	
	

}
