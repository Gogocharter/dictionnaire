package dictionnaire;

public class LexiNode {

	Node node;
	
	public static void main(String[] args) {
		
		
		Word word1 = new Word("test","tester de quoi");
		Word word2 = new Word("tester","tester de quoi");
		Word word3 = new Word("testorons","tester de quoi");
		
		addWord(word1);
		addWord(word2);
		addWord(word3);
	}
	
	public static void addWord(Word word) {
		
		
		for (int i = 0; i < word.getWord().length(); i++) {
			
		}
	}
}

