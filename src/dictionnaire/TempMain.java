package dictionnaire;

public class TempMain {

	public static void main(String[] args) {
		
		LexiNode dictionary = new LexiNode(' ');
		
		Word word1 = new Word("test", "tester de quoi");
		Word word2 = new Word("tester", "tester de quoi");
		Word word3 = new Word("testorons", "tester de quoi");

		dictionary.findNewWordBranch(word1, 0);
		dictionary.findNewWordBranch(word2, 0);
		dictionary.findNewWordBranch(word3, 0);
	}

}
